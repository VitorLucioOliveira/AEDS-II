/*Faça um programa que leia um número inteiro N e mostre na tela os N
primeiros números inteiros ímpares.*/
import java.util.Scanner;

public class ExemploWhile04 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Qual o numero de Impares: ");
        int num = scan.nextInt();

        int i=0;
        while(i<=num)
        {
            if (i%2!=0){System.out.println(i);}
            i++;
        }


        scan.close();

    }
}

