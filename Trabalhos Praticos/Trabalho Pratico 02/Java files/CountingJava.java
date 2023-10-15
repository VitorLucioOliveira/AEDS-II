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
public class CountingJava {

    // contador de comparações
    static int contador = 0, moves = 0;
    static long duration;

    // Criar log
    public static void criarLog() {
        String fileName = "tmp/810862_countingsort.txt";

        try {
            File logFile = new File(fileName);
            FileWriter writer = new FileWriter(logFile);
            writer.write("810862" + "\t" +contador + "\t" + moves + "\t" +  duration);
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void alfabetica(int fim, Jogadores jogadores[]) {

        for (int i = 0; i < fim; i++) { // organiza por ordem alfabetica os Jogadoreses com a mesma altura
            contador++;
            for (int j = i + 1; j < fim; j++) {
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
    }

    // ordenar os Jogadoreses
    public static void countSort(int fim, Jogadores jogadores[]) {
        // tempo de inicio de execução
        long startTime = System.nanoTime();

        int maior = jogadores[0].getAltura();
        Jogadores[] jogadoresC = new Jogadores[fim];

        // achar o maior elemento do array
        for (int i = 0; i < fim; i++) {
            contador++;
            if (jogadores[i].getAltura() > maior) {
                maior = jogadores[i].getAltura();
            }
        }

        // Agora vou só deixar na forma alfabetica

        // jarray auxiliar para contar as ocorrencias dos elementos

        int[] aux = new int[maior + 1];

        // inicialuza o array auxiliar
        for (int i = 0; i <= maior; i++) {
            contador++;
            aux[i] = 0;
        }

        // Exemplo: altura 4 aparece 3 vezes, então aux[4] = 3
        for (int i = 0; i < fim; i++) {
            contador++;
            aux[jogadores[i].getAltura()]++;
        }

        // Modificar o array auxiliar para que possa ser feito a colocação no JogadoresC
        // Eu tambem to confuso mas é isso mesmo
        for (int i = 1; i <= maior; i++) {
            contador++;
            aux[i] += aux[i - 1];
        }

        // AGORA A ORDENAÇÃO COMEÇA
        // Vamo botar os elementos(a partir da ultima repetição dele) na posição ordenada dele no JogadoresC
        // vou pegar a altura do primeiro jogador, depois vou ver em qual posição do
        // auxiliar ele ta, e vou colocar esse jogador no JogadoresC, COM A POSIÇÃO DO AUXILIAR
        for (int i = 0; i < fim; i++) {
            contador++;
            jogadoresC[aux[jogadores[i].getAltura()] - 1] = jogadores[i];
            aux[jogadores[i].getAltura()]--;
            moves++;
        }

        // Agora só vou trocar os elementos do Jogadores pelo JogadoresC, para ordenar
        for (int i = 0; i < fim; i++) {
            contador++;
            jogadores[i] = jogadoresC[i];
            moves++;
        }

        // Vou só colocar as alturas iguais em ordem alfabetica
        alfabetica(fim, jogadores);

        long endTime = System.nanoTime();// tempo de fim de execução
        duration = (endTime - startTime);// tempo de execução

    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        File tabela = new File("tmp/players.csv");

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

        countSort(numeroJogadores, jocker);

        // Printar os Jogadoreses ordenados
        for (int i = 0; i < numeroJogadores; i++) {
            System.out.println(jocker[i].dados());
        }

        criarLog();
    }
}
