#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include <time.h>
#include <stdlib.h>
#define MAXTAM 1000

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

// metodo para dividir a string linha em substrings entre as virgulas
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

void imprime( Jogador jogadores)
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

// CLASSE LISTA
typedef struct Lista
{
    Jogador array[MAXTAM];
    int n;
} Lista;

void start(Lista lista)
{
    lista.n = 0;
}

void inserirInicio(Lista lista, Jogador x)
{

    if (lista.n >= MAXTAM)
    {
        printf("Erro ao inserir!");
        exit(1);
    }

    for (int i = lista.n; i > 0; i--)
    {
        lista.array[i] = lista.array[i - 1];
    }

    lista.array[0] = x;
    lista.n++;
}

void inserirFim(Lista lista,Jogador x)
{

    // validar insercao
    if (lista.n >= MAXTAM)
    {
        printf("Erro ao inserir!");
        exit(1);
    }

    lista.array[lista.n] = x;
    lista.n++;
}


void inserir(Lista lista, Jogador x, int pos)
{
    

    // validar insercao
    if (lista.n >= MAXTAM || pos < 0 || pos > lista.n)
    {
        printf("Erro ao inserir!");
        exit(1);
    }

    // levar elementos para o fim do lista.array
    for (int i = lista.n; i > pos; i--)
    {
        lista.array[i] = lista.array[i - 1];
    }

    lista.array[pos] = x;
    lista.n++;
}

Jogador removerInicio(Lista lista)
{
    Jogador resp;

    // validar remocao
    if (lista.n == 0)
    {
        printf("Erro ao remover!");
        exit(1);
    }

    resp = lista.array[0];
    lista.n--;

    for (int i = 0; i < lista.n; i++)
    {
        lista.array[i] = lista.array[i + 1];
    }

    return resp;
}

/**
 * Remove um elemento da ultima posicao da
 * @return resp int elemento a ser removido.
 */
Jogador removerFim(Lista lista)
{

    // validar remocao
    if (lista.n == 0)
    {
        printf("Erro ao remover!");
        exit(1);
    }

    return lista.array[--lista.n];
}

/**
 * Remove um elemento de uma posicao especifica da lista e
 * movimenta os demais elementos para o inicio da mesma.
 * @param pos Posicao de remocao.
 * @return resp int elemento a ser removido.
 */
Jogador remover(Lista lista,int pos)
{
    Jogador resp;

    // validar remocao
    if (lista.n == 0 || pos < 0 || pos >= lista.n)
    {
        printf("Erro ao remover!");
        exit(1);
    }

    resp = lista.array[pos];
    lista.n--;

    for (int i = pos; i < lista.n; i++)
    {
        lista.array[i] = lista.array[i + 1];
    }

    return resp;
}

int main()
{

    char id[50];
    Jogador jogadores[3922];
    Lista lista;
    int numeroJogador = 0;
    FILE *file = fopen("tmp/players.csv", "r");
    do
    {
        scanf("%s", id);
        if (strcmp(id, "FIM") != 0 && strcmp(id, "fim") != 0)
        {
            int identificador = atoi(id);
            ler(jogadores, file);
            lista.array[numeroJogador] = jogadores[identificador];
            numeroJogador++;
        }
    } while ((strcmp(id, "FIM") != 0) && (strcmp(id, "fim") != 0));
    fclose(file);

    for (int i = 0; i < numeroJogador; i++)
    {
        imprime(lista.array[i]);
    }

}