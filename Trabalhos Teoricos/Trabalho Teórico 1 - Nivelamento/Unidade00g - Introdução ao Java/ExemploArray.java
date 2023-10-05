import java.util.Scanner;

public class ExemploArray {
    public static void main(String[] args) {

        int t = 5;
        int[] notas = new int[t];
        String[] nome = new String[t];
        int media = 0;
        double resultado;

        Scanner scan = new Scanner(System.in);

        for (int i = 0; i < t; i++) {
            System.out.print("Nome aluno " + (i + 1) + " : ");
            nome[i] = scan.nextLine();

            
            System.out.print("Nota: ");
            notas[i] = scan.nextInt();

            scan.nextLine();
            
            media += notas[i];
        }

        resultado = (double) media / t;

        System.out.println("Alunos acima da Media: " );
        for (int i = 0; i < t; i++) {
            if (notas[i] > resultado) {
                System.out.println(nome[i] );
            }
        }


        scan.close();
    }

}