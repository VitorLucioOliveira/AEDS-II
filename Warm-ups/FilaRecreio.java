import java.util.Scanner;

public class FilaRecreio {

    static void swap(int x, int y, int[] array) {
        int aux = array[x];
        array[x] = array[y];
        array[y] = aux;
    }

    static int AlunosMantidos(int[] notas) {
        /*
         * 1. Criar variavel contendo o numero de alunos que sofreram trocas
         * 2. Percorrer o array ate a penultima posição
         * 3. Comparar os elementos e salvando a posição contendo o maior valor
         * 4. Se a posição com o maior valor for diferente de i, troca os valores e incrementa a troca em 2, pois trocou 2 valores
         * 5. Retorna o numero de alunos que não sofreram trocas, sendo elas o tamanho de array menos os alunos que foram trocados
        */

        // 1.
        int alunosTrocados = 0;
        
        // 2.
        for (int i = 0; i < notas.length - 1; i++) {

            int PosicaoMaiorValor = i;

            //3.
            for (int j = i + 1; j < notas.length; j++) {

                if (notas[j] > notas[PosicaoMaiorValor]) {
                    PosicaoMaiorValor = j;
                }
            }
            
            // 4.
            if (PosicaoMaiorValor != i) {
                swap(PosicaoMaiorValor, i, notas);
                alunosTrocados += 2;
            }

        }

        // 5.
        return notas.length - alunosTrocados;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        /*
         * variável alunos: número de alunos na fila
         * variável notas: vetor que armazena a nota dos alunos
         */

        int casos = scan.nextInt();

        for (int i = 0; i < casos; i++) {
            int alunos = scan.nextInt();
            int[] notas = new int[alunos];

            // preenche o vetor notas
            for (int j = 0; j < alunos; j++) {
                notas[j] = scan.nextInt();
            }

            System.out.println(AlunosMantidos(notas));

        }

        scan.close();

    }

}