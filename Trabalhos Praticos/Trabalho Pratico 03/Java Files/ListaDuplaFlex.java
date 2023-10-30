
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//---------------------------------CLASSE JOGADOR----------------------------------//
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

// -----------------------------CLASSE CelulaDupla-----------------------------//
class CelulaDupla {

    public Jogador elemento; // Elemento inserido na CelulaDupla.
    public CelulaDupla prox, ant; // Aponta a CelulaDupla prox.

    public CelulaDupla() {
        this(null);
    }

    public CelulaDupla(Jogador elemento) {
        this.elemento = elemento;
        this.prox = this.ant = null;
    }
}

// -----------------------------CLASSE ListaDupla-----------------------------//
class ListaDupla {

    private CelulaDupla primeiro, ultimo;

    public ListaDupla() {
        primeiro = new CelulaDupla();
        ultimo = primeiro;
    }

    // inserção

    public void inserirInicio(Jogador jogador) {
        /*
         * Cria uma celula tmp, e faz com que o priximo dela seja o proximo do primeiro,
         * e o anterior dela seja o primeiro
         */
        /* tornando o primeiro.prox em primeiro.prox.prox */

        /* primeiro--->primeiro.prox */
        /* primeiro--->tmp--->primeiro.prox */

        CelulaDupla tmp = new CelulaDupla(jogador);

        tmp.prox = primeiro.prox;
        tmp.ant = primeiro;

        primeiro.prox = tmp;

        if (primeiro == ultimo) {
            ultimo = tmp;
        } else {
            tmp.prox.ant = tmp;
        }

        // desapontando as variaveis
        tmp = null;

    }

    public void inserirFim(Jogador jogador) {

        /*
         * Cria uma celula no ultimo.prox, que antes era nulo, depois aponta o
         * ultimo.prox.ant, para o ultimo, ligando elas e depois substitui o ultimo
         
         * ultimo--->null 

         * ultimo--->ultimo.prox--->null 

         * ultimo.prox.ant=ultimo<---ultimo.prox---->null 

         * ultimo.ant--->ultimo(ultimo.prox)---->ultimo--->null
        */

        ultimo.prox = new CelulaDupla(jogador);
        ultimo.prox.ant = ultimo;
        ultimo = ultimo.prox;
    }

    public void inserir(Jogador jogador, int pos) {
        int tamanho = tamanho();

        if (pos < 0 || pos > tamanho) {
            System.out.println("Erro ao remover (posicao  invalida!");
        } else if (pos == 0) {
            inserirInicio(jogador);
        } else if (pos == tamanho) {
            inserirFim(jogador);
        } else {
            // caminhar ate a posicao anterior a insercao
            CelulaDupla i = primeiro;
            for (int j = 0; j < pos; j++, i = i.prox)
                ;

            CelulaDupla tmp = new CelulaDupla(jogador);

            tmp.ant = i;// tmp.ant aponta para a ultima celula antes da posição que ela quer inserir

            tmp.prox = i.prox;// tmp.prox aponta para a celula que estava na posição que ela quer inserir

            tmp.ant.prox = tmp.prox.ant = tmp;// liga todas as celulas

            // desapontando as variaveis

            tmp = i = null;
        }
    }

    // remoção

    public Jogador removerInicio() {
        if (primeiro == ultimo) {
            System.out.println("Erro ao remover (inicio)!");
        }

        CelulaDupla tmp = primeiro;
        primeiro = primeiro.prox;

        Jogador resp = primeiro.elemento;

        // desapontando as variaveis
        tmp.prox = primeiro.ant = null;
        tmp = null;

        return resp;
    }

    public Jogador removerFim() {
        if (primeiro == ultimo) {
            System.out.println("Erro ao remover (fim)!");
        }

        Jogador resp = ultimo.elemento;
        ultimo = ultimo.ant;

        // desapontando as variaveis
        ultimo.prox.ant = null;
        ultimo.prox = null;

        return resp;
    }

