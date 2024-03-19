package factor;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.TreeMap;

import parser.Term;
import parser.Lexer;
import parser.Parser;

public class Derivative extends Expr {
    private final Expr expr;
    
    public Derivative(Expr expr, Boolean negative) {
        super(negative);
        this.expr = expr;
    }
    
    @Override
    public void postProcess() {
        expr.postProcess();
        TreeMap<String, HashMap<BigInteger, BigInteger>> tempMap = new TreeMap<>(expr.getMap());
        for (String outString : tempMap.keySet()) {
            //以exp内部的表达式字符串为键
            HashMap<BigInteger, BigInteger> hashMap = tempMap.get(outString);
            for (BigInteger outIndex : hashMap.keySet()) {
                BigInteger outCoefficient = hashMap.get(outIndex);
                if (outCoefficient.equals(BigInteger.ZERO)) {
                    continue;
                }
                if (outString.isEmpty()) {
                    if (outIndex.compareTo(BigInteger.ZERO) > 0) {
                        BigInteger newCoefficient = outCoefficient.multiply(outIndex);
                        BigInteger newIndex = outIndex.subtract(BigInteger.ONE);
                        addToMap(outString, newIndex, newCoefficient);
                    }
                } else {
                    //对exp内部因子求导
                    Lexer lexer = new Lexer(outString);
                    Parser parser = new Parser(lexer);
                    Expr tempExpr = parser.parseExpr(false);
                    Derivative derivative = new Derivative(tempExpr, false);
                    derivative.postProcess();
                    //exp内部因子求导后的表达式map
                    TreeMap<String, HashMap<BigInteger, BigInteger>> expMap = derivative.getMap();
                    if (outIndex.compareTo(BigInteger.ZERO) > 0) {
                        BigInteger newCoefficient = outCoefficient.multiply(outIndex);
                        BigInteger newIndex = outIndex.subtract(BigInteger.ONE);
                        addToMap(outString, newIndex, newCoefficient);
                    }
                    for (String inString : expMap.keySet()) {
                        HashMap<BigInteger, BigInteger> inHashMap = expMap.get(inString);
                        for (BigInteger inIndex : inHashMap.keySet()) {
                            BigInteger inCoefficient = inHashMap.get(inIndex);
                            BigInteger newCoefficient = outCoefficient.multiply(inCoefficient);
                            BigInteger newIndex = inIndex.add(outIndex);
                            String newString = Term.getNewString(inString, outString);
                            addToMap(newString, newIndex, newCoefficient);
                        }
                    }
                }
            }
        }
        dealWithExprString();
    }
}
