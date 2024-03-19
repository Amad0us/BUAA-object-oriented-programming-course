package factor;

import java.math.BigInteger;

public class Exponential implements Factor {
    private BigInteger index = BigInteger.ONE;
    
    public void changeIndex(BigInteger index) {
        this.index = index;
    }
    
    private final Expr expr;
    private final boolean negative;
    
    public boolean getNegative() {
        return negative;
    }
    
    public Exponential(Expr expr, boolean negative) {
        this.expr = expr;
        this.negative = negative;
    }
    
    public void postProcess() {
        expr.postProcess();
    }
    
    public String getInternal() {
        if (index.compareTo(BigInteger.ZERO) > 0) {
            return index + "*(" + expr + ")";
        } else {
            return "";
        }
    }
    
    public String toString() {
        String post;
        String pre;
        if (negative) {
            pre = "-";
        } else {
            pre = "";
        }
        if (index.compareTo(BigInteger.ONE) != 0) {
            post = "^" + index;
        } else {
            post = "";
        }
        return pre + "exp(" + expr.toString() + ")" + post;
    }
}
