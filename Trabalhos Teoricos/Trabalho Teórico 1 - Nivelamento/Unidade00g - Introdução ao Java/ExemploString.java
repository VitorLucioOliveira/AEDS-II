import java.util.Scanner;

public class ExemploString {
    public static void main(String[] args) {
        

        Scanner scan = new Scanner(System.in);


        String string;
        char letra;
        int c=0;


        System.out.print("Me fala algo: ");
        string = scan.nextLine();

        System.out.print("Me fala uma letra: ");
        letra = scan.nextLine().charAt(0);

        for(int i=0; i<string.length(); i++)
        {
            if(string.charAt(i)== letra){c++;}
        }



        System.out.print("Essa letra aparece: "+ c +"x");





        scan.close();
    }
}
