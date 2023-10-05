import java.util.Scanner;

public class ExemploIf {
    
    public static Scanner scan = new Scanner(System.in);
   public static void main(String[] args) {
    
    System.out.print("Nota do Aluno: ");
    int nota = scan.nextInt();

    if(nota>=80){System.out.println("Parabens Muito bom");}
    if(nota>=70 && nota<80){System.out.println("Parabens Aprovado");}
    else {System.out.println("Infelizmente Reprovado");}


   }
}
