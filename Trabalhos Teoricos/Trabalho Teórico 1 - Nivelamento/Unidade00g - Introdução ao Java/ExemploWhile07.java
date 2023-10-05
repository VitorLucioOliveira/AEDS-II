import java.util.Scanner;




public class ExemploWhile07 {
    


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o valor de n: ");
        int n = scanner.nextInt();

        int result = fibonacci(n);

        System.out.println("O " + n + "º termo da sequência de Fibonacci é: " + result);

        scanner.close();
    }

    public static int fibonacci(int n) {
        if (n <= 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            int termoAnterior = 0;
            int termoAtual = 1;
            int resultado = 0;

            for (int i = 2; i <= n; i++) {
                resultado = termoAnterior + termoAtual;
                termoAnterior = termoAtual;
                termoAtual = resultado;
            }

            return resultado;
        }
    }
}