    public Jogador remover(int pos) {

        int tamanho = tamanho();
        Jogador removido = null;

        if (pos < 0 || pos > tamanho) {
            System.out.println("Erro ao remover (posicao  invalida!");
        }

        if (pos == 0) {
            removerInicio();
        }

        if (pos == tamanho - 1) {
            removerFim();
        }

        else {

            // caminhar ate a posicao da remoção
            CelulaDupla i = primeiro;
            for (int j = 0; j <= pos; j++, i = i.prox)
                ;

            removido = i.elemento;// salvo o elemento que vai ser removido

            i.ant.prox = i.prox;// conecto a celula proxima da celula anterior a ceclula proxima da celula a ser
                                // removida
            i.prox.ant = i.ant;// conecto a celula anterior da celula proxima a celula anterior da celula
                               // removida

            // desapontando as variaveis
            i.ant = i.prox = null;
            i = null;

        }
        return removido;
    }

    // mostrar
    public void mostrar() {

        for (CelulaDupla i = primeiro.prox; i != null; i = i.prox) {
            System.out.println(i.elemento.dados());
        }
    }

    // tamnho
    public int tamanho() {

        int tamanho = 0;
        for (CelulaDupla i = primeiro; i != ultimo; i = i.prox) {
            tamanho++;
        }

        return tamanho;
    }

    // ordenar

  public static CelulaDupla pivo(CelulaDupla esq, CelulaDupla dir) {
        Jogador pivo = dir.elemento;
        CelulaDupla i = esq;

        /*
         * Usando a Celula i = primeiro.prox, vou comparar com a Celula j, que percorre todo o array
         * Se o elemento da Celula j for menor que o pivo, eu troco o elemento da Celula i com o da Celula j
         * Desta forma a Celula i vai percorrer o array e terminar com o ultimo numero menor que o pivo
         * depois eu troco o elemento da Celula i com o da Celula dir, que é o pivo
         */

        for (CelulaDupla j = esq; j != dir; j = j.prox) {
            if (j.elemento.getEstadoNascimento().compareTo(pivo.getEstadoNascimento()) < 0) {
                Jogador tmp = i.elemento;
                i.elemento = j.elemento;
                j.elemento = tmp;
                i = i.prox;
            }
            if (j.elemento.getEstadoNascimento().compareTo(pivo.getEstadoNascimento()) == 0) {
                if (j.elemento.getNome().compareTo(pivo.getNome()) < 0) {
                    Jogador tmp = i.elemento;
                    i.elemento = j.elemento;
                    j.elemento = tmp;
                    i = i.prox;
                }
            }
        }

        Jogador tmp = i.elemento;
        i.elemento = dir.elemento;
        dir.elemento = tmp;

        return i;
    }

    public static void doQuick(CelulaDupla esq, CelulaDupla dir) {
        if (esq != null && dir != null && esq != dir && esq.ant != dir) {
            CelulaDupla pivo = pivo(esq, dir);
            doQuick(esq, pivo.ant);
            doQuick(pivo.prox, dir);
        }
    }

    public void quickSort() {
        doQuick(primeiro.prox, ultimo);
    }

}

// ----------------------------MAIN----------------------------------//
public class ListaDuplaFlex {
    public static void main(String[] args) {

        ;// tempo de inicio de execução

        // variaveis e objetos
        Scanner scan = new Scanner(System.in);
        File tabela = new File("tmp/players.csv");
        ListaDupla lista = new ListaDupla();

        /* Leitura do jogador e incerção na Lista */
        String pedido = scan.nextLine();
        while (!pedido.equalsIgnoreCase("FIM")) {
            Jogador player = new Jogador(pedido, tabela); // cria um jogador novo e leva o pedido para o construtor
            lista.inserirFim(player);// inserir o jogador na lista
            pedido = scan.nextLine();
        }
        scan.close();

        // Segunda parte - ordenar os jogadores com base na ordem crescente de Estado
        lista.quickSort();

        lista.mostrar();

    }

}
