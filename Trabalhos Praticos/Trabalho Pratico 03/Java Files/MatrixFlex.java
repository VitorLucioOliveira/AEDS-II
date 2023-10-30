


// ------------------------CLASSE CELULA----------------------------------//
class Celula {

    public int elemento; // Elemento inserido na celula.
    public Celula sup, inf, esq, dir; // Aponta a celula prox.

    public Celula() {
        this(0);
    }

    public Celula(int elemento) {
        this.elemento = elemento;
        this.inf = this.sup = this.dir = this.esq = null;
    }

    public Celula(int elemento, Celula inf, Celula sup, Celula esq, Celula dir) {
        this.elemento = elemento;
        this.inf = inf;
        this.sup = sup;
        this.esq = esq;
        this.dir = dir;
    }
}

// ------------------------CLASSE MATRIZ FLEXIVEL--------------------------//

class Matrix {
    private Celula inicio;
    private int linha, coluna;

    public Matrix() {
        this(3, 3);
    }

    public Matrix(int l, int c)
    {
        this.linha = l;
        this.coluna = c;
    }


    public int SomaMatrix(Matrix matrix)
    {
        int soma = 0;
        Celula i = matrix.inicio;
        Celula j = matrix.inicio;
        for (int l = 0; l < matrix.linha; l++)
        {
            for (int c = 0; c < matrix.coluna; c++)
            {
                soma += i.elemento;
                i = i.dir;
            }
            j = j.inf;
            i = j;
        }
        return soma;
    }


}
