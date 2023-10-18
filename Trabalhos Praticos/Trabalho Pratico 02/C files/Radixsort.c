#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include <time.h>
#include <stdlib.h>
// Variaveis globais para definiçao do numero de comparacoes, movimentaçoes e do tempo de execucao do algoritimo
int numeroC, numeroM;
double tempoE;
clock_t inicio, fim;

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



// funcao para ordenar os jogadores com base no id
void countSort(int size, Jogador jogador[], int place) {


    int max = (jogador[0].id / place) % 10;
    Jogador output[size];

        // achar o maior elemento do array
        for (int i = 0; i < size; i++) {
            numeroC++;
            if((jogador[i].id / place) % 10 > max){
                max = (jogador[i].id / place) % 10;
                max = jogador[i].id;
                numeroC++;
            }
            
        }

         int count[max+1];
        
        for(int i = 0; i < max+1;i ++){
            count[i] = 0;
            numeroC++;
        }
        for(int i = 0; i < size; i++){
            count[(jogador[i].id / place) % 10]++;
            numeroC++;
        }
        for(int i = 1; i < max+1; i++){
            count[i] += count[i-1];
            numeroC++;
        }

        for(int i = size-1; i >= 0; i--){
            output[count[(jogador[i].id / place) % 10] - 1] = jogador[i];
            count[(jogador[i].id / place) % 10]--;
            numeroC++;
        }

        for(int i = 0; i < size; i++){
            jogador[i] = output[i];
            numeroC++;
        }
    }

//pega o maior valor do array
int maxo(Jogador Jogador[], int tam){
    int max = Jogador[0].id;
    for(int i = 1; i < tam; i++){
        numeroC++;
        if(Jogador[i].id > max){
            max = Jogador[i].id;
            numeroC++;
        }
    }
    return max;
}


void Radix(Jogador jogadores[], int tam){

    int place;
    int max= maxo(jogadores, tam);
    
    for(place = 1; max/place > 0; place*=10)
    {
        numeroC++;
       countSort(tam, jogadores, place);
       
    }
    


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
    FILE *arq = fopen("/tmp/810862__radixsort.txt", "w");
    fprintf(arq, "810862\t%d\t%d\t%lf", numeroC, numeroM * 3, tempoE);
    fclose(arq);
}
int main()
{

    char id[500];
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
    Radix(subJogadores, numeroJogador);
    fim = clock();

    for (int i = 0; i < numeroJogador; i++)
    {
        imprime(i, subJogadores);
    }

    tempoE = (double)(fim - inicio);
    criaLog();
}