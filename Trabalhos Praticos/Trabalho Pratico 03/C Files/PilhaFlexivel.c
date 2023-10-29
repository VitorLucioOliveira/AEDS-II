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
    Jogador jogador;    // Elemento inserido na celula.
    struct Celula *low; // Aponta a celula abaixo
} Celula;

Celula *novaCelula(Jogador elemento)
{
    Celula *nova = (Celula *)malloc(sizeof(Celula));
    nova->jogador = elemento;
    nova->low = NULL;

    return nova;
}

//------------------------------------CLASSE Pilha------------------------------//
typedef struct Pilha
{
    Celula *ultimo;
    int size;

    void (*Inserir)(struct Pilha *, Jogador x);

    Jogador (*Remover)(struct Pilha *);

    void (*Mostrar)(struct Pilha);

    void (*Close)(struct Pilha *);

} Pilha;

// INSERIR NA Pilha

void InserirFimPilha(Pilha *pilha, Jogador x)
{

    Celula *tmp = novaCelula(x);

    tmp->low = pilha->ultimo;
    pilha->ultimo = tmp;
    pilha->size++;
}

// REMOVER DA Pilha
Jogador RemoverFimPilha(Pilha *pilha)
{
    // validar remocao
    if (pilha->ultimo == NULL)
    {
        printf("Erro ao remover!");
        exit(1);
    }

    Celula *tmp = pilha->ultimo;
    Jogador removido = tmp->jogador;
    pilha->ultimo = pilha->ultimo->low;

    /*limpando variaveis*/
    tmp->low = NULL;
    free(tmp);
    tmp = NULL;

    pilha->size--;

    return removido;
}

// Mostrar Pilha

void doMostrarPilha(int size, Celula *ultimo)
{
    if (ultimo->low != NULL)
    {
        doMostrarPilha(size - 1, ultimo->low);
    }

    printf("[%d]", size);
    imprime(ultimo->jogador);
}

void MostrarPilha(Pilha pilha)
{
    if (pilha.size == 0)
    {
        printf("Pilha Vazia");
        exit(1);
    }

    /*Fazer de forma recursiva permite que faça do primeiro elemento ao ultimo*/
    doMostrarPilha(pilha.size - 1, pilha.ultimo);
}


// fechar Pilha
void ClosePilha(Pilha *Pilha)
{
    Celula *i = Pilha->ultimo;
    while (i != NULL)
    {
        i = i->low;
    }
}

Pilha newPilha()
{

    Pilha Pilha;

    Pilha.size = 0;
    Pilha.ultimo = NULL;

    Pilha.Inserir = InserirFimPilha;

    Pilha.Remover = RemoverFimPilha;

    Pilha.Mostrar = MostrarPilha;
    Pilha.Close = ClosePilha;

    return Pilha;
}

//------------------------------------MAIN------------------------------//

void doComando(Pilha *pilha, Jogador jogadores[])
{

    Split split = SplitSpace();

    // inserir
    if (strcmp(split.linha[0], "I") == 0)
    {
        int valor = atoi(split.linha[1]);
        pilha->Inserir(pilha, jogadores[valor]);
    }

    // remover
    if (strcmp(split.linha[0], "R") == 0)
    {
        Jogador jogador = pilha->Remover(pilha);
        printf("(R) %s\n", jogador.nome);
    }
}

int main()
{

    char id[50];
    Jogador jogadores[3922];
    Pilha Pilha = newPilha();

    FILE *file = fopen("/tmp/players.csv", "r");
    do
    {
        scanf("%s", id);
        if (strcmp(id, "FIM") != 0 && strcmp(id, "fim") != 0)
        {
            int identificador = atoi(id);

            ler(jogadores, file);

            Pilha.Inserir(&Pilha, jogadores[identificador]);
        }
    } while ((strcmp(id, "FIM") != 0) && (strcmp(id, "fim") != 0));
    fclose(file);

    /* A seguir  tem a funcionalidade de realizar os comandos pedidos */
    int action;
    scanf("%i", &action); // numero de acoes a serem realizadas

    for (int i = 0; i <= action; i++) // repedição para realizar as acoes
    {
        doComando(&Pilha, jogadores); // realiza os commandos e insere na Pilha de removidos
    }

    // imprime os resultados
    Pilha.Mostrar(Pilha);
    Pilha.Close(&Pilha);
}