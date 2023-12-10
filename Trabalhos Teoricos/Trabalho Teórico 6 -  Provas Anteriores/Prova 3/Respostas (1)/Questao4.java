public class Questao4 {

}

// --------------------O(log n)--------------------//
class No {

    String elemento;
    No esq, dir;
    int nivel;

    public No(String string) {
        this.elemento = string;
        this.dir = this.esq = null;
        this.nivel = 1;
    }

}

class AVL {
    No raiz;

    public void inserir(String s) {
        raiz = inserir(s, raiz);
    }

    private No inserir(String s, No no) {
        if (no == null) {
            no = new No(s);
        } else if (s.compareTo(no.elemento) < 0) {
            no.esq = inserir(s, no.esq);
        } else if (s.compareTo(no.elemento) > 0) {
            no.dir = inserir(s, no.dir);
        }

        return balancear(no);
    }

    private No balancear(No no) {
        return null;
    }

}

// --------------------0(1)--------------------//
class Celula {

    public String elemento; // Elemento inserido na celula.
    public Celula prox; // Aponta a celula prox.

    public Celula(String elemento) {
        this.elemento = elemento;
        this.prox = null;
    }
}

class Lista {

    Celula primeira, ultima;

    public void inserirFIM(String s) {
        ultima.prox = new Celula(s);
        ultima = ultima.prox;
    }

}

// --------------------0(n)--------------------//
class Celula2 {

    public String elemento; // Elemento inserido na celula.
    public Celula prox; // Aponta a celula prox.

    public Celula2(String elemento) {
        this.elemento = elemento;
        this.prox = null;
    }
}

class Lista2 {

    Celula primeira, ultima;

    public void inserirPOS(String s, int pos)
    {
        int tamanho = 0;

        for(Celula i = primeira.prox; i!=null; i=i.prox)
        {
            tamanho++;
        }

        Celula tmp= new Celula(s);
        
        //Inserir inicio
        if(pos==0)
        {
           tmp.prox= primeira.prox;
           primeira.prox = tmp;
           tmp=null;
        }
        //Inserir Fim
        else if(pos==tamanho)
        {
            ultima.prox= tmp;
            ultima = ultima.prox;
            tmp=null;
        }
        //inserir POS
        else{
        
            Celula j = primeira;
        
            for(int i = 0; i<pos; i++){ j=j.prox;}
        
            tmp.prox=j.prox;
        
            j.prox=tmp;
        
            tmp=j=null;
        }


       
    }

}