Celula* encontrarRepetidos(No* raiz, CelualaMatriz* inicio)
{
    Lista lista = criarLista();


    montarLista(lista, raiz, inicio);

    return lista;
}


void montarLista(Lista* lista, No* raiz, CelulaMatriz* inicio)// 0(n)
{


    if(raiz!=null)
    {

        montarLista(lista, raiz->dir, inicio);

        bool repetido = comprar(raiz.elemento, inicio);

        if(repetido)
        {
            inserirFim(lista, raiz.elemento)
        }

        montarLista(lista, raiz->esq, inicio);

    }
}


bool comparar(int numero, CelulaMatriz* inicio)// 0(m+n)
{
    bool resp = false;
    CelulaMatriz* i= inicio;


    while (i!=null)
    {
        CelulaMatriz* j= i;

        while (j!=null)
        {
            if(j.numero==numero)
            {
                resp=true;
            }
            j=j->prox;
        }
        i=i->inf;
    }
    
    return resp;


}