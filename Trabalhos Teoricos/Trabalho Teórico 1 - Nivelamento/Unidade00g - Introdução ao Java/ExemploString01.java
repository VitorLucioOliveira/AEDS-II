/*Faça um programa para ler uma palavra. Em seguida, mostre o número de
caracteres da mesma e seu número de caracteres maiúsculos
 */

import java.util.Scanner;

public class ExemploString01 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);


        String string;
        int c=0;
        


        System.out.print("Me fala algo: ");
        string = scan.nextLine();

        for(int i =0; i<string.length(); i++)
        {
            if(Character.isUpperCase(string.charAt(i)))
            {
                c++;
            }
        }



        System.out.println("Esse algo tem "+ string.length() + " letras");
        System.out.println("Esse algo tem "+   c + " letras Maiusculas");

        scan.close();
    }
}
