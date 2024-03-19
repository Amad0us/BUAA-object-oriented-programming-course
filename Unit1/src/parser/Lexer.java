package parser;

import java.math.BigInteger;
import java.util.ArrayList;

public class Lexer {
    private final ArrayList<Token> tokens = new ArrayList<>();//用于储存经过输入预处理的容器
    
    public ArrayList<Token> getTokens() {
        return tokens;
    }
    
    private int cur = 0;
    
    public boolean isEnd() {
        return cur >= tokens.size();
    }
    
    public Token peek() {
        return tokens.get(cur);
    }
    
    public boolean hasNext() {
        return (cur < tokens.size() - 1);
    }
    
    public void next(int n) {
        cur = cur + n;
    }
    
    public BigInteger getNumber(char mark) {
        String temp = peek().getContent();
        BigInteger num = BigInteger.valueOf(0);
        for (int i = 0; i < temp.length(); i++) {
            num = num.multiply(BigInteger.valueOf(10));
            num = num.add(BigInteger.valueOf(temp.charAt(i) - '0'));
        }
        if (mark == '-') {
            num = num.multiply(BigInteger.valueOf(-1));
        }
        return num;
    }
    
    public boolean judgeVar(char now) {
        return (now == 'x') || (now == 'y') || (now == 'z');
    }
    
    public boolean judgeFunc(char now) {
        return (now == 'f' || now == 'g' || now == 'h');
    }
    
    public String getString() {
        StringBuilder sb = new StringBuilder();
        for (Token token : tokens) {
            sb.append(token.getContent());
        }
        return sb.toString();
    }
    
    public Lexer(String target) {
        int position = 0;
        while (position < target.length()) {
            if (target.charAt(position) == '+' || target.charAt(position) == '-') {
                int now = position;
                int subNum = 0;
                while (target.charAt(now) == '+' || target.charAt(now) == '-') {
                    if (target.charAt(now) == '-') {
                        subNum++;
                    }
                    now++;
                }
                if (subNum % 2 == 0) {
                    tokens.add(new Token(Token.Type.ADD, "+"));
                } else {
                    tokens.add(new Token(Token.Type.SUB, "-"));
                }
                position = now;
            } else if (target.charAt(position) == '*') {
                tokens.add(new Token(Token.Type.MUL, "*"));
                position++;
            } else if (target.charAt(position) == '(') {
                tokens.add(new Token(Token.Type.LPAREN, "("));
                position++;
            } else if (target.charAt(position) == ')') {
                tokens.add(new Token(Token.Type.RPAREN, ")"));
                position++;
            } else if (target.charAt(position) == '^') {
                tokens.add(new Token(Token.Type.POW, "^"));
                position++;
            } else if (judgeVar(target.charAt(position))) {
                String p = target.charAt(position) + "";
                tokens.add(new Token(Token.Type.Variable, p));
                position++;
            } else if (judgeFunc(target.charAt(position))) {
                String p = target.charAt(position) + "";
                tokens.add(new Token(Token.Type.Func, p));
                position++;
            } else if (target.charAt(position) == 'e') {
                tokens.add(new Token(Token.Type.Exp, "exp"));
                position = position + 3;
            } else if (target.charAt(position) == ',') {
                tokens.add(new Token(Token.Type.Com, ","));
                position++;
            } else if (target.charAt(position) == 'd') {
                tokens.add(new Token(Token.Type.Der, "dx"));
                position = position + 2;
            } else {
                position = copeWithNumber(target, position);
            }
        }
    }
    
    public int copeWithNumber(String target, int init) {
        int position = init;
        char now = target.charAt(position);
        StringBuilder sb = new StringBuilder();
        while (now >= '0' && now <= '9') {
            sb.append(now);
            position++;
            if (position >= target.length()) {
                break;
            }
            now = target.charAt(position);
        }
        tokens.add(new Token(Token.Type.NUM, sb.toString()));
        return position;
    }
}

