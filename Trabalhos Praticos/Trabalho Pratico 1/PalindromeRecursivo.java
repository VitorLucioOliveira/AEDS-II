import java.util.Scanner;

public class PalindromeRecursivo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str;

        do {
            str = scanner.nextLine();
            if (!str.equals("FIM")) {
                System.out.println(ehPalin(str, 0) ? "SIM" : "NAO");
            }
        } while (!str.equals("FIM"));

        scanner.close();
    }

    static boolean ehPalin(String str, int index) {// saber se é palindrome
        char[] copy = str.toCharArray();// copia da string
       
       
        if(index<str.length()) {
            if (ehSpec(str.charAt(index))) {
                copy[index] = str.charAt(str.length() - 1 - index);
            }
            ehPalin(str, index +1);
        }

        
        return new String(copy).equals(str);
    }

    static boolean ehSpec(char c) {
        return (Character.isLetterOrDigit(c) || c == ' ' || c == '-');
    }
}