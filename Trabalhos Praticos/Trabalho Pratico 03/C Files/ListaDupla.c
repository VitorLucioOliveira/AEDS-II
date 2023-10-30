#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include <time.h>
#include <stdlib.h>
#define MAXTAM 500
#define MAX_ATTRIBUTES 8
#define MAX_LEN 100
//-------------------------------CLASSE JOGADOR-------------------------//
typedef struct Jogador
{
    int id;
    char nome[50];
    int altura;
    int peso;
    char universidade[100];
    int anoNascimento;
    char cidadeNascimento[50];
    char estadoNascimento[50];
} Jogador;

// metodos para dividir a string
//-----------------------CLASSE SPLIT-------------------------//
typedef struct Split
{
    char linha[MAX_ATTRIBUTES][MAX_LEN];
} Split;

void split(char linha[], char substrings[8][100])
{
    int qtSubstrings = 0;
    int cS = 0; // posicao da substring atual
    int c = 0;  // posicao da linha
    // inicializacao da matriz substrings para fins de controle
    for (int i = 0; i < 8; i++)
    {
        for (int j = 0; j < 100; j++)
        {
            substrings[i][j] = '\0';
        }
    }
    // loop que repete ate que a string linha seja totalmente percorrida
    while (linha[c] != '\0')
    {
        if (linha[c] != ',')
        {
            while (linha[c] != ',' && linha[c] != '\0')
            {
                if (linha[c] == '\n')
                    c++; // ignorar as quebras de linha
                else
                {
                    substrings[qtSubstrings][cS] = linha[c];
                    c++;
                    cS++;
                }
            }
            cS = 0;
            qtSubstrings++;
        }
        else
        {
            // condicional para caso o campo esteja vazio
            if (linha[c + 1] == ',' || linha[c + 1] == '\n' || linha[c + 1] == '\0')
            {
                strcpy(substrings[qtSubstrings], "nao informado");
                qtSubstrings++;
            }
            c++;
        }
    }
}

Split SplitSpace()
{ // pede e divide por espaços

    Split Split;

    for (int i = 0; i < 3; i++)
    {
        scanf("%[^ \n]", Split.linha[i]);
        if (getchar() == '\n')
            i = 3;
    }

    return Split;
}

// metodo para realizar a leitura de um arquivo e guardar as informacoes em um array de jogadores
void ler(Jogador jogadores[], FILE *file)
{

    char linha[200];
    int qtJogadores = -1; // inicializacao negativa para que a primeira linha seja ignorada

    while (fgets(linha, sizeof(linha), file) != NULL)
    {
        char substrings[8][100];
        if (qtJogadores >= 0)
        {
            split(linha, substrings);
            // conversao de strings para inteiros
            int ID = atoi(substrings[0]);
            int h = atoi(substrings[2]);
            int p = atoi(substrings[3]);
            int ano = atoi(substrings[5]);

            jogadores[qtJogadores].id = ID;
            strcpy(jogadores[qtJogadores].nome, substrings[1]);
            jogadores[qtJogadores].altura = h;
            jogadores[qtJogadores].peso = p;
            strcpy(jogadores[qtJogadores].universidade, substrings[4]);
            jogadores[qtJogadores].anoNascimento = ano;
            strcpy(jogadores[qtJogadores].cidadeNascimento, substrings[6]);
            strcpy(jogadores[qtJogadores].estadoNascimento, substrings[7]);
            qtJogadores++;
        }
        else
            qtJogadores++;
    }
}

void dados(Jogador jogadores)
{
    printf("[%d ## %s ## %d ## %d ## %d ## %s ## %s ## %s]\n",
           jogadores.id,
           jogadores.nome,
           jogadores.altura,
           jogadores.peso,
           jogadores.anoNascimento,
           jogadores.universidade,
           jogadores.cidadeNascimento,
           jogadores.estadoNascimento);
}
//-------------------------CLASSE CELULA------------------------//
typedef struct CelulaDupla
{
    Jogador elemento;         // Elemento inserido na celula.
    struct CelulaDupla *prox; // Aponta a celula prox.
    struct CelulaDupla *ant;  // Aponta a celula anterior.
} CelulaDupla;

