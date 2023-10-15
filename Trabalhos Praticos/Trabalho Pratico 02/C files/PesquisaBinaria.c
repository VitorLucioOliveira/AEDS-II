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

// funcao para ordenar os jogadores com base na ordem alfabetica dos nomes, para posteriormente serem pesquisados
void selecao(Jogador jogadores[], int tam)
{
    for (int i = 0; i < tam - 1; i++)
    {
        int menor = i;
        for (int j = i + 1; j < tam; j++)
        {
            numeroC++;
            if (jogadores[j].nome[0] < jogadores[menor].nome[0])
                menor = j;
            else
            {
                numeroC++;
                if (jogadores[j].nome[0] == jogadores[menor].nome[0])
                {
                    int posChar = 1;
                    while (jogadores[j].nome[posChar] == jogadores[menor].nome[posChar])
                    {
                        posChar++;
                    }
                    numeroC++;
                    if (jogadores[j].nome[posChar] < jogadores[menor].nome[posChar])
                        menor = j;
                }
            }
        }
        numeroM++;
        // Swap
        Jogador aux = jogadores[i];
        jogadores[i] = jogadores[menor];
        jogadores[menor] = aux;
    }
}
// metodo para pesquisar um nome e validar sua presenca no conjunto
bool pesquisaBinaria(Jogador jogadores[], char chave[50], int tam)
{
    bool resp = false;
    int dir = (tam - 1), esq = 0, meio;

    while (esq <= dir)
    {
        meio = (esq + dir) / 2;
        int resul = strcmp(chave, jogadores[meio].nome);
        if (resul == 0)
        {
            resp = true;
            esq = dir + 1;
        }
        else
        {
            int c = 0;
            while (chave[c] == jogadores[meio].nome[c])
            {
                c++;
            }
            if (chave[c] > jogadores[meio].nome[c])
            {
                esq = meio + 1;
            }
            else
                dir = meio - 1;
        }
    }
    return resp;
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
    FILE *arq = fopen("810862_binaria.txt", "w");
    fprintf(arq, "810862\t%d\t%d\t%lf", numeroC, numeroM * 3, tempoE);
    fclose(arq);
}
int main()
{
    clock_t inicio, fim;
    inicio = clock();
    char id[50];
    Jogador jogadores[3922];
    Jogador subJogadores[500];
    int numeroJogador = 0;
    FILE *file = fopen("/tmp/players.csv", "r");
    do
    {
        scanf("%s", id);
        if (strcmp(id, "FIM") != 0 && strcmp(id, "fim") != 0    )
        {
            int identificador = atoi(id);
            ler(jogadores, file);
            subJogadores[numeroJogador] = jogadores[identificador];
            numeroJogador++;
        }
    } while (strcmp(id, "FIM") != 0 && strcmp(id, "fim") != 0   );
    fclose(file);
    selecao(subJogadores, numeroJogador);
    char nome[50];
    do
    {
        scanf(" %[^\r\n]%*c", nome);
        if (strcmp(nome, "FIM") != 0 && strcmp(nome, "fim") != 0    )
        {
            if (pesquisaBinaria(subJogadores, nome, numeroJogador))
                printf("SIM\n");
            else
                printf("NAO\n");
        }
    } while (strcmp(nome, "FIM") != 0 && strcmp(nome, "fim") != 0  );
    fim = clock();
    tempoE = fim - inicio;
    criaLog();
}