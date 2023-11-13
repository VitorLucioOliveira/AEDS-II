
Celula* encontrarRepetidos(No* raiz, CelulaMatriz* inicio)
{
   Lista lista = criarLista();


    
  MontarListaRepetidos(raiz, j, lista);
   
    return lista
}



void MontarListaRepetidos(No* raiz, CelulaMatriz* inicio, Celula* lista) //0(n)
{   
    

    if(raiz != null)
    {
    MontarListaRepetidos(raiz->dir, inicio,  lista );
    
    bool repetido = comparar(raiz.elemento, inicio);

    if(repetido)
    {
        inserirFim(raiz.elemento, lista);
    }
     
    MontarListaRepetidos(raiz->esq, inicio,  lista);

    }

}

bool comparar(int numero, CelulaMatriz* inicio)
{
    bool resp = false;
    CelulaMatriz* i = inicio;

    while(i!=null){

        CelulaMatriz* j = i;
        
        while (j!=null)
        {
            if(j->elemento == numero){resp=true;}
            j=j->prox;
       
        }
        i=i->inf;
    }
   
   return resp;
}
