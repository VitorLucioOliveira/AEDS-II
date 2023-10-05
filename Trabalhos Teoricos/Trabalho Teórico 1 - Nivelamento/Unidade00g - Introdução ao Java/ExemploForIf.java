import java.util.Scanner;

public class ExemploForIf {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int media = 0, t=5, c=0;
        int[] notas = new int[t];
        

        for (int i = 0; i < t; i++) {
            System.out.print("Nota aluno " + (i + 1) + " : ");
            notas[i] = scan.nextInt();
            if(notas[i]>=80) {media += notas[i]; c++;}
        }

        System.out.println("Media dos Alunos acima de 80: "+ ( (double) media/c) +"%");

        scan.close();
    }
}
