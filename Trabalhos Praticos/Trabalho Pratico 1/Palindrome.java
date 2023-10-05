import java.util.Scanner;

public class Palindrome {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str;

        do {
            str = scanner.nextLine();
            if (!str.equals("FIM")) {
                System.out.println(ehPalin(str) ? "SIM" : "NAO");
            }
        } while (!str.equals("FIM"));

        scanner.close();
    }

    static boolean ehPalin(String str) {
        char[] copy = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            if (ehSpec(str.charAt(i))) {
                copy[i] = str.charAt(str.length() - 1 - i);
            }
        }
        return new String(copy).equals(str);
    }

    static boolean ehSpec(char c) {
        return (Character.isLetterOrDigit(c) || c == ' ' || c == '-');
    }
}
