
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        Scanner scanner;
        scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine().trim());
        for (int i = 0; i < n; i++) {
            String nextLine = scanner.nextLine();
            String[] strings = nextLine.trim().split(" +");
            CaseSelection.dealWithScanner(strings, scanner);
        }
    }
}
