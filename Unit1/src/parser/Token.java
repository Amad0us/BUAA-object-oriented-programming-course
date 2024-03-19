package parser;

public class Token {
    public enum Type {
        ADD, SUB, MUL, POW, LPAREN, RPAREN, NUM, Variable, Func, Exp, Com, Der
    }
    
    public boolean isPow() {
        return this.type == Type.POW;
    }
    
    public boolean isAdd() {
        return this.type == Type.ADD;
    }
    
    public boolean isSub() {
        return this.type == Type.SUB;
    }
    
    public boolean isLeft() {
        return this.type == Type.LPAREN;
    }
    
    public boolean isRight() {
        return this.type == Type.RPAREN;
    }
    
    public boolean isDerive() {
        return this.type == Type.Der;
    }
    
    public boolean isFunc() {
        return this.type == Type.Func;
    }
    
    public boolean isMul() {
        return this.type == Type.MUL;
    }
    
    public boolean isVar() {
        return this.type == Type.Variable;
    }
    
    public boolean isExp() {
        return this.type == Type.Exp;
    }
    
    private final Type type;
    
    private final String content;
    
    public Token(Type type, String content) {
        this.type = type;
        this.content = content;
    }
    
    public String getContent() {
        return content;
    }
    
    @Override
    public String toString() {
        return content;
    }
}
