package factor;

import parser.Lexer;
import parser.Parser;
import parser.Term;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Expr implements Factor {
    private BigInteger index = BigInteger.ONE;
    
    public BigInteger getIndex() {
        return index;
    }
    
    public void changeIndex(BigInteger index) {
        this.index = index;
    }
    
    private final boolean negative;
    
    public boolean getNegative() {
        return negative;
    }
    
    private String exprString;
    private final ArrayList<Term> terms;
    
    public Expr(boolean negative) {
        this.negative = negative;
        this.terms = new ArrayList<>();
    }
    
    public ArrayList<Term> getTerms() {
        return terms;
    }
    
    private final TreeMap<String, HashMap<BigInteger, BigInteger>> exprMap = new TreeMap<>();
    
    public TreeMap<String, HashMap<BigInteger, BigInteger>> getMap() {
        return exprMap;
    }
    
    public void addTerm(Term term) {
        this.terms.add(term);
    }
    
    public void postProcess() {
        for (Term term : getTerms()) {
            term.postProcess();
            for (String termString : term.getMap().keySet()) {
                HashMap<BigInteger, BigInteger> termHashMap = term.getMap().get(termString);
                for (BigInteger index : termHashMap.keySet()) {
                    BigInteger coefficient = termHashMap.get(index);
                    addToMap(termString, index, coefficient);
                }
            }
        }
        dealWithExprString();
    }
    
    public void addToMap(String s, BigInteger i, BigInteger c) {
        HashMap<BigInteger, BigInteger> tempHash = new HashMap<>();
        tempHash.put(i, c);
        if (!getMap().containsKey(s)) {
            getMap().put(s, tempHash);
        } else {
            if (getMap().get(s).containsKey(i)) {
                BigInteger num = getMap().get(s).get(i);
                num = num.add(c);
                getMap().get(s).replace(i, num);
            } else {
                getMap().get(s).put(i, c);
            }
        }
    }
    
    public void dealWithExprString() {
        StringBuilder sb = new StringBuilder();
        if (exprMap.isEmpty()) {
            HashMap<BigInteger, BigInteger> tempHash = new HashMap<>();
            tempHash.put(BigInteger.ZERO, BigInteger.ZERO);
            exprMap.put("", tempHash);
        }
        for (String string : exprMap.keySet()) {
            HashMap<BigInteger, BigInteger> stringNum = exprMap.get(string);
            for (BigInteger index : stringNum.keySet()) {
                BigInteger coefficient = stringNum.get(index);
                if (coefficient.equals(BigInteger.ZERO)) {
                    continue;
                }
                String indexString;
                if (index.equals(BigInteger.ZERO)) {
                    indexString = "";
                } else if (index.equals(BigInteger.ONE)) {
                    indexString = "x";
                } else {
                    indexString = "x^" + index;
                }
                StringBuilder latter = new StringBuilder();
                latter.append(indexString);
                String expString = getExpString(string);
                if (latter.length() > 0 && !expString.isEmpty()) {
                    latter.append("*").append(expString);
                } else {
                    latter.append(expString);
                }
                if (sb.length() == 0) {
                    dealWithString(sb, coefficient, latter);
                } else {
                    if (coefficient.compareTo(BigInteger.ZERO) > 0) {
                        sb.append("+");
                    }
                    dealWithString(sb, coefficient, latter);
                }
            }
        }
        if (sb.length() == 0) {
            sb.append("0");
        }
        exprString = sb.toString();
    }
    
    private static String getExpString(String tempString) {
        String expString;
        if (!tempString.isEmpty()) {
            Lexer templexer = new Lexer(tempString);
            Parser tempParser = new Parser(templexer);
            Expr tempExpr = tempParser.parseExpr(false);
            tempExpr.postProcess();
            String string = tempExpr.getMap().firstKey();
            if (tempExpr.getMap().size() > 1) {
                expString = "exp((" + tempExpr + "))";
            } else if (tempExpr.getMap().get(string).size() > 1) {
                expString = "exp((" + tempExpr + "))";
            } else {
                String temp = tempExpr.toString();
                if (judgeBracket(temp)) {
                    expString = "exp(" + tempExpr + ")";
                } else {
                    HashMap<BigInteger, BigInteger> tempHash = tempExpr.getMap().get(string);
                    Map.Entry<BigInteger, BigInteger> uni = tempHash.entrySet().iterator().next();
                    BigInteger coefficient = uni.getValue();
                    if (coefficient.compareTo(BigInteger.ONE) > 0) {
                        String[] strings = temp.split("\\*");
                        String preString = strings[0];
                        temp = temp.replace(preString + "*", "");
                        if (judgeBracket(temp)) {
                            expString = "exp(" + temp + ")^" + coefficient;
                        } else {
                            expString = "exp((" + temp + "))^" + coefficient;
                        }
                    } else {
                        expString = "exp((" + temp + "))";
                    }
                }
            }
        } else {
            expString = "";
        }
        return expString;
    }
    
    public static boolean judgeBracket(String string) {
        char p = string.charAt(0);
        boolean first = p == 'e';
        boolean next = !string.contains("e");
        boolean last = (!string.contains("*")) && (!string.contains("^"));
        return (first || (next && last));
    }
    
    public String toString() {
        return exprString;
    }
    
    private void dealWithString(StringBuilder sb, BigInteger coefficient, StringBuilder latter) {
        StringBuilder tempBuild = new StringBuilder();
        if (latter.length() == 0) {
            tempBuild.append(coefficient);
            sb.append(tempBuild);
        } else {
            if (coefficient.equals(BigInteger.ONE)) {
                tempBuild.append(latter);
                sb.append(tempBuild);
            } else if (coefficient.equals(BigInteger.valueOf(-1))) {
                tempBuild.append("-").append(latter);
                sb.append(tempBuild);
            } else {
                tempBuild.append(coefficient).append("*").append(latter);
                sb.append(tempBuild);
            }
        }
    }
}