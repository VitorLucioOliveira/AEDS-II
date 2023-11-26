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
        this.cidadeNascimento= "0";
        this.estadoNascimento= "0";
        this.id =0;
        this.peso= 0;
        this.universidade= "0";


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
    Jogador elemento;
    No esq, dir;

    public No(Jogador elemento) {
        this(elemento, null, null);
    }

    public No(Jogador elemento, No esq, No dir) {
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

        // MAIOR e MENOR NO

    public Jogador getMaior() {
        Jogador tmp = null;

        if (raiz != null) {
            No i;
            for (i = raiz; i.dir != null; i = i.dir)
                ;
            tmp = i.elemento;
        }

        return tmp;
    }

    public Jogador getMenor() {
        Jogador tmp = null;

        if (raiz != null) {
            No i;
            for (i = raiz; i.esq != null; i = i.esq)
                ;
            tmp = i.elemento;
        }

        return tmp;
    }

    // ALTURA
    public int getAltura() {
        return getAltura(raiz, 0);
    }

    public int getAltura(No i, int altura) {
        if (i == null) {
            altura--;
        } else {
            int alturaEsq = getAltura(i.esq, altura + 1);
            int alturaDir = getAltura(i.dir, altura + 1);
            altura = (alturaEsq > alturaDir) ? alturaEsq : alturaDir;
        }
        return altura;
    }


    // INSERIR
    public void inserir(Jogador jogador) throws Exception {
        raiz = inserir(jogador, raiz);
    }

    private No inserir(Jogador jogador, No i) throws Exception {
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
        } else if (jogador.getNome().compareTo(i.elemento.getNome()) < 0) {// 2º
            i.esq = inserir(jogador, i.esq);
        } else if (jogador.getNome().compareTo(i.elemento.getNome()) > 0) {// 3º
            i.dir = inserir(jogador, i.dir);
        } else {
            throw new Exception("Erro ao inserir");
        }
        return i;
    }

    // REMOVER

    public void remover(Jogador jogador) throws Exception {
        raiz = remover(jogador, raiz);
    }

    private No remover(Jogador jogador, No i) throws Exception {
        /*
         * 1º: se não tiver raiz, ERRO;
         * 2º: vai para o No do nome a ser removido
         * 3º: no No a ser removido, ele vai ser substituido por um dos filhos;
         * 4º: se ele tiver 2 filhos, substitui pelo maior da esquerda da Arvore;
         * Obs: tambem pode ser feito com o menor a direita;
         * 
         */

        // NEVEGAR PELA ARVORE
        if (i == null)// 1º
        {
            throw new Exception("Erro ao Remover");
        } else if (jogador.getNome().compareTo(i.elemento.getNome()) < 0) {// 2º
            i.esq = remover(jogador, i.esq);
        } else if (jogador.getNome().compareTo(i.elemento.getNome()) > 0) {// 2º
            i.dir = remover(jogador, i.dir);
        }

        // CASOS QUE SÓ TEM 1 OU NENHUM FILHO
        else if (i.esq == null) {// 3º
            i = i.dir;
        } else if (i.dir == null) {// 3º
            i = i.esq;
        }

        // CASO QUE TEM 2 FILHOS
        else {
            i.esq = maiorEsq(i, i.esq);
        }
        return i;
    }

    private No maiorEsq(No i, No j) {

        /*
         * 1º: Se o elemento do j(i.esq) for nulo, j é o maior da AB esquerda;
         * 2º: Se o elemento do j(i.esq) não for nulo, j não é o maior da AB esquerda;
         * Obs: O processo se repete indo o mais a direita da esquerda possivel;
         */

        if (j.dir == null) {// 1
            i.elemento = j.elemento;
            j = j.esq;
        } else {// 2
            j.dir = maiorEsq(i, j.dir);
        }

        return j;
    }

    // PESQUISAR
    public void pesquisar(Jogador jogador) {
        System.out.print(" raiz");
        if( pesquisar(jogador, raiz))
        {
           
            System.out.println(" SIM");
        }
        else {
            System.out.println(" NAO");
        }
        }
    

    private boolean pesquisar(Jogador jogador, No i) {
        /*
         * 1º: Verifica se a arvore não esta vazia;
         * 2º: Se o nome do elemento for o mesmo da Raiz, returna true;
         * 3º: Se o nome do elemento for menor da Raiz, vai pra esquerda da arvore;
         * 4º: Se o nome do elemento for meaior da Raiz, vai pra direita da arvore;
         * OBS: se não achar nada, returna false;
         */
        boolean resp = false;

        if (i == null) {// 1º
            resp = false;
        } else if (jogador.getNome().compareTo(i.elemento.getNome()) == 0)// 2º
        {
            resp = true;
        } else if (jogador.getNome().compareTo(i.elemento.getNome()) < 0) // 3º
        {   System.out.print(" esq");
            resp = pesquisar(jogador, i.esq);
        } else// 4º
        {   
            System.out.print(" dir");
            resp = pesquisar(jogador, i.dir);
        }

        return resp;
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
            System.out.println(i.elemento.getNome());// 1º
            mostrar(i.esq);// 2º
            mostrar(i.dir);// 3°
        }
    }



}

// -------------------Main---------------------------//
public class ArvoreBinaria {
    public static void main(String[] args) throws Exception {
        
        //variaveis e objetos
        Scanner scan = new Scanner(System.in);
        File tabela = new File("/tmp/players.csv");
        ArvoreBi arvore = new ArvoreBi();


        /*Leitura do jogador e incerção na Arvore */
        String pedido = scan.nextLine();
        while (!pedido.equalsIgnoreCase("FIM")) {
            Jogador player = new Jogador(pedido, tabela); // cria um jogador novo e leva o pedido para o construtor
            arvore.inserir(player);// inserir o jogador na Arvore
            pedido = scan.nextLine();
        }

        pedido = scan.nextLine();
        while (!pedido.equalsIgnoreCase("FIM")) {
            Jogador player = new Jogador(pedido);
            System.out.print(player.getNome());
            arvore.pesquisar(player);
           
            pedido = scan.nextLine();
        }
        scan.close();
    }
}
