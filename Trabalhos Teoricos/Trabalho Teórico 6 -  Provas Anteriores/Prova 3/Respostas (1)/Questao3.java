public class Questao3 {

    
    // HASH's
    private int hashT1(char x) {
        return x % tam1;
    }

    private int hashT2(char x) {
        return x % tam2;
    }

     private int hashT3(char x) {
        return x % tam3;
    }

    private int hashVirtual(char x) {
        return x % 2;
    } 
    private int rehashT3(char x) {
        return ++x%tam3;
    }

    // Pesquisar
    public boolean pesquisar(String palavra) {

        boolean resp = false;
        int pos = hashT1(palavra.charAt(0));

        //PESQUISA T1
        if (palavras[pos].equals(palavra)) {
            resp = true;
        } else {
            //PESQUISA T2
            if (hashVirtual(palavra.charAt(0)) == 0) {
                pos = hashT2(palavra.charAt(1));

                resp = pesquisarT2(T2.raizes[pos], palavra);

            }
            //PESQUISA T3
            else{
                pos = rehashT3(palavra.charAt(1));

             if (T3.palavras[pos].equals(palavra)) {
             resp = true;}
             else{
                resp= pesquisarLista(inicio, palavra);

             }

            }
        }

        return resp;
    }
    //PESQUISAR NA ARVORE
    private boolean pesquisarT2(No i, String palavra)
    {
        boolean resp = false;

        if(i != null)
        {
            if(palavra.charAt(0) == i.letra)
            {
                resp=pesquisarLista(i.inicio,palavra);
            }
            else if(palavra.charAt(0) <= i.letra)
            {
                resp = pesquisarT2(i.esq, palavra);
            }
            else 
            {
                resp = pesquisarT2(i.dir, palavra);
            }
        }

        return resp;
    }

    //PESQUISAR T3 E LISTA DOS NO T2
    private boolean pesquisarLista(Celula i, String palavra)
    {
        boolean resp = false;

        while(i != null && reso==true)
        {
            if(palavra.equals(i.palavra))
            {
                resp=true;
            }
            i.prox;
            
        }

        return resp;
    }

   

 

    private static final char tam2 = 0;
    private static final String[] palavras = null;
    private static final char tam1 = 0;
    private static final char tam3 = 0;
}
