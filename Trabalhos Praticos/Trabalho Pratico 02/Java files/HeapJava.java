import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

//Class Jogadores

class Jogadores {

    // instancias
    private int id;
    private String nome;
    private int altura;
    private int peso;
    private String universidade;
    private int anoNascimento;
    private String cidadeNascimento;
    private String estadoNascimento;

    // construtores

    public Jogadores(String pedido, File tabela) {
        try {
            Scanner scan = new Scanner(tabela);

            while (scan.hasNextLine()) {
                String linha = scan.nextLine(); // le a linha
                String[] elementos = linha.split(",", -1); // divide a linha pela virgula e faz um array

                for (int i = 0; i < elementos.length; i++) {
                    if (elementos[i].isEmpty()) {
                        elementos[i] = "nao informado";
                    }
                }

                if (pedido.equals(elementos[0]) && elementos.length == 8) { // olha o id do pedido feito e completa as
                                                                            // informações

                    setId(Integer.parseInt(elementos[0]));
                    setNome(elementos[1]);
                    setAltura(Integer.parseInt(elementos[2]));
                    setPeso(Integer.parseInt(elementos[3]));
                    setUniversidade(elementos[4]);
                    setAnoNascimento(Integer.parseInt(elementos[5]));
                    setCidadeNascimento(elementos[6]);
                    setEstadoNascimento(elementos[7]);
                }
            }

            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();

            System.out.println("arquivo não encontrado");
        }
    }

    public Jogadores() {
    }

    // funções set

    public void setId(int id) {
        this.id = id;
    }

    private void setNome(String nome) {
        this.nome = nome;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public void setUniversidade(String universidade) {
        this.universidade = universidade;
    }

    public void setAnoNascimento(int anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public void setCidadeNascimento(String cidadeNascimento) {
        this.cidadeNascimento = cidadeNascimento;
    }

    public void setEstadoNascimento(String estadoNascimento) {
        this.estadoNascimento = estadoNascimento;
    }

    // funções gets

    public int getId() {
        return this.id;
    }

    public int getAltura() {
        return this.altura;
    }

    public String getNome() {
        return this.nome;
    }

    public int getPeso() {
        return this.peso;
    }

    public String getUniversidade() {
        return this.universidade;
    }

    public int getAnoNascimento() {
        return this.anoNascimento;
    }

    public String getCidadeNascimento() {
        return this.cidadeNascimento;
    }

    public String getEstadoNascimento() {
        return this.estadoNascimento;
    }

    // funções normais

    public String dados() { // print todos os dados do Jogadores
        return ("[" +
                getId() +
                " ## " +
                getNome() +
                " ## " +
                getAltura() +
                " ## " +
                getPeso() +
                " ## " +
                getAnoNascimento() +
                " ## " +
                getUniversidade() +
                " ## " +
                getCidadeNascimento() +
                " ## " +
                getEstadoNascimento() +
                "]");
    }
}

// MAIN
public class HeapJava {

    // contador de comparações
    static int contador = 0, moves = 0;
    static long duration;

    // Criar log
    public static void criarLog() {
        String fileName = "/tmp/810862_heapsort.txt.";

        try {
            File logFile = new File(fileName);
            FileWriter writer = new FileWriter(logFile);
            writer.write("810862" + "\t" + duration + "\t" + contador + "\t" + moves);
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void doHeap(Jogadores jogadores[], int i, int fim) {

        int raiz = i; // salva o vetor inicial
        int esquerda = 2 * i + 1; // salva o vetor da esquerda da arvore
        int direita = 2 * i + 2; // salva o vetor da direita da arvore

        if (esquerda < fim && jogadores[esquerda].getAltura() > jogadores[raiz].getAltura()) { // compara a altura da
                                                                                               // esquerda com a raiz
            contador++;
            raiz = esquerda;// troca a raiz pelo maior valor
        }

        if (direita < fim && jogadores[direita].getAltura() > jogadores[raiz].getAltura()) { // compara a altura da
                                                                                             // direita com a raiz
            contador++;
            raiz = direita;// troca a raiz pelo maior valor
        }

        if (raiz != i) {
            contador++;
            Jogadores aux = jogadores[i];// raiz original
            jogadores[i] = jogadores[raiz];// troca a raiz pelo maior valor encontrado
            jogadores[raiz] = aux;// troca o maior valor encontrado pelo valor original da raiz
            moves++;
            doHeap(jogadores, raiz, fim); // chama a função novamente para ordenar as raizes trocadas da arvore
                                          // subsequentes a raiz original que era i
        }

    }

    // ordenar heap sort
    public static void heapsort(int fim, Jogadores jogadores[]) {

          long startTime = System.nanoTime();
        ;// tempo de inicio de execução

        /*
         * Referente ao FOR a seguir.
         * O FOR, tem a função de tornar as raizes da arvore os meiores elementos.
         * Ou seja, os maiores elementos pai do vetor estão na primeira metada da arvore
         * e são maiores que os filhos.
         */
        for (int i = ((fim / 2) - 1); i >=0; i--) {
            contador++;
            doHeap(jogadores, i, fim);
        }

        /*
         * Referente ao FOR a seguir.
         * O FOR, tem a função de jogar os maiores elementos do vetor para o final.
         * Depois, para garantir a organização, ele chama a função doHeap para ordenar
         * novamente a arvore nova criada.
         */

        for (int i = fim - 1; i >= 0; i--) {
            contador++;
            Jogadores aux = jogadores[0];
            jogadores[0] = jogadores[i];
            jogadores[i] = aux;
            moves++;
            doHeap(jogadores, 0, i);
        }

        for (int i = 0; i < fim; i++) { // organiza por ordem alfabetica os Jogadoreses com a mesma altura
            contador++;
            for (int j = i+1; j < fim; j++) {
                contador++;
                if (jogadores[i].getAltura() == jogadores[j].getAltura()) {
                    contador++;
                    if (jogadores[i].getNome().compareToIgnoreCase(jogadores[j].getNome()) > 0) {
                        contador++;
                        Jogadores aux = jogadores[j];
                        jogadores[j] = jogadores[i];
                        jogadores[i] = aux;
                        moves++;
                    }
                }
            }
        }

         long endTime = System.nanoTime();// tempo de fim de execução
           duration = (endTime - startTime);// tempo de execução

    }

    public static void main(String[] args) {


        Scanner scan = new Scanner(System.in);
        File tabela = new File("/tmp/players.csv");

        // variaveis e arrays inicializados
        Jogadores[] jocker = new Jogadores[3000];
        String pedido;
        int numeroJogadores = 0;

        do {
            pedido = scan.nextLine();

            if (!pedido.equalsIgnoreCase("FIM")) {
                Jogadores player = new Jogadores(pedido, tabela); // cria um Jogadores novo e leva o pedido para o
                                                                  // construtor
                jocker[numeroJogadores] = player; // ARMAZENA O Jogadores NO ARRAY
                numeroJogadores++;
            }
        } while (!pedido.equalsIgnoreCase("FIM"));
        scan.close();

        // Segunda parte - ordenar os Jogadoreses

        heapsort(numeroJogadores, jocker);

        // Printar os Jogadoreses ordenados
        for (int i = 0; i < numeroJogadores; i++) {
            System.out.println(jocker[i].dados());
        }

       
        criarLog();
    }
}
