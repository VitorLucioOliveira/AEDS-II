import java.util.Scanner;

public class ExemploArray03 {
    public static void main(String[] args) {

        int N = 5;
        int[] numeros = new int[N];

        Scanner scan = new Scanner(System.in);

        for (int i = 0; i < N; i++) {

            System.out.print("Numero: ");
            numeros[i] = scan.nextInt();

        }

        for (int i = 0; i < N - 1; i++) {

            for (int j = 0; j < N - 1; j++) {

                if (numeros[j] > numeros[j + 1]) {
                    int temp = numeros[j];
                    numeros[j] = numeros[j + 1];
                    numeros[j + 1] = temp;
                }
            }

        }

        for (int i = 0; i < N; i++) {

            System.out.println("Numeros: " + numeros[i]);

        }

        scan.close();
    }
}
