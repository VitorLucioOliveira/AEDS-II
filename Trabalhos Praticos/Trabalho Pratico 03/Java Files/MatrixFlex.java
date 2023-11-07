import java.util.Scanner;

// ------------------------CLASSE CelulaMatriz----------------------------------//
class CelulaMatriz {

    public int elemento; // Elemento inserido na CelulaMatriz.
    public CelulaMatriz sup, inf, esq, dir;

    public CelulaMatriz() {
        this(0);
    }

    public CelulaMatriz(int elemento) {
        this.elemento = elemento;
        this.inf = this.sup = this.dir = this.esq = null;
    }

    public CelulaMatriz(int elemento, CelulaMatriz inf, CelulaMatriz sup, CelulaMatriz esq, CelulaMatriz dir) {
        this.elemento = elemento;
        this.inf = inf;
        this.sup = sup;
        this.esq = esq;
        this.dir = dir;
    }
}

// ------------------------CLASSE MATRIZ FLEXIVEL--------------------------//
class Matriz {
    private CelulaMatriz inicio;
    private int linha, coluna;

    // Construtores
    public Matriz() {
        this(3, 3);
    }

    public Matriz(int l, int c) {
        this.linha = l;
        this.coluna = c;

        inicializarCelulas();
    }

    public void inicializarCelulas() {

        inicio = new CelulaMatriz();
        CelulaMatriz tmp = inicio;// salva a celula inicial

        /*------ Criação da Primeira Linha ---------
         * 1º passo: percorrer todas as colunas da primeira linha (pulando o inicio que
         * ja ta criada)
         * 2º passo: a cada passagem, vamos criar uma nova celula a direita
         * 3º passo: a nova celula criada vai apontar para a celula anterior
         * 4º passo: avançar para a proxima celula, ate o numero maximo de colunas
         * Resultado: criamos e ligamos os elementos da primeira linha uns com os outros, para usarmos de base para fazer com o resto
        */
        for (int i = 1; i < coluna; i++) {// 1º
            tmp.dir = new CelulaMatriz();// 2º
            tmp.dir.esq = tmp;// 3º
            tmp = tmp.dir;// 4º
        }
        /*
         * --------Criação das Restantes------------
         * 1º passo: voltar para a celula inicial
         * 2º passo: salva a celula temporaria (i), e percorrer todas as linhas (pulando
         * a primeiro pq ja foi feita e avança a cada passagem)
         * 3º passo: a cada passagem, vamos criar uma nova celula abaixo (i.inf)
         * 4º passo: a nova celula criada vai apontar para a celula acima
         * 5º passo: salvamos a celula inferior criada(j=i.inf)
         * 6º passo: Iniciamos um FOR, para agora ligar as colunas da linha e as linhas
         * 7º passo: a cada passagem, vamos avançar celula i para a direita (i=i.dir)
         * 8º passo: vamos criar uma nova celula abaibo do i(i.inf) e ligar ela com
         * i(i.inf.sup=i)
         * 9º passo: vamos ligar a nova celula(i.inf) com a celula j (i.inf.esq=j) e o j
         * com ele (j.dir=i.in)
         * 10º passo: fazer isso ate o numero maximo de colunas
         * Resultado: ligaremos todos os elementos da matriz, usando a primeira como
         * referencia
         */

        tmp = inicio;// 1º
        for (int l = 1; l < linha; l++, tmp = tmp.inf) {// FOR externo // 2º

            CelulaMatriz i = tmp;

            i.inf = new CelulaMatriz();// 3º

            i.inf.sup = i;// 4º

            CelulaMatriz j = i.inf;// 5º

            for (int c = 1; c < coluna; c++) {// FOR interno // 6º

                i = i.dir;// 7º

                i.inf = new CelulaMatriz();// 8º
                i.inf.sup = i;// 8º

                i.inf.esq = j;// 9º
                j.dir = i.inf;// 9º

                j = j.dir;
            }

        }

    }

    public void setElementoMatriz(int linha, int coluna, int elemento) {

        /*
         * 1º passo: criar uma celula com o valor de inicio para navegar na matriz
         * 2º passo: usar "int coluna", para levar a ATUAL ate a coluna desejada
         * 3º passo: usar "int linha", para levar a ATUAL ate a linha desejada
         * 4º passo: inserir o valor desejado no elemento de ATUAL
         * Resultado: navegamos pela matriz, usando o mesmo tipo de localização de
         * batalha naval
         */

        CelulaMatriz atual = inicio;

        for (int i = 0; i < coluna; i++) {
            atual = atual.dir;
        }
        for (int i = 0; i < linha; i++) {
            atual = atual.inf;
        }

        atual.elemento = elemento;
    }

    public int getElementoMatriz(int linha, int coluna) {

        /*
         * 1º passo: criar uma celular com o valor de inicio para navegar na matriz
         * 2º passo: usar "int coluna", para levar a ATUAL ate a coluna desejada
         * 3º passo: usar "int linha", para levar a ATUAL ate a linha desejada
         * 4º passo: retornar o valor achado no elemento de ATUAL
         * Resultado: navegamos pela matriz, usando o mesmo tipo de localização de
         * batalha naval
         */

        CelulaMatriz atual = inicio;// 1º

        for (int i = 0; i < coluna; i++) {// 2º
            atual = atual.dir;
        }
        for (int i = 0; i < linha; i++) {// 3º
            atual = atual.inf;
        }

        return atual.elemento;// 4º

    }

