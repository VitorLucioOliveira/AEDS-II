/*Faça um programa que leia um número inteiro N e mostre na tela os N
primeiros números da sequência 1, 5, 12, 16, 23, 27 34 */

import java.util.Scanner;

public class ExemploWhile05 {
    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);

        int[] array = {1, 5, 12, 16, 23, 27, 34};

        System.out.print("Qual o Numero?:  ");
        int n = scan.nextInt();
        int i=0;

        while (i<n) {
            System.out.println(array[i]);
            i++;
            
        }
        scan.close();
    }
}
