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
public class MergeJava {

    // contador de comparações
    static int contador = 0, moves = 0;
    static long duration;

    // Criar log
public static void criarLog() {
        String fileName = "/tmp/810862_mergesort.txt";

        try {
            File logFile = new File(fileName);
            FileWriter writer = new FileWriter(logFile);
            writer.write("810862" + "\t" + contador + "\t" + moves + "\t" + duration);
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

 public static void doMerge(Jogadores[] jogadores, Jogadores[] esquerda, Jogadores[] direita) {

        int Tesquerda = esquerda.length;
        int Tdireita = direita.length;

        int i = 0, j = 0, k = 0;

        while (i < Tesquerda && j < Tdireita) {
            contador++;
            if ((esquerda[i].getUniversidade()).compareTo(direita[j].getUniversidade()) < 0) {
                jogadores[k] = esquerda[i];
                i++;
                contador++;
               
            } 
            else if ((esquerda[i].getUniversidade()).compareTo(direita[j].getUniversidade()) > 0) {
                jogadores[k] = direita[j];
                j++;
                contador++;
                
            } 
            else {
                // Se as universidades forem iguais, compare pelo nome
                if ((esquerda[i].getNome()).compareTo(direita[j].getNome()) < 0) {
                    jogadores[k] = esquerda[i];
                    i++;
                    contador++;

                } else {
                    jogadores[k] = direita[j];
                    j++;
                    contador++;

                }
            }
            k++;
        }

        // se sobrar elementos só botar eles no array
        while (i < Tesquerda) {
            jogadores[k] = esquerda[i];
            contador++;
            i++;
            k++;

        }
        while (j < Tdireita) {
            jogadores[k] = direita[j];
            contador++;
            j++;
            k++;
            
        }

    }

    // ordenar os Jogadoreses
    public static void MergeSort(Jogadores[] jogadores) {
        // tempo de inicio de execução
        long startTime = System.nanoTime();
        int fim = jogadores.length;
        

        contador++;
        if (fim <= 1) {
            return;
        } // parada da recursão

        int mid = fim / 2;// meio do array
        Jogadores[] esquerda = new Jogadores[mid];// array da esquerda
        Jogadores[] direita = new Jogadores[fim - mid];// array da direita

        for (int i = 0; i < mid; i++) {// copia os elementos para o array da esquerda
            contador++;
            esquerda[i] = jogadores[i];
            
        }
        for (int i = mid; i < fim; i++) {// copia os elementos para o array da direita
            contador++;
            direita[i - mid] = jogadores[i];
            
        }

        MergeSort(esquerda);
        MergeSort(direita);


        doMerge(jogadores, esquerda, direita);

        long endTime = System.nanoTime();// tempo de fim de execução
        duration = (endTime - startTime);// tempo de execução

    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        File tabela = new File("/tmp/players.csv");

        // variaveis e arrays inicializados
        Jogadores[] jocker = new Jogadores[1000];
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
        
        Jogadores[] newJogador = new Jogadores[numeroJogadores];

        for (int i = 0; i < numeroJogadores; i++) {
            newJogador[i] = jocker[i];
        }
        // Segunda parte - ordenar os Jogadoreses

        MergeSort(newJogador);

        // Printar os Jogadoreses ordenados
        for (int i = 0; i < numeroJogadores; i++) {
            System.out.println( newJogador[i].dados());
        }

        criarLog();
    }
}
