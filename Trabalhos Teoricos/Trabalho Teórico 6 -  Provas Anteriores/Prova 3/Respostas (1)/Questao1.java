import java.util.Scanner;

/*
 * Neste codigo de arvore Trie de lista flexivel, Cada Celula  tem um No, esse No aponta pra outra celula

    Celula('c')
    |
    No -> Celula('a')
        |
        No -> Celula('s')
           |
           No -> Celula('a', isWord = true)
               |
               No (vazio) 

 * E o Celula.prox, Representa Celulas do mesmo nivel
    
             c
             |
             a
            / \
           r   s
          |   |
         r   a (isWord = true)
         |
         o (isWord = true)
 
*/

//----------------Classe Celula-----------------------//
class Celula {

    public char elemento;
    public Celula prox;
    public No no;

    // contrutores
    public Celula() {
        this.elemento = 0;
        this.prox = null;
        this.no = null;
    }

    public Celula(char elemento) {
        this.elemento = elemento;
        this.prox = null;
        this.no = new No(elemento);
    }
}

// -----------------Classe NO----------------------------//
class No {

    char elemento;
    boolean isWord;
    private Celula primeiro, ultimo;

    // construtores
    public No() {
        this(' ');
    }

    public No(char elemento) {
        this.elemento = elemento;
        this.primeiro = this.ultimo = new Celula();
        this.isWord = false;
    }

    // inserir NO
    public No inserir(char x) {
        ultimo.prox = new Celula(x);
        ultimo = ultimo.prox;

        return ultimo.no;
    }

    // pesquisar NO
    public No pesquisar(char x) {
        No resp = null;

        // retorna o No com essa letra
        for (Celula i = primeiro.prox; i != null; i = i.prox) {
            if (i.elemento == x) {
                resp = i.no;
                i = ultimo;
            }
        }

        return resp;
    }

    // Identificar fim da palavra
    public void setWord(char x) {
        // Busca nas celulas da lista, a letra que mandamos e transforma ela em TRUE
        for (Celula i = primeiro.prox; i != null; i = i.prox) {
            if (i.elemento == x) {
                i.no.isWord = true;
                i = ultimo;

            }
        }
    }

    public No[] getFilho() {
        int n = 0;//contador

        //conta o numero das celulas Lista
        for (Celula i = primeiro.prox; i != null; i = i.prox, n++);

        //cria um vetor de No com o tamanho da Lista
        No[] vet = new No[n];

        //reseta o contador
        n = 0;

        // agora a gente percorre de novo a lista, inserindo os No(letras) no vetor
        for (Celula i = primeiro.prox; i != null; i = i.prox) {
            vet[n++] = i.no;
        }

        return vet;
    }

}

// -----------------------CLASSE Trie---------------------------//
class Trie {

    No raiz;

    // CONSTRUTOR
    public Trie() {
        raiz = new No();
    }

    // INSERIR
    public void inserir(String s) {
        inserir(s, raiz, 0);
    }

    private void inserir(String palavra, No no, int i) {

        // pesquisa se já tem uma celula da lista com a mesma letra
       
        No filho = no.pesquisar(palavra.charAt(i));

        // se o filho for NULL vamos criar uma celula dele no NO
        if (filho == null) {
            // criamos a celula com a letra da palavra e agora o Filho não é null mais
            
            filho = no.inserir(palavra.charAt(i));

            // Se a palavra acabou isWord é TRUE, indicando que é uma palavra completa
            if (i == palavra.length() - 1) {
                no.setWord(palavra.charAt(i));
            }
            // Se não, continuamos inserindo, com agora o FILHO, sendo o No e incrementa a letra (i+1)
            else {
                inserir(palavra, filho, i + 1);
            }
        }

        // Se o Filho não for null, e ele não for a palavra completa, continuamos inserindo a partir dele
        else if (no.isWord == false && i < palavra.length() - 1) {
            inserir(palavra, filho, i+1);
        }

    }

    public void mostrar() {
        mostrar("", raiz);
    }

    public void mostrar(String s, No no) {

        /*
         * Recursivamente, eu vou ir em cada Celula do No raiz;
         * Desta forma eu tenho acesso a todas as palavras formadas em cadeia;
         * E vou ir em cada uma ate encontrar uma palavra, assim printo ela;
         * O processo se repete ate que não tenham mais palavras formadas no array de No
        */
        
        //se o No for o fim da palavra printamos a String mais a letra do No
        if (no.isWord == true) {
            if( s.length()>=5 && no.elemento=='a' || no.elemento=='e' || no.elemento=='i' || no.elemento=='o' || no.elemento=='u' ){
            System.out.println("Palavra: " + (s + no.elemento));}
        } 
        else {
            //Vou pegar todos os filhos desse No, e fazer a chamada com eles, sendo assim vou printar TODOS 
            No[] filho = no.getFilho();
            for (int i = 0; i < filho.length; i++) {
                mostrar(s + no.elemento, filho[i]);
            }
        }
    }

}

public class Questao1 {

    public static void main(String[] args) {

        Trie arvore = new Trie();
        Scanner scan = new Scanner(System.in);
        String pedido;

       do{
            pedido = scan.nextLine();
            arvore.inserir(pedido);

            
       }while (!pedido.equals("fim"));
            
        arvore.mostrar();

        scan.close();
    }

}
