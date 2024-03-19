package factor;

import parser.Parser;
import parser.Token;
import parser.Lexer;
import parser.Term;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class SelfDefinedFunc extends Expr {
    private BigInteger index = BigInteger.ONE;
    
    public void changeIndex(BigInteger index) {
        this.index = index;
    }
    
    private final ArrayList<Expr> realParameters;
    private final ArrayList<String> formParameters;
    private final Expr expression;
    private final ArrayList<Term> terms = new ArrayList<>();
    
    public ArrayList<Term> getTerms() {
        return terms;
    }
    
    public void postProcess() {
        expression.postProcess();
    }
    
    public Lexer replaceVariable(Lexer lexer) {
        ArrayList<Token> tokens = lexer.getTokens();
        StringBuilder sb = new StringBuilder();
        for (Token token : tokens) {
            boolean mark = true;
            for (int i = 0; i < realParameters.size(); i++) {
                if (token.getContent().equals(formParameters.get(i))) {
                    sb.append("(").append(realParameters.get(i)).append(")");
                    mark = false;
                    break;
                }
            }
            if (mark) {
                sb.append(token.getContent());
            }
        }
        return new Lexer(sb.toString());
    }
    
    public TreeMap<String, HashMap<BigInteger, BigInteger>> getMap() {
        return expression.getMap();
    }
    
    public SelfDefinedFunc(ArrayList<String> exprForm, ArrayList<Expr> factors, boolean negative) {
        super(negative);
        realParameters = new ArrayList<>(factors);
        formParameters = new ArrayList<>();
        for (int i = 0; i < exprForm.size() - 1; i++) {
            formParameters.add(exprForm.get(i));
        }
        for (Expr expr : realParameters) {
            expr.postProcess();
            
        }
        String expr = exprForm.get(exprForm.size() - 1);
        Lexer lexer = new Lexer(expr);
        lexer = replaceVariable(lexer);
        Parser tempParser = new Parser(lexer);
        expression = tempParser.parseExpr(negative);
        expression.changeIndex(index);
        terms.addAll(expression.getTerms());
    }
    
    public String toString() {
        return expression.toString();
    }
    
}
