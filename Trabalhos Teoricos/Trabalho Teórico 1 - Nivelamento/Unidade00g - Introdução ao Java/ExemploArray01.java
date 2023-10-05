/* Faça um programa que leia os elementos de um array de tamanho n e
mostre a posição do menor elemento do array */

import java.util.Scanner;

public class ExemploArray01 {
    public static void main(String[] args) {
         int n=5, menor=100, maior=0;
        int[] elemento = new int[n];
        

        Scanner scan = new Scanner( System.in);


        for(int i=0; i<n; i++)
        {
            
            System.out.print("Elemento "+ (i+1) + " : " );
            elemento[i]=scan.nextInt();
            if(elemento[i]<menor){ menor= elemento[i]; }
            if(elemento[i]>maior){maior = elemento[i];}
        }

        System.out.println("Menor elemento: "+ menor);
        System.out.println("Maior elemento: "+ maior);
        scan.close();
    }
    
}
