/*Faça um programa que leia N números inteiros e mostre na tela a soma do primeiro e do último, a do segundo e do penúltimo, a do terceiro e do
antepenúltimo, …
 */

import java.util.Scanner;

public class ExemploArray02 {
    public static void main(String[] args) {
        
        int N=5;
        int[] numeros = new int[N];

        Scanner scan = new Scanner(System.in);

        for(int i=0; i<N; i++)
        {
            System.out.print("Numero: ");
            numeros[i]= scan.nextInt();
  
        }

        for(int i=0; i<N; i++)
        {
            System.out.print("Somas: "+(numeros[i] + numeros[N-1-i])+ "  ");
            
  
        }




        scan.close();
    }

    
}
