package parser;

import java.util.ArrayList;
import java.util.HashMap;

public class FuncMap {
    private static final HashMap<String, ArrayList<String>> funcMap = new HashMap<>();
    
    public static HashMap<String, ArrayList<String>> getFuncMap() {
        return funcMap;
    }
    
    public FuncMap() {
    
    }
    
    public static void constructFuncMap(ArrayList<String> selfDefinedFunc) {
        for (String func : selfDefinedFunc) {
            ArrayList<String> tempList = new ArrayList<>();
            String[] strings = func.split("=");
            String funcName = func.charAt(0) + "";
            int mark1 = func.indexOf("(");
            int mark2 = func.indexOf(")");
            for (int i = mark1 + 1; i < mark2; i++) {
                if (func.charAt(i) != ',') {
                    tempList.add(func.charAt(i) + "");
                }
            }
            Lexer tempLexer = new Lexer(strings[1]);
            tempList.add(tempLexer.getString());
            funcMap.put(funcName, tempList);
        }
    }
}

