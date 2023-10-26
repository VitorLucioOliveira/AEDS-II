#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include <time.h>
#include <stdlib.h>
#define MAXTAM 500
#define MAX_ATTRIBUTES 8
#define MAX_LEN 100 

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

typedef struct Split {
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

Split SplitSpace(){//pede e divide por espaços

    Split Split;

    for(int i=0; i<3; i++){
        scanf("%[^ \n]", Split.linha[i]);   
        if(getchar() == '\n') i=3;  
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

void imprime(Jogador jogadores)
{
    printf(" ## %s ## %d ## %d ## %d ## %s ## %s ## %s ##\n",
           jogadores.nome,
           jogadores.altura,
           jogadores.peso,
           jogadores.anoNascimento,
           jogadores.universidade,
           jogadores.cidadeNascimento,
           jogadores.estadoNascimento);
}

// CLASSE LISTA
typedef struct Lista
{
    Jogador *array;
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
void InserirInicioListaLinear(Lista *lista, Jogador x)
{

    if (lista->size >= MAXTAM)
    {
        printf("Erro ao inserir no inicio!");
        exit(1);
    }

    for (int i = lista->size; i > 0; i--)
    {
        lista->array[i] = lista->array[i - 1];
    }

    lista->array[0] = x;
    lista->size++;
}

void InserirFimListaLinear(Lista *lista, Jogador x)
{

    // validar insercao
    if (lista->size >= MAXTAM)
    {
        printf("Erro ao inserir!");
        exit(1);
    }

    lista->array[lista->size] = x;
    lista->size++;
}

void InserirListaLinear(Lista *lista, Jogador x, int pos)
{

    // validar insercao
    if (lista->size >= MAXTAM || pos < 0 || pos > lista->size)
    {
        printf("Erro ao inserir!");
        exit(1);
    }

    // levar elementos para o fim do lista.array
    for (int i = lista->size; i > pos; i--)
    {
        lista->array[i] = lista->array[i - 1];
    }

    lista->array[pos] = x;
    lista->size++;
}

// REMOVER DA LISTA
Jogador RemoverInicioListaLinear(Lista *lista)
{
    // validar remocao
    if (lista->size == 0)
    {
        printf("Erro ao remover!");
        exit(1);
    }

    Jogador removido = lista->array[0];

    for (int i = 0; i < lista->size; i++)
    {
        lista->array[i] = lista->array[i + 1];
    }

    lista->size--;
    return removido;
}

Jogador RemoverFimListaLinear(Lista *lista)
{

    // validar remocao
    if (lista->size == 0)
    {
        printf("Erro ao remover!");
        exit(1);
    }

    return lista->array[--lista->size];
}

Jogador RemoverListaLinear(Lista *lista, int pos)
{
    // validar remocao
    if (lista->size == 0 || pos < 0 || pos >= lista->size)
    {
        printf("Erro ao remover!");
        exit(1);
    }

    Jogador removido = lista->array[pos];

    for (int i = pos; i < lista->size; i++)
    {
        lista->array[i] = lista->array[i + 1];
    }

    lista->size--;
    return removido;
}

void MostrarListaLinear(Lista *lista)
{

    for (int i = 0; i < lista->size; i++)
    {
        printf("[%i]", i);
        imprime(lista->array[i]);
    }
}

void MostrarRListaLinear(Lista *lista)
{

    for (int i = 0; i < lista->size; i++)
    {
        printf("(R) %s\n", lista->array[i].nome);
        
    }
}

void CloseListaLinear(Lista *lista)
{
    free(lista->array);
}

Lista newLista()
{

    Lista lista;

    lista.size = 0;
    lista.array = (Jogador *)malloc(MAXTAM * sizeof(Jogador));

    lista.InserirInicio = InserirInicioListaLinear;
    lista.InserirFim = InserirFimListaLinear;
    lista.Inserir = InserirListaLinear;

    lista.RemoverInicio = RemoverInicioListaLinear;
    lista.RemoverFim = RemoverFimListaLinear;
    lista.Remover = RemoverListaLinear;

    lista.MostrarR = MostrarRListaLinear;
    lista.Mostrar = MostrarListaLinear;
    lista.Close = CloseListaLinear;

    return lista;
}

void doComando(Lista *lista, Jogador jogadores[], Lista* removidos)
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
        removidos->InserirFim(removidos, lista->Remover(lista,id));
    }
}

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

    /* A seguir  tem a funcionalidade de realizar os comandos pedidos */
   
    
    Lista removidos = newLista();//lista para guardar os removidos
    
    int action; scanf("%i", &action); // numero de acoes a serem realizadas

    for (int i = 0; i <=action; i++)// repedição para realizar as acoes
    {
        doComando(&lista, jogadores, &removidos);// realiza os commandos e insere na lista de removidos
    }
    

    //imprime os resultados
    removidos.MostrarR(&removidos);
    lista.Mostrar(&lista);
    lista.Close(&lista);
    removidos.Close(&removidos);
}