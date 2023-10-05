/*• Faça um programa que leia um número inteiro N indicando a nota máxima
em uma prova P. Em seguida, leia a nota de 20 alunos (entre 0 e N) e
mostre na tela: (i) a média da turma, (ii) o número de alunos cuja nota foi
menor que a média da Universidade (suponha 60%) e (iii) a quantidade de
alunos com conceito A (mais de 90%). */





import java.util.Scanner;

public class ExemploWhile06 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Qual a nota maxima:  ");
        int n = scan.nextInt();
        int t=5;
        int[] alunos = new int[t];
        int i = 0;
        int soma = 0;
        int c = 0;
        int c2= 0;
        int o=0;
      

        while (i < t) {
            System.out.print("Qual a nota do aluno:  ");

            alunos[i] = scan.nextInt();
            if (alunos[i] <= n || alunos[i] >= 0) {
                soma += alunos[i];
                i++;

            }

        }

        Double media = (double) soma / t;

        System.out.print("Media da turma: " + media +" / ");

        while (o<t) {

            if (alunos[o] < (n * 0.6)) {
                c++;
            }

            if (alunos[o]>(n*0.9)){ c2++;}

            o++;
        }

        System.out.print("Alunos abaixo da media : " + c +" / ");
        System.out.print("Alunos conceito A: " + c2 +" / ");

        scan.close();
    }

}
