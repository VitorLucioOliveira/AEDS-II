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

    public Jogador(String nome) {

        this.nome = nome;
        this.altura = 0;
        this.anoNascimento = 0;
        this.cidadeNascimento = "0";
        this.estadoNascimento = "0";
        this.id = 0;
        this.peso = 0;
        this.universidade = "0";

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

// -----------------------CLASSE NO---------------------------//
class No {
    
    No[] filhos;// alfabeto ASCII Maiusculo + Minusculo + Espaço
    boolean isWord;// palavra completa

    public No() {
        this.filhos = new No[95];
        this.isWord = false;
    }

}

// -----------------------CLASSE Trie---------------------------//
class Trie {

    No raiz;

    // CONSTRUTOR
    public Trie() {
        raiz = new No();
    }

    public Trie(Trie t1, Trie t2) {
        this();
        this.juntar(t1);
        this.juntar(t2);
    }

    // MÉTODO PARA JUNTAR UMA TRIE À TRIE ATUAL
    public void juntar(Trie t) {
        juntar(t.raiz, "");
    }

    private void juntar(No no, String s) {
        if (no.isWord) {
            this.inserir(s);
        }
        for (int i = 0; i < no.filhos.length; i++) {
            if (no.filhos[i] != null) {
                juntar(no.filhos[i], s + (char)(i + 32));
            }
        }
    }

    // INSERIR
    public void inserir(String nome) {
        /*
         * 1º: Salvar o No que estaremos usando para navegar;
         * 2º: Separamos a inserção letra por letra;
         * 2.obs: subtraimos por 'a', para ter o valor da tabela ASCII que vamos inserir;
         * 3º: Se a letra não tiver sido inserida ainda, vamos inserir e depois avançar o No;
         * 4º: Se a letra já tiver sido inserida, simplemente avançamos para ela;
         * 5º: Quando o FOR acabar a palavra vai estar completa, sendo assim deixamos isWord true;
        */

        // 1º
        No atual = this.raiz;

        // 2º
        for (int i = 0; i < nome.length(); i++) {
            char c = nome.charAt(i);
            // 2.obs
            int index = c - 32;

            // 3º
            if (atual.filhos[index] == null) {
                No node = new No();
                atual.filhos[index] = node;

                atual = node;// avançando o No
            }
            // 4º
            else {
                atual = atual.filhos[index];
            }

        }

        // 5º
        atual.isWord = true;
    }


    public void pesquisar(String nome)
    {

        if(pesquisar(nome, raiz))
        {
            System.out.println(nome+" SIM");
        }
        else
        {
            System.out.println(nome+" NAO");
        }
        
    }


    private boolean pesquisar(String nome, No raiz) {
        /*
         * 1º: Salvar o No que estaremos usando para navegar;
         * 2º: Separamos a busca letra por letra;
         * 2.obs: subtraimos por 'a', para ter o valor da tabela ASCII que vamosbuscar;
         * 3º: Se a letra não tiver sido inserida ainda, retornamos false;
         * 4º: Se a letra já tiver sido inserida, simplesmente avançamos para ela;
         * 5º: Quando o FOR acabar a palavra vai estar completa, sendo assim verificamos se isWord é true;
        */

        // 1º
        No atual = raiz;
        boolean resp = false;

        // 2º
        for (int i = 0; i < nome.length(); i++) {
            char c = nome.charAt(i);
            // 2.obs
            int index = c - 32;

            // 3º
            if (atual.filhos[index] == null) {
                resp = false;
            }
            // 4º
            else {
                atual = atual.filhos[index];
            }

        }

        // 5º
        if (atual.isWord) {
            resp = true;
        } else {
            resp = false;
        }

        return resp;
    }

}

// -------------------Main---------------------------//
public class ArvoreTrie {
    public static void main(String[] args) throws Exception {

        // variaveis e objetos
        Scanner scan = new Scanner(System.in);
        File tabela = new File("/tmp/players.csv");
        
        Trie arvore1 = new Trie();
        Trie arvore2 = new Trie();
        

        /* Leitura do jogador e incerção na 1º Arvore */
        String pedido = scan.nextLine();
        while (!pedido.equalsIgnoreCase("FIM")) {
            Jogador player = new Jogador(pedido, tabela); // cria um jogador novo e leva o pedido para o construtor
            arvore1.inserir(player.getNome());// inserir o jogador na Arvore
            pedido = scan.nextLine();
        }

        /* Leitura do jogador e incerção na 2º Arvore */
        pedido = scan.nextLine();
        while (!pedido.equalsIgnoreCase("FIM")) {
            Jogador player = new Jogador(pedido, tabela); // cria um jogador novo e leva o pedido para o construtor
            arvore2.inserir(player.getNome());// inserir o jogador na Arvore
            pedido = scan.nextLine();
        }

        Trie arvoreMerge = new Trie(arvore1, arvore2);//juntar arvores

        /* Pesquisa na arvore junta */
        pedido = scan.nextLine();
        while (!pedido.equalsIgnoreCase("FIM")) {
            arvoreMerge.pesquisar(pedido);
            pedido = scan.nextLine();
        }
        scan.close();
    }
}
