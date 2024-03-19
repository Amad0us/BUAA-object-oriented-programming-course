package factor;

import java.math.BigInteger;

public class Variable implements Factor {
    private BigInteger index = BigInteger.ONE;
    
    public void changeIndex(BigInteger index) {
        this.index = index;
    }
    
    private final char varType;
    private final boolean negative;
    
    public boolean getNegative() {
        return negative;
    }
    
    public Variable(char varType, boolean negative) {
        this.varType = varType;
        this.negative = negative;
    }
    
    public BigInteger getIndex() {
        return index;
    }
    
    @Override
    public String toString() {
        String pre;
        String post;
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
        return pre + varType + post;
    }
}
