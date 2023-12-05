public class Questao1 {// analize de complexidade = O(h)-pesquisa na arvore + O(n)-pesquisa na lista

    public int contarPalavra(char primeiro, char ultimo) throws Exception {

        /*
         *Verifica se a Arvore não é nula
         * Se não for puxa o metodo contarPalavra
        */

        if (raiz == null) {
            throw new Exception("Erro Arvore Vazia");
        }

        return contarPalavra(this.raiz, primeiro, ultimo);
    }


    //pesquisa 0(h)
    private int contarPalavra(No raiz, char primeiro, char ultimo) throws Exception {


        int contador = 0;
        /*
         * Metodo de pesquisa simples em Arvore binaria
         * Vou comparar o primeiro char com o char da raiz
         * Se for menor vou para a esquerda e se for maior pra direita
         * Faço isso ate achar o No com Letra igual ao primeiro char
        */

        if (raiz == null) {
            throw new Exception("Erro Letra não encontrada");
        }

        //Inicio da pesquesa do No
        else if (primeiro < raiz.Letra) {
            contador = contarPalavra(raiz.esq, primeiro, ultimo);
        }

        else if (primeiro > raiz.Letra) {
            contador = contarPalavra(raiz.dir, primeiro, ultimo);
        }
        //Fim da pesquisa do No

        //Achou o No com a Letra igual ao primeiro 
        else if (primeiro == raiz.Letra) {
            contador = contarUltimo(raiz.raiz, ultimo);
        }

        return contador;

    }


    //caminhar 0(n)
    private int contarUltimo(No2 raiz, char ultimo) throws Exception {

        int contador = 0;

        if(raiz != null){
       
         int length = raiz.palavra.length()-1;
        
        //vai na menor raiz da esquerda 
        contador += contarUltimo(raiz.esq, ultimo);
       
        //incrementa 1 se a ultima letra da palavra for igual a ultima letra da palavra
        if(raiz.palavra.charAt(length) == ultimo){contador++;}
        
        //vai a menor raiz da direita
        contador += contarUltimo(raiz.dir, ultimo);
        }

        return contador;
    }

}