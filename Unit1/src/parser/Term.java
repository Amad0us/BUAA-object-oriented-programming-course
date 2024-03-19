package parser;

import factor.Factor;
import factor.Expr;
import factor.Exponential;
import factor.Variable;
import factor.Number;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class Term {
    private final TreeMap<String, HashMap<BigInteger, BigInteger>> termMap = new TreeMap<>();
    private TreeMap<String, HashMap<BigInteger, BigInteger>> newMap = new TreeMap<>();
    
    public TreeMap<String, HashMap<BigInteger, BigInteger>> getMap() {
        return termMap;
    }
    
    private final ArrayList<Factor> factors;
    
    public Term() {
        this.factors = new ArrayList<>();
    }
    
    public void addFactor(Factor factor) {
        this.factors.add(factor);
    }
    
    public boolean less(BigInteger i, BigInteger index) {
        return i.compareTo(index) < 0;
    }
    
    public void postProcess() {
        for (Factor factor : factors) {
            if (factor instanceof Expr) {
                ((Expr) factor).postProcess();
                if (((Expr) factor).getNegative()) {
                    reverseMap();
                }
                if (((Expr) factor).getIndex().equals(BigInteger.ZERO)) {
                    dealWithNum(BigInteger.ONE);
                    continue;
                }
                BigInteger index = ((Expr) factor).getIndex();
                for (BigInteger i = BigInteger.ZERO; less(i, index); i = i.add(BigInteger.ONE)) {
                    dealWithExpr(((Expr) factor).getMap());
                }
            } else if (factor instanceof Exponential) {
                if (((Exponential) factor).getNegative()) {
                    reverseMap();
                }
                ((Exponential) factor).postProcess();
                dealWithExp(((Exponential) factor).getInternal());
            } else if (factor instanceof Number) {
                dealWithNum(((Number) factor).getNum());
            } else if (factor instanceof Variable) {
                if (((Variable) factor).getNegative()) {
                    reverseMap();
                }
                dealWithVar(((Variable) factor).getIndex());
            }
        }
    }
    
    public void reverseMap() {
        dealWithNum(BigInteger.ONE.multiply(BigInteger.valueOf(-1)));
    }
    
    public void dealWithExpr(TreeMap<String, HashMap<BigInteger, BigInteger>> exprMap) {
        newMap = new TreeMap<>();
        if (getMap().isEmpty()) {
            newMap.putAll(exprMap);
        } else {
            for (String termString : getMap().keySet()) {
                HashMap<BigInteger, BigInteger> termHashMap = getMap().get(termString);
                for (BigInteger index1 : termHashMap.keySet()) {
                    for (String exprString : exprMap.keySet()) {
                        HashMap<BigInteger, BigInteger> exprHashMap = exprMap.get(exprString);
                        for (BigInteger index2 : exprHashMap.keySet()) {
                            BigInteger coefficient1 = termHashMap.get(index1);
                            BigInteger coefficient2 = exprHashMap.get(index2);
                            BigInteger coefficient = coefficient1.multiply(coefficient2);
                            BigInteger index = index1.add(index2);
                            String newString = getNewString(termString, exprString);
                            addToMap(newString, index, coefficient);
                        }
                    }
                }
            }
        }
        getMap().clear();
        getMap().putAll(newMap);
    }
    
    public void dealWithExp(String expString) {
        newMap = new TreeMap<>();
        if (getMap().isEmpty()) {
            addToMap(expString, BigInteger.ZERO, BigInteger.ONE);
        } else {
            for (String termString : getMap().keySet()) {
                HashMap<BigInteger, BigInteger> termHashMap = getMap().get(termString);
                for (BigInteger index : termHashMap.keySet()) {
                    BigInteger coefficient = termHashMap.get(index);
                    String newString = getNewString(termString, expString);
                    addToMap(newString, index, coefficient);
                }
            }
        }
        getMap().clear();
        getMap().putAll(newMap);
    }
    
    public void dealWithNum(BigInteger num) {
        newMap = new TreeMap<>();
        if (getMap().isEmpty()) {
            addToMap("", BigInteger.ZERO, num);
        } else {
            for (String termString : getMap().keySet()) {
                HashMap<BigInteger, BigInteger> termHashMap = getMap().get(termString);
                for (BigInteger index : termHashMap.keySet()) {
                    BigInteger coefficient = termHashMap.get(index).multiply(num);
                    addToMap(termString, index, coefficient);
                }
            }
        }
        getMap().clear();
        getMap().putAll(newMap);
    }
    
    public void dealWithVar(BigInteger varIndex) {
        newMap = new TreeMap<>();
        if (getMap().isEmpty()) {
            addToMap("", varIndex, BigInteger.ONE);
        } else {
            for (String termString : getMap().keySet()) {
                HashMap<BigInteger, BigInteger> termHashMap = getMap().get(termString);
                for (BigInteger index : termHashMap.keySet()) {
                    BigInteger coefficient = termHashMap.get(index);
                    addToMap(termString, index.add(varIndex), coefficient);
                }
            }
        }
        getMap().clear();
        getMap().putAll(newMap);
    }
    
    public static String getNewString(String termString, String expString) {
        String string;
        if (termString.isEmpty() || expString.isEmpty()) {
            string = termString + expString;
        } else {
            string = termString + "+" + expString;
        }
        if (string.isEmpty()) {
            return "";
        }
        Lexer templexer = new Lexer(string);
        Parser tempParser = new Parser(templexer);
        Expr tempExpr = tempParser.parseExpr(false);
        tempExpr.postProcess();
        return tempExpr.toString();
    }
    
    public void addToMap(String s, BigInteger i, BigInteger c) {
        HashMap<BigInteger, BigInteger> tempHash = new HashMap<>();
        tempHash.put(i, c);
        if (!newMap.containsKey(s)) {
            newMap.put(s, tempHash);
        } else {
            if (newMap.get(s).containsKey(i)) {
                BigInteger num = newMap.get(s).get(i);
                num = num.add(c);
                newMap.get(s).replace(i, num);
            } else {
                newMap.get(s).put(i, c);
            }
        }
    }
    
}

