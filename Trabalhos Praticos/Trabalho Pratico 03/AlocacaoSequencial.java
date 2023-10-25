import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// classe jogador
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
        return (

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
                " ##");
    }
}

// classe lista
class Lista {

    private Jogador[] array;
    private int n;

    // construtores

    public Lista() {
        this(6);
    }

    public Lista(int tamanho) {
        array = new Jogador[tamanho];
        n = 0;
    }

    // funções de inserção

    public void inserirInicio(Jogador jogador) throws Exception {

        if (n >= array.length) {
            throw new Exception("Erro ao inserir no inicio");
        } // valida o array

        for (int i = n; i > 0; i--) {// empurra os elementos para frente
            array[i] = array[i - 1];// pegando o elemento da posição anterior e colocando na posição atual
        }

        array[0] = jogador;// insere o elemento na primeira posição
        n++;
    }

    public void inserirFim(Jogador jogador) throws Exception {
        if (n >= array.length) {
            throw new Exception("Erro ao inserir no fim");
        } // valida o array

        array[n] = jogador;// insere o elemento na ultima posição
        n++;

    }

    public void inserir(Jogador jogador, int pos) throws Exception {

        if (n >= array.length || pos < 0 || pos > n) {
            throw new Exception("Erro ao inserir!");
        } // valida o array e a posição

        for (int i = n; i > pos; i--) {// empurra os elementos para frente
            array[i] = array[i - 1];// pegando o elemento da posição anterior e colocando na posição atual ate achar
                                    // a posição que quer inserir
        }

        array[pos] = jogador;// insere o elemento na posição desejada
        n++;
    }

    // funções de remoção

    public Jogador removerInicio() throws Exception {
        if (n == 0) {
            throw new Exception("Erro ao remover no inicio");
        } // valida o array

        Jogador removido = array[0];// guarda o elemento removido

        for (int i = 0; i < n; i++) {// empurra os elementos para trás
            array[i] = array[i + 1];// pegando o elemento da posição posterior e colocando na posição atual
        }

        n--;
        return removido;
    }

    public Jogador removerFim() throws Exception {
        if (n == 0) {
            throw new Exception("Erro ao remover no fim");
        } // valida o array

        return array[--n];// vai retornar o array excluido e depois diminuir o numero de elementos
    }

    public Jogador remover(int pos) throws Exception {
        if (n == 0 || pos < 0 || pos >= n) {
            throw new Exception("Erro ao remover");
        } // valida o array e a posição

        Jogador removido = array[pos];// guarda o elemento removido

        for (int i = pos; i < n; i++) {// empurra os elementos para trás
            array[i] = array[i + 1];// pegando o elemento da posição posterior e colocando na posição atual
                                    // dessa forma o elemento da posição que queremos remover vai ser substituido
        }

        n--;
        return removido;
    }

    // funções de mostrar
    public void mostrar() {

        for (int i = 0; i < n; i++) {
            System.out.println("[" + (i) + "]" + array[i].dados());
        }
        ;
    }

  

}

// MAIN
public class AlocacaoSequencial {

    // Realiza os comandos pedidos 
    static public void doComando(String[] comando, Lista lista, File tabela) throws Exception
    {

        //inserir
        if(comando[0].equalsIgnoreCase("II")){ 
            Jogador player = new Jogador(comando[1], tabela);
            lista.inserirInicio(player);
        }
        
        if(comando[0].equalsIgnoreCase("IF")){ 
            Jogador player = new Jogador(comando[1], tabela);
            lista.inserirFim(player);
        }

        if(comando[0].equalsIgnoreCase("I*")){ 
            Jogador player = new Jogador(comando[2], tabela);
            lista.inserir(player, Integer.parseInt(comando[1]) );
        }
        
        //remover
        if(comando[0].equalsIgnoreCase("RI")){System.out.println("(R) " + lista.removerInicio().getNome());}
        
        if(comando[0].equalsIgnoreCase("RF")){ System.out.println("(R) " + lista.removerFim().getNome());}

        if(comando[0].equalsIgnoreCase("R*")){ System.out.println("(R) " + lista.remover(Integer.parseInt(comando[1])).getNome());}    

    }







    public static void main(String[] args) throws Exception {
        
        //variaveis e objetos
        Scanner scan = new Scanner(System.in);
        File tabela = new File("/tmp/players.csv");
        Lista lista = new Lista(3921);


        /*Leitura do jogador e incerção na Lista */
        String pedido = scan.nextLine();
        while (!pedido.equalsIgnoreCase("FIM")) {
            Jogador player = new Jogador(pedido, tabela); // cria um jogador novo e leva o pedido para o construtor
            lista.inserirFim(player);// inserir o jogador na lista
            pedido = scan.nextLine();
        }

        /*Leitura dos comandos e tratamento*/
        int pedidoRegistro = scan.nextInt();
        String linha;
        

        for (int i = 0; i < pedidoRegistro; i++) {
            do {
                linha = scan.nextLine();// le os comandos
            } while(linha.equals(""));

            String[] comando =  linha.split(" ");// separa os comandos em um array
            doComando(comando, lista, tabela);
        }

        lista.mostrar();
        

        scan.close();
    }
}