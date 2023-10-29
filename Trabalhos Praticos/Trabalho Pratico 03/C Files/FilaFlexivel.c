#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include <time.h>
#include <stdlib.h>
#define MAX_ATTRIBUTES 8
#define MAX_LEN 100

//----------------------------------CLASSE JOGADOR------------------------------//
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

//------------------------------------CLASSE SPLIT------------------------------//

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

//------------------------------------CLASSE CELULA------------------------------//

typedef struct Celula
{
    Jogador jogador;     // Elemento inserido na celula.
    struct Celula *prox; // Aponta a celula prox.
} Celula;

Celula *novaCelula(Jogador elemento)
{
    Celula *nova = (Celula *)malloc(sizeof(Celula));
    nova->jogador = elemento;
    nova->prox = NULL;

    return nova;
}

//------------------------------------CLASSE FILA------------------------------//
typedef struct Fila
{
    Celula *primeiro, *ultimo;
    int size, maxSize;

    void (*Inserir)(struct Fila *, Jogador x);

    Jogador (*Remover)(struct Fila *);

    void (*Mostrar)(struct Fila*);

    float (*getMediaAlturas)(struct Fila);

    void (*Close)(struct Fila *);

} Fila;

// media da altura dos jogadores
float GetMediaAlturasFilaCircular(Fila fila)
{
    float media = 0;
    for (Celula *i = fila.primeiro->prox; i != NULL; i = i->prox)
    {
        media += i->jogador.altura;
    }

    return media / fila.size;
}

// INSERIR NA Fila

void InserirFimFilaCircular(Fila *fila, Jogador x)
{

    // validar insercao
    if (fila->size == 0)
    {
        fila->primeiro = fila->ultimo = novaCelula(x);
    }
    else if (fila->size == fila->maxSize+1)
    {
        fila->Remover(fila);
    }
   
    
        fila->ultimo->prox = novaCelula(x);
        fila->ultimo = fila->ultimo->prox;

        fila->size++;

    printf("%.f\n", fila->getMediaAlturas(*fila));
}

// REMOVER DA Fila
Jogador RemoverInicioFilaCircular(Fila *fila)
{
    // validar remocao
    if (fila->primeiro == fila->ultimo)
    {
        printf("Erro ao remover!");
        exit(1);
    }

    

        Celula *tmp = fila->primeiro;
        fila->primeiro = fila->primeiro->prox;
        Jogador removido = fila->primeiro->jogador;


        tmp->prox = NULL;
        free(tmp);
        tmp = NULL;

    fila->size--;

    return removido;
}

// Mostrar Fila
void MostrarFilaCircular(Fila *fila)
{

    Celula *i;
    int j = 0;
    for (i = fila->primeiro->prox; i != NULL; i = i->prox, j++)
    {
        printf("[%d]", j);
        imprime(i->jogador);
    }
}

// fechar Fila
void CloseFilaCircular(Fila *fila)
{
    Celula *i = fila->primeiro;
    while (i != NULL)
    {
        i = i->prox;
    }
}

Fila newFila()
{

    Fila fila;

    fila.size = 0;
    fila.primeiro = fila.ultimo = NULL;
    fila.maxSize = 4;

    fila.getMediaAlturas = GetMediaAlturasFilaCircular;

    fila.Inserir = InserirFimFilaCircular;

    fila.Remover = RemoverInicioFilaCircular;

    fila.Mostrar = MostrarFilaCircular;
    fila.Close = CloseFilaCircular;

    return fila;
}

//------------------------------------MAIN------------------------------//

void doComando(Fila *fila, Jogador jogadores[])
{

    Split split = SplitSpace();

    // inserir
    if (strcmp(split.linha[0], "I") == 0)
    {
        int valor = atoi(split.linha[1]);
        fila->Inserir(fila, jogadores[valor]);
    }

    // remover
    if (strcmp(split.linha[0], "R") == 0)
    {
        Jogador jogador = fila->Remover(fila);
        printf("(R) %s\n", jogador.nome);
    }
}

int main()
{

    char id[50];
    Jogador jogadores[3922];
    Fila fila = newFila();

    FILE *file = fopen("/tmp/players.csv", "r");
    do
    {
        scanf("%s", id);
        if (strcmp(id, "FIM") != 0 && strcmp(id, "fim") != 0)
        {
            int identificador = atoi(id);

            ler(jogadores, file);

            fila.Inserir(&fila, jogadores[identificador]);
        }
    } while ((strcmp(id, "FIM") != 0) && (strcmp(id, "fim") != 0));
    fclose(file);

    /* A seguir  tem a funcionalidade de realizar os comandos pedidos */
    int action;
    scanf("%i", &action); // numero de acoes a serem realizadas

    for (int i = 0; i <= action; i++) // repedição para realizar as acoes
    {
        doComando(&fila, jogadores); // realiza os commandos e insere na Fila de removidos
    }

    // imprime os resultados
    fila.Mostrar(&fila);
    fila.Close(&fila);
}