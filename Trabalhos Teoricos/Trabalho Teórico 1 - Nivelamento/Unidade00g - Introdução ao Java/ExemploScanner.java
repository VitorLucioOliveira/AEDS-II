import java.util.Scanner;

public class ExemploScanner{



 public static void main(String[] args) {

    Scanner scan = new Scanner(System.in);

    String string;
    int inteiro;
    Double real;

    System.out.print("Me fala uma String: ");
    string= scan.nextLine();

    System.out.print("Me fala um Inteiro: ");
    inteiro = scan.nextInt();

    System.out.print("Me fala um Real: ");
    real = scan.nextDouble();

    System.out.println("---------------------------");

    System.out.print("String: "+string+" Inteiro: "+inteiro+" Real: "+real);
    
    scan.close();

    
}
      











}