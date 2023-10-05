import java.util.Scanner;

public class Fibonacci {
    public static void main(String[] args) {

        Scanner scan= new Scanner(System.in);

        System.out.print("Me fala o Termo de fibonacci: ");
        int fi  = scan.nextInt();
        System.out.print(fibo(fi-1));

        scan.close();

    }

     public static int fibo(int fi)
    {
        int res;
        if(fi==1 || fi==0){res=1;}
        
        
        else {res= fibo(fi-1)+fibo(fi-2);}
        
        
        return res;

    }


}