    public void mostrarDiagonalMatriz() {
        /*
         * 1º: Essa é simples mas dificil
         * 2º: Só fazer o print dos elementos da diagonal
         * 3º: Use o raciocinio dos iguais a seguir:
         * - [0 1 2 3]
         * 0 | x o o o |
         * 
         * 1 | o x o o |
         * 
         * 2 | o o x o |
         * 
         * 3 | o o o x |
         * -
         */
        for (int i = 0; i < this.linha; i++) {
            System.out.print(getElementoMatriz(i, i) + " ");
        }
    }

    public void mostrarDiagonal2Matriz() {
        /*
         * 1º: Essa é simples mas dificil
         * 2º: Só fazer o print dos elementos da diagonal
         * 3º: Use o raciocinio do MAIS e MENOS a seguir(aumentando a linha e diminuindo
         * a coluna):
         * - [0 1 2 3]
         * 0 | o o o x |
         * 
         * 1 | o o x o |
         * 
         * 2 | o x o o |
         * 
         * 3 | x o o o |
         * -
         */
        for (int i = 0; i < this.linha; i++) {
            System.out.print(getElementoMatriz(i, (this.coluna - 1 - i)) + " ");
        }
    }

    public void mostrarMatriz() {
        for (int i = 0; i < this.linha; i++) {

            for (int j = 0; j < this.coluna; j++) {

                System.out.print(getElementoMatriz(i, j) + " ");
            }
            System.out.println();
        }
    }

    public Matriz somaMatrizes(Matriz matriz) {

        /*
         * 1º passo: varificar se as matrizes tem o mesmo tamnaho(não da pra somar
         * tamanhos diferentes)
         * 2º passo: criar a matriz que contera os resultados das somas das posições
         * 3º passo: percorrer cada coluna de cada linha(basico boy)
         * 4º passo: armazenar o resultado da soma dos elemento dessa posição
         * 5º passo: inserir esse elemento no array vazio dos resultados
         * 6º retornar a matriz das somas
         * Resultado: percorremos cada celula das matrizes e somamos o elemento da
         * posição x das duas
         */

        Matriz somados = null;

        if (this.linha == matriz.linha && this.coluna == matriz.coluna) {// 1º
            somados = new Matriz(this.linha, this.coluna);// 2º

            for (int i = 0; i < this.linha; i++) {
                for (int j = 0; j < this.coluna; j++) {// 3º

                    int elemento = this.getElementoMatriz(i, j) + matriz.getElementoMatriz(i, j);// 4º
                    somados.setElementoMatriz(i, j, elemento);// 5º

                }
            }
        } else {
            System.out.println("Matrizes Desiguais");
        }

        return somados;// 6º
    }

    public Matriz multiplicaMatrizes(Matriz matriz) {
        /*
         * 1º passo: varificar se as matrizes tem o mesmo tamnaho(não da pra multiplicar
         * tamanhos diferentes)
         * 2º passo: criar a matriz que contera os resultados das multiplicações das
         * posições
         * 3º passo: percorrer cada coluna de cada linha(basico boy)
         * 4º passo: armazenar o resultado da multiplicar dos elemento dessa posição
         * 5º passo: inserir esse elemento no array vazio dos resultados
         * 6º retornar a matriz das multiplicações
         * Resultado: percorremos cada celula das matrizes e multiplicamos o elemento da
         * posição x das duas
         */

        Matriz multiplicados = null;

        if (this.linha == matriz.linha && this.coluna == matriz.coluna) {// 1º
            multiplicados = new Matriz(this.linha, this.coluna);// 2º

            for (int i = 0; i < this.linha; i++) {
                for (int j = 0; j < this.coluna; j++) {// 3º

                    int elemento = this.getElementoMatriz(i, j) * matriz.getElementoMatriz(i, j);// 4º
                    multiplicados.setElementoMatriz(i, j, elemento);// 5º

                }
            }
        } else {
            System.out.println("Matrizes Desiguais");
        }

        return multiplicados;// 6º
    }

}

// ------------------------MAIN----------------------------------//
public class MatrixFlex {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        int numCasos = scan.nextInt() * 2;

        Matriz[] casos = new Matriz[numCasos];


        //preencher array de matrizes
        for (int k = 0; k < numCasos; k++) {

            // ler matriz
            int linhas = scan.nextInt();
            int colunas = scan.nextInt();
            Matriz m = new Matriz(linhas, colunas);
    
            scan.nextLine();
            for(int i = 0; i < linhas; i++){
                String entrada = scan.nextLine();
                String[] valores = entrada.split(" ");
                for(int j = 0; j < colunas; j++){
                    m.setElementoMatriz(i, j, Integer.parseInt(valores[j]));
                }
            }

            // colocar matriz no meu array de matrizes
            casos[k] = m;
        }

        //MOSTRAR MATRIZES E OPERAÇÕES
        for (int i = 0; i < numCasos; i=i+2) {
            
        
        casos[i].mostrarDiagonalMatriz();
        System.out.println();
        casos[i].mostrarDiagonal2Matriz();
        
        Matriz soma = casos[i].somaMatrizes(casos[i+1]);
        System.out.println();
        soma.mostrarMatriz();
        

        Matriz multp = casos[i].multiplicaMatrizes(casos[i+1]);
        multp.mostrarMatriz();


        }


        scan.close();
    }

}