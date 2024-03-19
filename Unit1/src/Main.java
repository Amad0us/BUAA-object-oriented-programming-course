import parser.Parser;
import parser.FuncMap;
import parser.Lexer;
import factor.Expr;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String temp = scanner.nextLine();
        int funcNum = Integer.parseInt(temp);
        ArrayList<String> selfDefinedFunc = new ArrayList<>();
        for (int i = 0; i < funcNum; i++) {
            String str = scanner.nextLine().replaceAll("\\s","");
            selfDefinedFunc.add(str);
        }
        FuncMap.constructFuncMap(selfDefinedFunc);
        String raw = scanner.nextLine();
        String target = raw.replaceAll(" ", "").replaceAll("\t", "");
        Lexer lexer = new Lexer(target);
        Parser parser = new Parser(lexer);
        Expr expr = parser.parseExpr(false);
        expr.postProcess();
        System.out.println(expr);
    }
}
