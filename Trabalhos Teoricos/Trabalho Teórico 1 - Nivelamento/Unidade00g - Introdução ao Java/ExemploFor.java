import java.util.Scanner;

public class ExemploFor {
    public static void main(String[] args) {
        
        int t=5;
        int[] notas = new int[t];
        int media=0;

        Scanner scan = new Scanner( System.in);


        for(int i=0; i<t; i++)
        {
            System.out.print("Nota aluno "+ (i+1) + " : " );
            notas[i]=scan.nextInt();
            media+=notas[i];
        }

        System.out.println("Media: "+ ( (double) media/t));
        scan.close();
    }
}
