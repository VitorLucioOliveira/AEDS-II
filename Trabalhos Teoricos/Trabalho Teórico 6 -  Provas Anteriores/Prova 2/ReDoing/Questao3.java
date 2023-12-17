public class Questao3 {

void removerImpares()//pesquisa em Matriz - 0(m*n)
{
    CelulaMat i =  inicio;


    while (i!=null) {

        CelulaMat j = i;

        while (j!=null) {

            removerImparesLista(j.primeiro);
            
            j=j.prox;
        }
        
        i=i.prox;
    }

}

void removerImparesLista(Celula primeiro)// pesquisa em lista - 0(n)
{
    int pos = 0;

    for(Celula i = primeiro; i!=null; i=i.prox, pos++)
    {
        if((i.numero%2)!=0)
        {
            remover(pos);
        }
    }
}

}
