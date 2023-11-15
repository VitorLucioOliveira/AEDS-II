import java.util.Scanner;

public class Question02 {

    static void swap(int x, int y, int[] array) {
        int aux = array[x];
        array[x] = array[y];
        array[y] = aux;
    }

    static int Permutacao(int[] numeros) {
        /*
         * 1. Criar variavel contendo o numero que sofreram trocas
         * 2. Percorrer o array ate a penultima posição
         * 3. Comparar os elementos e salvando a posição contendo o maior valor
         * 4. Se a posição com o maior valor for diferente de i, troca os valores e incrementa a troca em 2, pois trocou 2 valores
         * 5. Retorna o numero de trocas
         */

        // 1.
        int trocas = 0;

        // 2.
        for (int i = 0; i < numeros.length - 1; i++) {

            int inicial = i;
            // 3.
            for (int j = i + 1; j < numeros.length; j++) {

                if (numeros[inicial] > numeros[j]) {
                    inicial = j;
                }
            }

            // 4.
            if (inicial != i) {
                swap(inicial, i, numeros);
                trocas++;
            }

        }

        // 5.
        return trocas;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // Inteiro de testes
        int testes = scan.nextInt();

        // cada teste tem um numero maximo de numeros e um array de numeros
        for (int i = 0; i < testes; i++) {
            int numMax = scan.nextInt();
            int[] numeros = new int[numMax];

            // preenche os numeros do array
            for (int j = 0; j < numMax; j++) {
                numeros[j] = scan.nextInt();
            }

            System.out.println(Permutacao(numeros));

        }

        scan.close();

    }

}