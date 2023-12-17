
public class Questao1 {

    


    public int contarPalavra(char primeiro, char ultimo)
    {
        return contarPalavra( this.raiz,primeiro, ultimo);
    }

    private int contarPalavra(No i, char primeiro, char ultimo)
    {
        int contador=0;


        if(i==null)
        {
        System.err.println("Raiz vazia");
        }
       
        else if(i.letra==primeiro)
        {
            contador= contarPalavra(i.raiz, ultimo);
        }

        else if(primeiro<i.letra)
        {
            contador= contarPalavra(i.esq,primeiro, ultimo);
        }

        else if(primeiro>i.letra)
        {
            contador =contarPalavra(i.dir, primeiro, ultimo);
        }

        return contador;

    }

    private int contarPalavra(No2 i, char ultimo)
    {   
        int contador = 0;
        
        if(i!=null)
        {
          contador += contarPalavra(i.esq, ultimo);
          
          if(i.palavra.charAt(i.palavra.length-1)==ultimo)
          {
            contador++;
          }
          contador +=contarPalavra(i.dir, ultimo);
        }

        return contador;
    }





}