CelulaDupla *novaCelulaDupla(Jogador elemento)
{
    CelulaDupla *nova = (CelulaDupla *)malloc(sizeof(CelulaDupla));
    nova->elemento = elemento;
    nova->ant = nova->prox = NULL;
    return nova;
}
//-------------------------CLASSE LISTA------------------------//
typedef struct Lista
{
    CelulaDupla *primeiro, *ultimo;
    int size;

    void (*InserirInicio)(struct Lista *, Jogador x);
    void (*InserirFim)(struct Lista *, Jogador x);
    void (*Inserir)(struct Lista *, Jogador x, int pos);

    Jogador (*RemoverInicio)(struct Lista *);
    Jogador (*RemoverFim)(struct Lista *);
    Jogador (*Remover)(struct Lista *, int pos);

    void (*Mostrar)(struct Lista *);
    void (*MostrarR)(struct Lista *);
    void (*Close)(struct Lista *);

} Lista;

// INSERIR NA LISTA
void InserirInicioListaDupla(Lista *lista, Jogador x)
{

    if (lista->size >= MAXTAM)
    {
        printf("Erro ao inserir no inicio!");
        exit(1);
    }

    /*
     * Cria uma celula tmp, e faz com que o priximo dela seja o proximo do primeiro, e o anterior dela seja o primeiro
     * Tornando o primeiro.prox em primeiro.prox.prox
     * primeiro--->primeiro.prox
     * primeiro--->tmp--->primeiro.prox
    */

    lista->size++;

    CelulaDupla *tmp = novaCelulaDupla(x);

    tmp->ant = lista->primeiro;
    tmp->prox = lista->primeiro->prox;
    lista->primeiro->prox = tmp;

    if (lista->primeiro == lista->ultimo)
    {
        lista->ultimo = tmp;
    }
    else
    {
        tmp->prox->ant = tmp;
    }
    // desapontando variaveis
    tmp = NULL;
    free(tmp);
}

void InserirFimListaDupla(Lista *lista, Jogador x)
{

    // validar insercao
    if (lista->size >= MAXTAM)
    {
        printf("Erro ao inserir!");
        exit(1);
    }

    /*
     * Cria uma celula no ultimo.prox, que antes era nulo, depois aponta o
     * ultimo.prox.ant, para o ultimo, ligando elas e depois substitui o ultimo

     * ultimo--->null

     * ultimo--->ultimo.prox--->null

     * ultimo.prox.ant=ultimo<---ultimo.prox---->null

     * ultimo.ant--->ultimo(ultimo.prox)---->ultimo--->null
    */

    lista->ultimo->prox = novaCelulaDupla(x);
    lista->ultimo->prox->ant = lista->ultimo;
    lista->ultimo = lista->ultimo->prox;

    lista->size++;
}

// REMOVER DA LISTA
Jogador RemoverInicioListaDupla(Lista *lista)
{
    // validar remocao
    if (lista->size == 0)
    {
        printf("Erro ao remover!");
        exit(1);
    }

    Jogador removido = lista->primeiro->prox->elemento;
    CelulaDupla *tmp = lista->primeiro;

    lista->primeiro = lista->primeiro->prox;

    lista->size--;

    // desapontando variaveis
    tmp->prox = lista->primeiro->ant = NULL;
    tmp = NULL;
    free(tmp);

    return removido;
}

Jogador RemoverFimListaDupla(Lista *lista)
{

    // validar remocao
    if (lista->size == 0)
    {
        printf("Erro ao remover!");
        exit(1);
    }

    lista->size--;
    Jogador removido = lista->ultimo->elemento;

    lista->ultimo = lista->ultimo->ant;

    // desaponando variaveis
    lista->ultimo->prox->ant = NULL;
    free(lista->ultimo->prox);
    lista->ultimo->prox = NULL;

    return removido;
}

void MostrarListaDupla(Lista *lista)
{
    CelulaDupla *J = lista->primeiro->prox;
    for (int i = 0; i < lista->size; i++)
    {
        dados(J->elemento);
        J = J->prox;
    }
}

void CloseListaDupla(Lista *lista)
{
    free(lista->primeiro);
}

