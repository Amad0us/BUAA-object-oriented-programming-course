package factor;

import java.math.BigInteger;

public class Number implements Factor {
    private final BigInteger num;
    private BigInteger index = BigInteger.ONE;
    
    public void changeIndex(BigInteger index) {
        this.index = index;
    }
    
    public BigInteger getNum() {
        BigInteger result = BigInteger.ONE;
        for (BigInteger i = BigInteger.ZERO; i.compareTo(index) < 0; i = i.add(BigInteger.ONE)) {
            result = result.multiply(num);
        }
        return result;
    }
    
    public Number(BigInteger num) {
        this.num = num;
    }
    
    @Override
    public String toString() {
        BigInteger result = BigInteger.ONE;
        for (BigInteger i = BigInteger.ZERO; i.compareTo(index) < 0; i = i.add(BigInteger.ONE)) {
            result = result.multiply(num);
        }
        return result.toString();
    }
}
