#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include <time.h>
#include <stdlib.h>
// Variaveis globais para definiçao do numero de comparacoes, movimentaçoes e do tempo de execucao do algoritimo
int numeroC, numeroM;
double tempoE;

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

void doShell(Jogador jogadores[], int tam, int pes, int gap)
{
    for (int i = (gap + pes); i < tam; i += gap)// i= distancia entre os elementos a serem comparados, ou seja o ultimo do grupo
    {
        numeroC++;
        Jogador tmp = jogadores[i];
        int j = i - gap;// primeiro do grupo a ser comparado
        while ((j >= 0) && (jogadores[j].peso > tmp.peso))
        {
            numeroC++;
            jogadores[j + gap] = jogadores[j];
            j -= gap;
            numeroM++;
        }

        while ((j >= 0) && (strcmp(jogadores[j].nome, tmp.nome) > 0) && (jogadores[j].peso == tmp.peso))
        {
            numeroC++;
            jogadores[j + gap] = jogadores[j];
            j -= gap;
            numeroM++;
        }

        jogadores[j + gap] = tmp;
    }
}

// funcao para ordenar os jogadores com base na ordem crescente de peso
void shell(Jogador jogadores[], int tam)
{
    int gap = 1; // distancia entre as comparacoes

    do
    {
        gap = (gap * 3) + 1;
    } while (gap < tam); // não sei, mas tem que fazer isso pra inicializar o gap direito

    do
    {
        gap /= 3; // diminui o gap em grupos de 3
        for (int pes = 0; pes < gap; pes++)
        {
            numeroC++;
            doShell(jogadores, tam, pes, gap); // campara os elementos com gap de distancia
        }
    } while (gap != 1);
}

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

// metodo para imprimir os atributos do jogador com id recebido
void imprime(int identificador, Jogador jogadores[])
{
    printf("[%d ## %s ## %d ## %d ## %d ## %s ## %s ## %s]\n",
           jogadores[identificador].id,
           jogadores[identificador].nome,
           jogadores[identificador].altura,
           jogadores[identificador].peso,
           jogadores[identificador].anoNascimento,
           jogadores[identificador].universidade,
           jogadores[identificador].cidadeNascimento,
           jogadores[identificador].estadoNascimento);
}
// cria um arquivo de log
void criaLog()
{
    FILE *arq = fopen("/tmp/810862_quicksort.txt", "w");
    fprintf(arq, "810862\t%d\t%d\t%lf", numeroC, numeroM * 3, tempoE);
    fclose(arq);
}
int main()
{
    clock_t inicio, fim;

    char id[50];
    Jogador jogadores[3922];
    Jogador subJogadores[500];
    int numeroJogador = 0;
    FILE *file = fopen("/tmp/players.csv", "r");
    do
    {
        scanf("%s", id);
        if (strcmp(id, "FIM") != 0 && strcmp(id, "fim") != 0)
        {
            int identificador = atoi(id);
            ler(jogadores, file);
            subJogadores[numeroJogador] = jogadores[identificador];
            numeroJogador++;
        }
    } while ((strcmp(id, "FIM") != 0) && (strcmp(id, "fim") != 0));
    fclose(file);

    inicio = clock();
    shell(subJogadores, numeroJogador);
    fim = clock();

    for (int i = 0; i < numeroJogador; i++)
    {
        imprime(i, subJogadores);
    }

    tempoE = (double)(fim - inicio) / CLOCKS_PER_SEC;
    criaLog();
}