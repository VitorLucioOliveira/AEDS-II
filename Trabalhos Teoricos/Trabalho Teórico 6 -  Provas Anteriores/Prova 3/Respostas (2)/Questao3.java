
class Trie{
    
    
    public Trie interceção(Trie a1, Trie a2) {
        
        Trie a3 = new Trie();
        a3.juntar(a1);
        a3.juntar(a2);

        return a3;
    }

    // MÉTODO PARA JUNTAR UMA TRIE À TRIE ATUAL
    public void juntar(Trie t) {
        juntar(t.raiz, "");
    }

    private void juntar(No no, String s) {
        if (no.isWord) {//folha
            this.inserir(s);
        }

        No[] filhos = no.getFilhos();
        for (int i = 0; i < filhos.length; i++) {
            if (no.filhos[i] != null) {
                juntar(filhos[i], s + no.elemento);
            }
        }
    }

}

public class Questao3 {
    
        


    }