import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//Class Jogador

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

    public String dados() { // print todos os dados do jogador
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
public class QuickJava {

    // pesquisa sequencial
    public static void doQuick(Jogadores jogadores[], int esq, int dir) {
        int i = esq, j = dir;
        Jogadores pivo = jogadores[(dir + esq) / 2];
        Jogadores aux;

        while (i <= j) {
            while (jogadores[i].getEstadoNascimento().compareTo(pivo.getEstadoNascimento()) < 0) {
                i++;

            }

            while (jogadores[j].getEstadoNascimento().compareTo(pivo.getEstadoNascimento()) > 0) {
                j--;

            }

            if (i <= j) {

                aux = jogadores[i];
                jogadores[i] = jogadores[j];
                jogadores[j] = aux;
                i++;
                j--;

            }

        }

        if (esq < j) {
            doQuick(jogadores, esq, j);
        }

        if (i < dir) {
            doQuick(jogadores, i, dir);
        }
    }

    // funcao para ordenar os jogadores com base na ordem crescente de peso
    public static void Quick(Jogadores jogadores[], int inicio, int fim) {

        doQuick(jogadores, inicio, fim - 1);

        for (int i = 0; i < fim; i++) {
            for (int j = i + 1; j < fim; j++) {

                if (jogadores[i].getEstadoNascimento().compareTo(jogadores[j].getEstadoNascimento()) == 0) {
                    if (jogadores[i].getNome().compareTo(jogadores[j].getNome()) > 0) {
                        Jogadores aux = jogadores[i];
                        jogadores[i] = jogadores[j];
                        jogadores[j] = aux;

                    }
                }
            }

        }
    }

    public static void main(String[] args) {

        ;// tempo de inicio de execução

        Scanner scan = new Scanner(System.in);
        File tabela = new File("/tmp/players.csv");

        // variaveis e arrays inicializados
        Jogadores[] jocker = new Jogadores[500];
        String pedido;
        int numeroJogadores = 0;

        do {
            pedido = scan.nextLine();

            if (!pedido.equalsIgnoreCase("FIM")) {
                Jogadores player = new Jogadores(pedido, tabela); // cria um jogadores novo e leva o pedido para o
                                                                  // construtor
                jocker[numeroJogadores] = player; // ARMAZENA O JOGADOR NO ARRAY
                numeroJogadores++;
            }
        } while (!pedido.equalsIgnoreCase("FIM"));
        scan.close();

        // Segunda parte - ordenar os jogadores
        Jogadores[] newJogador = new Jogadores[numeroJogadores];

        
        for (int i = 0; i < numeroJogadores; i++) {
            newJogador[i] = jocker[i];}


        Quick(newJogador, 0, numeroJogadores);
       
       
        int k = 10;
        Jogadores[] min = new Jogadores[k];
       
        for (int i = 0; i < k; i++) {
            min[i] = newJogador[i];}

        // Printar os jogadores ordenados
      for (int i = 0; i < min.length; i++) {
            System.out.println(min[i].dados());
        }

    }
    
}
