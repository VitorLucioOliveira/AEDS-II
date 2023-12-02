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

    public int getMod() {
        return (this.altura % 15);
    }
}

// -----------------------CLASSE NO---------------------------//
class No {
    int elemento;
    No esq, dir;
    No2 outro;

    public No(int elemento) {
        this(elemento, null, null, null);
    }

    public No(int elemento, No esq, No dir, No2 outro) {
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
        this.outro = outro;
    }
}

class No2 {
    Jogador elemento;
    No2 esq, dir;

    public No2(Jogador elemento) {
        this(elemento, null, null);
    }

    public No2(Jogador elemento, No2 esq, No2 dir) {
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
    }
}

// -----------------------CLASSE ArvoreBinaria---------------------------//
class ArvoreBi {

    No raiz;

    public ArvoreBi() throws Exception {
        raiz = null;
        this.inserirMod(7);
        this.inserirMod(3);
        this.inserirMod(11);
        this.inserirMod(1);
        this.inserirMod(5);
        this.inserirMod(9);
        this.inserirMod(13);
        this.inserirMod(0);
        this.inserirMod(2);
        this.inserirMod(4);
        this.inserirMod(6);
        this.inserirMod(8);
        this.inserirMod(10);
        this.inserirMod(12);
        this.inserirMod(14);
    }

    // INSERIRMod mod
    public void inserirMod(int elemento) throws Exception {
        raiz = inserirMod(elemento, raiz);
    }

    private No inserirMod(int elemento, No i) throws Exception {
        if (i == null) {
            i = new No(elemento);
        } else if (elemento < i.elemento) {
            i.esq = inserirMod(elemento, i.esq);
        } else if (elemento > i.elemento) {
            i.dir = inserirMod(elemento, i.dir);
        } else {
            throw new Exception("Erro ao inserirMod MOD");
        }
        return i;
    }

    // INSERIR jogador mod

    public void inserir(Jogador jogador) throws Exception {
        raiz = inserir(jogador, raiz);
    }

    private No inserir(Jogador jogador, No i) throws Exception {

        if (i == null) {
            throw new Exception("Erro ao inserir: JOGADOR invalido!");

        } else if (jogador.getMod() < i.elemento) {
            i.esq = inserir(jogador, i.esq);
        } else if (jogador.getMod() > i.elemento) {// 3º
            i.dir = inserir(jogador, i.dir);
        }
        //
        else {
            i.outro = inserir(jogador, i.outro);
        }
        return i;
    }

    // INSERIR jogador

    private No2 inserir(Jogador jogador, No2 i) throws Exception {
       
        if (i == null) 
        {
            i = new No2(jogador);
        } 
        
        else if (jogador.getNome().compareTo(i.elemento.getNome()) < 0) 
        {
            i.esq = inserir(jogador, i.esq);
        } 
       
        else if (jogador.getNome().compareTo(i.elemento.getNome()) > 0) 
        {
            i.dir = inserir(jogador, i.dir);
        } 
        
        else {
            throw new Exception("Erro ao inserir");
        }

        return i;
    }

    // PESQUISAR
    public boolean mostrar(String nome) throws Exception {

        System.out.print(nome +" raiz");

        return mostrar(nome, raiz);
    }

    private boolean mostrar(String nome, No i) {
        boolean resp = false;

        if (i != null) {

            //Vai em todos os nós2 do nó i e procura o nome
            if (!resp) {
                resp = mostrarSub(nome, i.outro);
            } 
            //Vai para a esquerda
            if (!resp) {
                System.out.print(" esq");
                resp = mostrar(nome, i.esq);
            }
            //va para a direita
            if (!resp) {
                System.out.print(" dir");
                resp = mostrar(nome, i.dir);
            }

        }

        return resp;
    }

    private boolean mostrarSub(String nome, No2 i) {
        boolean resp = false;

        if (i != null) {

            if (nome.equals(i.elemento.getNome())) {
                resp = true;
            } 
            else {
                System.out.print(" ESQ");
                resp = mostrarSub(nome, i.esq);

                if (!resp) {
                    System.out.print(" DIR");
                resp = mostrarSub(nome, i.dir);
                }
            }

        }

        return resp;
    }

}

// -------------------Main---------------------------//
public class ArvoreArvore {

    public static void main(String[] args) throws Exception {

        // variaveis e objetos
        Scanner scan = new Scanner(System.in);
        File tabela = new File("/tmp/players.csv");
        ArvoreBi arvore = new ArvoreBi();

        /* Leitura do jogador e incerção na Arvore */
        String pedido = scan.nextLine();
        while (!pedido.equalsIgnoreCase("FIM")) {
            Jogador player = new Jogador(pedido, tabela); // cria um jogador novo e leva o pedido para o construtor
            arvore.inserir(player);// inserir o jogador na Arvore
            pedido = scan.nextLine();
        }

        pedido = scan.nextLine();
        while (!pedido.equalsIgnoreCase("FIM")) {
            Jogador player = new Jogador(pedido);
           if( arvore.mostrar(player.getNome())){
               System.out.println(" SIM");
           }else{
               System.out.println(" NAO");}

            pedido = scan.nextLine();
        }

        // mostrar(arvore.raiz);
        scan.close();
    }
}
