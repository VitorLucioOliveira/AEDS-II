


// ------------------------CLASSE CelulaMatriz----------------------------------//
class CelulaMatriz {

    public int elemento; // Elemento inserido na CelulaMatriz.
    public CelulaMatriz sup, inf, esq, dir; // Aponta a CelulaMatriz prox.

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

class Matrix {
    private CelulaMatriz inicio;
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
        CelulaMatriz i = matrix.inicio;
        CelulaMatriz j = matrix.inicio;
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
