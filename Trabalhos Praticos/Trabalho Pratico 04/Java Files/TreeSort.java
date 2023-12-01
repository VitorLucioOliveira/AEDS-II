import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//-----------------------CLASSE JOGADOR---------------------------//
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

//-----------------------CLASSE CELULA---------------------------//
class Celula {
    
    public Jogador elemento; // Elemento inserido na celula.
    public Celula prox; // Aponta a celula prox.

    public Celula() {
        this(null);
    }

    public Celula(Jogador elemento) {
        this.elemento = elemento;
        this.prox = null;
    }
}
//-----------------------CLASSE LISTA FLEXIVEL---------------------------//
class ListaFlexivel {

    private Celula primeiro;
    private Celula ultimo;

    // construtores

    public ListaFlexivel() {
        primeiro = new Celula();
        ultimo = primeiro;
    }

    // funções de inserção

    public void inserirInicio(Jogador jogador) {

        Celula tmp = new Celula(jogador);

        tmp.prox = primeiro.prox;// tmp prox aponta para o mesmo elemento que o primeiro prox
        primeiro.prox = tmp;// primeiro prox aponta para o elemento tmp agora
        
        /* primeiro-->primeiro.prox-->primeiro.prox.prox */
        /* temp.prox-->primeiro.prox */
        /* primeiro-->temp-->primeiro.prox-->primeiro.prox.prox */
        
        //assim o temp se torna o primeiro elemento da lista/primeiro.prox
        /* primeiro-->primeiro.prox-->primeiro.prox.prox */


        if (primeiro == ultimo) { // se a lista estiver vazia
            ultimo = tmp;
        }
        tmp = null;

    }

    public void inserirFim(Jogador jogador) {

        ultimo.prox = new Celula(jogador);
        ultimo = ultimo.prox;

        /* ultimo--> ultimo.prox=null */
        /* ultimo--> ultimo.prox= Jogador*/
        /* ultimo--> ultimo.prox-->null*/

        // Agora o ultimo prox aponta para nulo, inves de ser nulo, e o ultimo aponta para o ultimo prox
        // se tronando o ultimo elemento da lista

    }

    public void inserir(Jogador jogador, int pos)
    {

        int tamanho = tamanho();

        if(pos<0 || pos>tamanho){System.err.println("Erro ao Inserir na posição");}
        
        else if(pos == 0){inserirInicio(jogador);}// adianta o trabalho já que pos é a primeira posição
        else if(pos == tamanho){inserirFim(jogador);}//adianta o trabalho já que pos é a ultima posição

        else{
            Celula i = primeiro;
            for(int j = 0; j < pos; j++, i = i.prox);// leva o i até a posição desejada

            Celula tmp = new Celula(jogador);// cria uma celula temporaria
            tmp.prox = i.prox;// tmp prox aponta para o mesmo elemento que o i prox
            i.prox = tmp;// i prox aponta para o elemento tmp agora

            tmp=i=null;// limpa as variaveis
        }



    }

    // funções de remoção

    public String removerInicio() {

        if(primeiro == ultimo){System.err.println("Erro ao remover no inicio");}

        Celula tmp = primeiro.prox;// salva o primeiro elemento
        primeiro.prox = tmp.prox;// primeiro agora é o proximo elemento

        Jogador removido = tmp.elemento;// salva o elemento removido

        /*limpa a variaveis */
        tmp.prox = null;
        tmp = null;
        
        return removido.getNome();
    }

    public Jogador removerFim() {
        
        if(primeiro == ultimo){System.err.println("Erro ao remover no fim");}

        Celula i;

        for(i = primeiro; i.prox != ultimo; i = i.prox);// leva o i até a posição anterior ao ultimo

        Jogador removido = ultimo.elemento;// salva o elemento removido
        ultimo = i;// ultimo agora é o i

        /*limpa a variaveis */
        i = null;
        ultimo.prox = null;


        return removido;
    }

    
    // função de mostrar
    public void mostrar() {

        int j=0;// contador para mostrar a posição do elemento
        for (Celula i = primeiro.prox; i != null; i=i.prox, j++)// vai ate o ultimo.prox elemento
        {
            System.out.println("[" + (j) + "]" + i.elemento.dados());
        }
        ;
    }

    // função de tamanho

    public int tamanho () {

        int tamanho = 0;
        for(Celula i = primeiro; i != ultimo; i = i.prox)
        {
            tamanho++;
        }
        
        return tamanho;
    }

}

// -----------------------CLASSE NO---------------------------//
class No {
    String elemento;
    No esq, dir;

    public No(String elemento) {
        this(elemento, null, null);
    }

    public No(String elemento, No esq, No dir) {
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
    }
}

// -----------------------CLASSE ArvoreBinaria---------------------------//
class ArvoreBi {

    No raiz;

    public ArvoreBi() {
        raiz = null;
    }


    // INSERIR
    public void inserir(String jogador) throws Exception {
        raiz = inserir(jogador, raiz);
    }

    private No inserir(String jogador, No i) throws Exception {
        /*
         * 1º: Se o No não tiver elementos, insiro como elemento;
         * 2º: Se o nome do jogador for menor que do i, insere ele na esq;
         * 3º: Se o nome do jogador for maior que do i, insere ele na dir;
         * Obs: isso é feito de forma recursiva ate achar o lugar que ele pertence;
         * Obs: NÃO PODE TER ELEMENTO REPETIDO;
         */
        if (i == null)// 1º
        {
            i = new No(jogador);
        } else if (jogador.compareTo(i.elemento) < 0) {// 2º
            i.esq = inserir(jogador, i.esq);
        } else if (jogador.compareTo(i.elemento) > 0) {// 3º
            i.dir = inserir(jogador, i.dir);
        } else {
            throw new Exception("Erro ao inserir");
        }
        return i;
    }

     

    // MOSTRAR-CAMINHAR
    public void mostrar() {
        mostrar(raiz);
    }

    private void mostrar(No i) {
        /*
         * 1º: printa o nome da celula
         * 2º: vai pra celula mais a esquerda possivel, e recursivamente printa;
         * 3º: vai pra celula mais a direita possivel, e recursivamente printa;
         */

        if (i != null) {
           
            mostrar(i.esq);// 2º
             System.out.println(i.elemento);// 1º
            mostrar(i.dir);// 3°
        }
    }

    public void tree(ListaFlexivel lista) throws Exception {

      int  tamanho = lista.tamanho();

      for(int i=0; i<tamanho; i++)
      {
          
        inserir(lista.removerInicio());
          
      }
        
    }



}

//-----------------------MAIN---------------------------//
public class TreeSort {


    public static void main(String[] args) throws Exception {

        // variaveis e objetos
        Scanner scan = new Scanner(System.in);
        File tabela = new File("/tmp/players.csv");
        ListaFlexivel lista = new ListaFlexivel();

        /* Leitura do jogador e incerção na Lista */
        String pedido = scan.nextLine();
        while (!pedido.equalsIgnoreCase("FIM")) {
            Jogador player = new Jogador(pedido, tabela); // cria um jogador novo e leva o pedido para o construtor
            lista.inserirFim(player);// inserir o jogador na lista
            pedido = scan.nextLine();
        }

        ArvoreBi arvore = new ArvoreBi();

        arvore.tree(lista);

        arvore.mostrar();
    

        
        lista.mostrar();
        scan.close();
    }
}