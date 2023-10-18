#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include <time.h>
#include <stdlib.h>
// Variaveis globais para definiçao do numero de comparacoes, movimentaçoes e do tempo de execucao do algoritimo
int numeroC, numeroM;
double tempoE;
clock_t inicio, fim;

typedef struct Jogadores
{
    int id;
    char nome[50];
    int altura;
    int peso;
    char universidade[100];
    int anoNascimento;
    char cidadeNascimento[50];
    char estadoNascimento[50];
} Jogadores;



// funcao para ordenar os jogadores com base no id
void doHeap(Jogadores jogadores[], int i, int fim) {

        int raiz = i; // salva o vetor inicial
        int esquerda = 2 * i + 1; // salva o vetor da esquerda da arvore
        int direita = 2 * i + 2; // salva o vetor da direita da arvore

        if (esquerda < fim && jogadores[esquerda].altura > jogadores[raiz].altura) { // compara a altura da
                                                                                               // esquerda com a raiz
            
            raiz = esquerda;// troca a raiz pelo maior valor
        }

        if (direita < fim && jogadores[direita].altura > jogadores[raiz].altura) { // compara a altura da
                                                                                             // direita com a raiz
            
            raiz = direita;// troca a raiz pelo maior valor
        }

        if (raiz != i) {
            
            Jogadores aux = jogadores[i];// raiz original
            jogadores[i] = jogadores[raiz];// troca a raiz pelo maior valor encontrado
            jogadores[raiz] = aux;// troca o maior valor encontrado pelo valor original da raiz
           
            doHeap(jogadores, raiz, fim); // chama a função novamente para ordenar as raizes trocadas da arvore
                                          // subsequentes a raiz original que era i
        }

    }

    // ordenar heap sort
void heapsort(int fim, Jogadores jogadores[]) {

         
       

        /*
         * Referente ao FOR a seguir.
         * O FOR, tem a função de tornar as raizes da arvore os meiores elementos.
         * Ou seja, os maiores elementos pai do vetor estão na primeira metada da arvore
         * e são maiores que os filhos.
         */
        for (int i = ((fim / 2) - 1); i >=0; i--) {
            
            doHeap(jogadores, i, fim);
        }

        /*
         * Referente ao FOR a seguir.
         * O FOR, tem a função de jogar os maiores elementos do vetor para o final.
         * Depois, para garantir a organização, ele chama a função doHeap para ordenar
         * novamente a arvore nova criada.
         */

        for (int i = fim - 1; i >= 0; i--) {
            
            Jogadores aux = jogadores[0];
            jogadores[0] = jogadores[i];
            jogadores[i] = aux;
      
            doHeap(jogadores, 0, i);
        }

        for (int i = 0; i < fim; i++) { // organiza por ordem alfabetica os Jogadoreses com a mesma altura
            
            for (int j = i+1; j < fim; j++) {
                
                if (jogadores[i].altura == jogadores[j].altura) {
                    
                    if ( strcmp(jogadores[i].nome, jogadores[j].nome) > 0) {
                        
                        Jogadores aux = jogadores[j];
                        jogadores[j] = jogadores[i];
                        jogadores[i] = aux;
                  
                    }
                }
            }
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
void ler(Jogadores jogadores[], FILE *file)
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
void imprime(int identificador, Jogadores jogadores[])
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

int main()
{

    char id[500];
    Jogadores jogadores[3922];
    Jogadores subJogadores[500];
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

    
    heapsort(numeroJogador,subJogadores);

     int k= 10;
    Jogadores min[k];
     //inicializa o vetor min com os k primeiros jogadores
    for (int i = 0; i < k; i++)
    {
        min[i] = subJogadores[i];
    }
    

     for (int i = 0; i < k; i++)
    {
        imprime(i, min);
    }

 
}