Lista newLista()
{

    Lista lista;

    lista.size = 0;
    lista.primeiro = novaCelulaDupla((Jogador){0});
    lista.ultimo = lista.primeiro;

    lista.InserirInicio = InserirInicioListaDupla;
    lista.InserirFim = InserirFimListaDupla;

    lista.RemoverInicio = RemoverInicioListaDupla;
    lista.RemoverFim = RemoverFimListaDupla;

    lista.Mostrar = MostrarListaDupla;
    lista.Close = CloseListaDupla;

    return lista;
}

void doComando(Lista *lista, Jogador jogadores[], Lista *removidos)
{

    Split split = SplitSpace();

    // inserir
    if (strcmp(split.linha[0], "II") == 0)
    {
        int valor = atoi(split.linha[1]);

        lista->InserirInicio(lista, jogadores[valor]);
    }

    if (strcmp(split.linha[0], "IF") == 0)
    {
        int valor = atoi(split.linha[1]);

        lista->InserirFim(lista, jogadores[valor]);
    }

    if (strcmp(split.linha[0], "I*") == 0)
    {
        int valor = atoi(split.linha[2]);
        int id = atoi(split.linha[1]);
        lista->Inserir(lista, jogadores[valor], id);
    }

    // remover
    if (strcmp(split.linha[0], "RI") == 0)
    {
        removidos->InserirFim(removidos, lista->RemoverInicio(lista));
    }

    if (strcmp(split.linha[0], "RF") == 0)
    {
        removidos->InserirFim(removidos, lista->RemoverFim(lista));
    }

    if (strcmp(split.linha[0], "R*") == 0)
    {
        int id = atoi(split.linha[1]);
        removidos->InserirFim(removidos, lista->Remover(lista, id));
    }
}

CelulaDupla *Pivo(CelulaDupla *esq, CelulaDupla *dir)
{
    Jogador pivo = dir->elemento;
    CelulaDupla *i = esq;
    /*
     * Usando a Celula i = primeiro.prox, vou comparar com a Celula j, que percorre todo o array
     * Se o elemento da Celula j for menor que o pivo, eu troco o elemento da Celula i com o da Celula j
     * Desta forma a Celula i vai percorrer o array e terminar com o ultimo numero menor que o pivo
     * depois eu troco o elemento da Celula i com o da Celula dir, que é o pivo
     */

    for (CelulaDupla *j = esq; j != dir; j = j->prox)
    {
         int compareResult = strcmp(j->elemento.estadoNascimento, pivo.estadoNascimento);

        if (compareResult < 0 || (compareResult == 0 && strcmp(j->elemento.nome, pivo.nome) < 0))
        {
            Jogador tmp = i->elemento;
            i->elemento = j->elemento;
            j->elemento = tmp;
            i = i->prox;
        }
       
    }

    Jogador tmp = i->elemento;
    i->elemento = dir->elemento;
    dir->elemento = tmp;

    return i;
}

void QuickSort(CelulaDupla *esq, CelulaDupla *dir)
{
    if (esq != NULL && dir != NULL && esq != dir && esq->ant != dir)
    {
        CelulaDupla *pivo = Pivo(esq, dir);
        
        QuickSort(pivo->prox, dir);
        QuickSort(esq, pivo->ant);
    }
}

void QuickSortListaDupla(Lista lista)
{
    QuickSort(lista.primeiro->prox, lista.ultimo);
   

}
//-------------------------------MAIN---------------------------------//
int main()
{

    char id[50];
    Jogador jogadores[3922];
    Lista lista = newLista();

    FILE *file = fopen("/tmp/players.csv", "r");
    do
    {
        scanf("%s", id);
        if (strcmp(id, "FIM") != 0 && strcmp(id, "fim") != 0)
        {
            int identificador = atoi(id);
            ler(jogadores, file);
            lista.InserirFim(&lista, jogadores[identificador]);
        }
    } while ((strcmp(id, "FIM") != 0) && (strcmp(id, "fim") != 0));

    fclose(file);

    QuickSortListaDupla(lista);

    /* A seguir  tem a funcionalidade de realizar os comandos pedidos */

    // imprime os resultados
    lista.Mostrar(&lista);
    lista.Close(&lista);
}