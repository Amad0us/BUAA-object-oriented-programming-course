package parser;

import java.math.BigInteger;
import java.util.ArrayList;

import factor.Derivative;
import factor.Factor;
import factor.Expr;
import factor.Exponential;
import factor.Variable;
import factor.Number;
import factor.SelfDefinedFunc;

public class Parser {
    private final Lexer lexer;
    
    public Parser(Lexer lexer) {
        this.lexer = lexer;
    }
    
    public BigInteger getIndex() {
        BigInteger index = BigInteger.ONE;
        if (lexer.hasNext()) {
            lexer.next(1);
            if (lexer.peek().isPow()) {
                lexer.next(1);
                index = getNum();
            } else {
                lexer.next(-1);
            }
        }
        return index;
    }
    
    public BigInteger getNum() {
        char mark;
        if (lexer.peek().isSub()) {
            mark = '-';
            lexer.next(1);
        } else if (lexer.peek().isAdd()) {
            mark = '+';
            lexer.next(1);
        } else {
            mark = '+';
        }
        
        return lexer.getNumber(mark);
    }
    
    public boolean termExist() {
        if (lexer.isEnd()) {
            return false;
        }
        Token token = lexer.peek();
        return token.isAdd() || token.isSub();
    }
    
    public boolean factorExist() {
        if (lexer.isEnd()) {
            return false;
        }
        Token token = lexer.peek();
        return token.isMul();
    }
    
    public Expr parseExpr(boolean negative) {
        Expr expr = new Expr(negative);
        expr.addTerm(parseTerm());
        while (termExist()) {
            expr.addTerm(parseTerm());
        }
        return expr;
    }
    
    public Term parseTerm() {
        Term term = new Term();
        term.addFactor(parseFactor());
        while (factorExist()) {
            lexer.next(1);
            term.addFactor(parseFactor());
        }
        return term;
    }
    
    public Factor parseFactor() {
        boolean negative = lexer.peek().isSub();
        if (lexer.peek().isAdd() || lexer.peek().isSub()) {
            lexer.next(1);
        }
        if (lexer.peek().isLeft()) {
            lexer.next(1);
            Expr expr = parseExpr(negative);
            expr.changeIndex(getIndex());
            lexer.next(1);
            return expr;
        } else if (lexer.peek().isVar()) {
            char varType = lexer.peek().getContent().charAt(0);
            Variable variable = new Variable(varType, negative);
            variable.changeIndex(getIndex());
            lexer.next(1);
            return variable;
        } else if (lexer.peek().isFunc()) {
            char funcType = lexer.peek().getContent().charAt(0);
            final ArrayList<String> form = FuncMap.getFuncMap().get(funcType + "");
            lexer.next(2);
            ArrayList<Expr> factors = new ArrayList<>();
            Expr tempExpr = parseExpr(false);
            factors.add(tempExpr);
            while (!lexer.peek().isRight()) {
                lexer.next(1);
                tempExpr = parseExpr(false);
                factors.add(tempExpr);
            }
            SelfDefinedFunc selfDefinedFunc = new SelfDefinedFunc(form, factors, negative);
            selfDefinedFunc.changeIndex(getIndex());
            lexer.next(1);
            return selfDefinedFunc;
        } else if (lexer.peek().isExp()) {
            lexer.next(2);
            Exponential exponential = new Exponential(parseExpr(false), negative);
            exponential.changeIndex(getIndex());
            lexer.next(1);
            return exponential;
        } else if (lexer.peek().isDerive()) {
            lexer.next(2);
            Derivative derivative = new Derivative(parseExpr(false), negative);
            lexer.next(1);
            return derivative;
        } else {
            char mark;
            if (negative) {
                mark = '-';
            } else {
                mark = '+';
            }
            BigInteger temp = lexer.getNumber(mark);
            Number number = new Number(temp);
            number.changeIndex(getIndex());
            lexer.next(1);
            return number;
        }
    }
}
