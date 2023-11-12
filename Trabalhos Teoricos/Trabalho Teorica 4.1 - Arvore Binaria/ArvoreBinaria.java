
public class ArvoreBinaria {

    // ------------Classe No------------------//
    class No {

        int elemento;
        No esq, dir;

        // construtor
        No(int elemento) {
            this.elemento = elemento;
            this.esq = this.dir = null;
        }

        No(int elemento, No esq, No dir) {
            this.elemento = elemento;
            this.esq = esq;
            this.dir = dir;
        }
    }

    // ------------Classe Arvore Binaria------------------//
    class ArvoreBi {
        No raiz;

        // construtor
        ArvoreBi() {
            raiz = null;
        }

        // ------- inserir na Arvore Binaria-------//

        void inserir(int x) throws Exception {
            raiz = inserir(x, raiz);
        }

        No inserir(int x, No i) {

            /*
             * 1. Se o No cabeça (Raiz) for nula insere o elemento nela
             * 2. Se o elemento for menor que o elemento do No inserir no No esq do No(que
             * inicia Nulo)
             * 3. Se o elemento for maior que o elemento do No inserir no No dir do No(que
             * inicia Nulo)
             * 4. Retorna o No raiz que é o No cabeça, mas agora aponta para outros nos.
             */

            if (i == null) {// 1
                i = new No(x);
            }

            else if (i.elemento > x) {// 2

                i.esq = inserir(x, i.esq);
            }

            else if (i.elemento < x) {// 3

                i.dir = inserir(x, i.dir);
            }

            return raiz;
        }
        // ------------Remover da Arvore Binaria------------------//

        void remover(int x) throws Exception {
            raiz = remover(x, raiz);
        }

        No remover(int x, No i) throws Exception {
            if (i == null) {
                throw new Exception("Erro a remover");
            }

            /*
             * 1. Se o elemento for menor que o elemento do No remover no No esq do No
             * cabeça
             * 3. Se o elemento for maior que o elemento do No remover no No dir do No
             * cabeça
             * 4. Ao achar o elemento no Na Arvore, e seu .dir for nulo, eu retorno como
             * i=i.esq
             * 5. Ao achar o elemento no Na Arvore, e seu .esq for nulo, eu retorno como
             * i=i.dir
             */

            // NEVEGAR PELA ARVORE
            else if (x < i.elemento) {// 1
                i.esq = remover(x, i.esq);
            } else if (x > i.elemento) {// 2
                i.dir = remover(x, i.dir);
            }

            // CASOS QUE SÓ TEM 1 OU NENHUM FILHO
            else if (i.dir == null) {// 4
                i = i.esq;
            } else if (i.esq == null) {// 5
                i = i.dir;
            }

            // CASO QUE TEM 2 FILHOS
            else {
                i.esq = maiorEsq(i, i.esq);
            }

            return i;
        }

        No maiorEsq(No i, No j) {

            /*
             * 1. Se o elemento do j(i.esq) for nulo, significa que o j é o maior elemento
             * da arvore a esquerda
             * 2. Se o elemento do j(i.esq) não for nulo, significa que o j não é o maior
             * elemento da arvore a esquerda
             * 3. O processo se repete indo o mais a direita da esquerda possivel.
             */

            if (j.dir == null) {// 1
                i.elemento = j.elemento;
                j = j.esq;
            } else {// 2
                j.dir = maiorEsq(i, j.dir);
            }

            return j;
        }

        // -----------------Maiores e menores elementos da arvore------------------//
        public int getMaior() {
            int resp = -1;

            /*
             * Pesquisa pra achar o maior elemento da arvore
             * Sendo o maior, ele vai ser o no mais a direita
             * Então vou percorrer a arvore ate achar o ultimo elemento da direita
             */

            if (raiz != null) {
                for (No i = raiz; i.dir != null; i = i.dir) {
                    resp = i.elemento;
                }
            }

            return resp;
        }

        public int getMenor() {
            int resp = -1;

            /*
             * Pesquisa pra achar o menor elemento da arvore
             * Sendo o menor, ele vai ser o no mais a esquerda
             * Então vou percorrer a arvore ate achar o ultimo elemento da esquerda
             */

            if (raiz != null) {
                for (No i = raiz; i.esq != null; i = i.esq) {
                    resp = i.elemento;
                }
            }

            return resp;
        }

        // -----------------Pesquisar na arvore------------------//
        boolean pesquisar(int x) {
            return pesquisar(x, raiz);
        }

        boolean pesquisar(int x, No raiz) {
            boolean resp = false;

            // se a raiz for nula significa que não encontrou o elemento
            if (raiz == null) {
                return resp;
            }

            // se o elemento foi igual ao elemento buscado, achou o elemento
            else if (raiz.elemento == x) {
                resp = true;
            }

            // caso o elemento não seja igual ao elemento buscado, vou procurar na esquerda
            // ou direita
            else if (x < raiz.elemento) {
                resp = pesquisar(x, raiz.esq);
            }

            else if (x > raiz.elemento) {
                resp = pesquisar(x, raiz.dir);
            }

            return resp;
        }

        // -----------------Mostrar a arvore------------------//
        void caminharCentral(No i) {
            if (i != null) {
                caminharCentral(i.esq);// vou ir ate o menor numero a esquerda
                System.out.println(i.elemento + "");// vou printa-lo
                caminharCentral(i.dir);// vou ir ate o maior numero a direita
            }

        }

    }
}
