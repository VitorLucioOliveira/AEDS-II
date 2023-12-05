
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

public class Questao1 {
    
}
