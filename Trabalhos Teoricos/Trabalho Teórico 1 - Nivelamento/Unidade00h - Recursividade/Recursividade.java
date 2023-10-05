import java.util.Scanner;

public class Recursividade {
    public static void main(String[] args) {
        
        Scanner scan= new Scanner(System.in);


        System.out.print("Me fala numero: ");
        int x  = scan.nextInt();
        System.out.print("Me fala numero: ");
        int y  = scan.nextInt();

        System.out.print(multi(x,y));

        scan.close();
    }

    static int multi(int x, int y)
    {
            int resp=0;


            if(y>0){resp= x+multi(x, y-1);}




            return resp;
    }




}
