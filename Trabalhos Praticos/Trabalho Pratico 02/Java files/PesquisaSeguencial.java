import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

//Class Jogador

class Jogador {

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

  public Jogador(String pedido, File tabela) {
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

  public Jogador() {
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
public class PesquisaSeguencial {

  // contador de comparações
  static int contador = 0;


  // Criar log
  public static void criarLog(Long duration) {
    String fileName = "/tmp/810862_sequencial.txt";

    try {
      File logFile = new File(fileName);
      FileWriter writer = new FileWriter(logFile);
      writer.write("810862" + "  " + duration + "  " + contador);
      writer.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }


  // pesquisa sequencial
  public static boolean pesquisa(int i, String pedido, Jogador[] jocker, Integer[] ID) {

    boolean achou = false;

    for (int j = 0; j < i; j++) {

      contador++;
      if (pedido.equals(jocker[j].getNome())) {
        if (ID[j] == jocker[j].getId()) {
          achou = true;
          contador++;
          j = i;
        }
      }

    }

    return achou;
  }

  public static void main(String[] args) {

    long startTime = System.nanoTime();
    ;// tempo de inicio de execução

    Scanner scan = new Scanner(System.in);
    File tabela = new File("/tmp/players.csv");

    // variaveis e arrays inicializados
    Jogador[] jocker = new Jogador[1000];
    String pedido;
    Integer[] ID = new Integer[1000];
    int numeroJogador = 0;

    do {
      pedido = scan.nextLine();

      if (!pedido.equalsIgnoreCase("FIM")) {
        Jogador player = new Jogador(pedido, tabela); // cria um jogador novo e leva o pedido para o construtor
        jocker[numeroJogador] = player;
        ID[numeroJogador] = Integer.parseInt(pedido);
        numeroJogador++;
      }
    } while (!pedido.equalsIgnoreCase("FIM"));


    String pedido2; // Segunda parte

    do {
        pedido2 = scan.nextLine();
        System.out.println((pesquisa(numeroJogador, pedido2, jocker, ID)) ? "SIM" : "NAO");

    } while (!pedido2.equalsIgnoreCase("FIM"));
    scan.close();


    long endTime = System.nanoTime();// tempo de fim de execução
    criarLog(endTime - startTime);
  }
}
