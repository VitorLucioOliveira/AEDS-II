/**
 * Questao1
 */
public class Questao1 {

    public void mostrar()
    {
        mostrar("", this.raiz);
    }

    public void mostrar(String string, No i)
    {
        if(i.folha==true)
        {
            if(string.length()<=5 && i.elemento == 'a')
           System.out.println( (string + i.elemento));
        }
        else{
             No[] filhos = i.getFilhos();

             for(int i = 0; i<filhos.length; i++)
             {
                mostrar((string + i.elemento), filhos[i]);
             }
        }
    }

}