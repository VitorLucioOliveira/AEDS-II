import java.util.Scanner;

public class ExercicioWhile {

    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        
        
        int[] aluno = new int[5];
        int c=0;
        int soma=0;


        while (c<5) {
            System.out.print("Nota do Aluno: ");
            aluno[c] = scan.nextInt();

            soma +=aluno[c];

            c++;

            
        }
        

        double media = (double) soma/c;

         System.out.println("soma: "+soma);

        System.out.println("Media: "+ media);





        scan.close();





    }
}
