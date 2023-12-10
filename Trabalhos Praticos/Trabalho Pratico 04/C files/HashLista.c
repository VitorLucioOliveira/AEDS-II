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
typedef struct Celula
{
    char* elemento;         // Elemento inserido na celula.
    struct Celula *prox; // Aponta a celula prox.
} Celula;

Celula *novaCelula(char *elemento)
{
    Celula *nova = (Celula *)malloc(sizeof(Celula));
    nova->elemento = elemento;
    nova->prox = NULL;
    return nova;
}
//-------------------------CLASSE LISTA------------------------//
typedef struct Lista
{
    Celula *primeiro, *ultimo;
    

} Lista;

Lista *newLista()
{
    Lista *tmp = (Lista*)malloc(sizeof(Lista));
    tmp->primeiro= novaCelula("");
    tmp->ultimo = tmp->primeiro;

    return tmp;
}

void InserirLista(Lista *lista, char* nome)
{

    lista->ultimo->prox = novaCelula(nome);
    lista->ultimo = lista->ultimo->prox;
   
}
bool pesquisarLista(char* nome,Lista *lista)
{
    bool resp = false;
     
    Celula *i;
    for (i = lista->primeiro->prox; i !=NULL; i = i->prox)
    {
        if(strcmp(i->elemento,nome)==0)
        {
            resp =true;
            i = lista->ultimo;
        }
    }

    return resp;
}

//-------------------------CLASSE HASH-----------------------------//
typedef struct Hash
{
    Lista* tabela[25];
    int tt;
}Hash;
 
Hash *newHash()
{
    Hash *tmp=(Hash*)malloc(sizeof(Hash));
    tmp->tt=25;

    for (int i = 0; i < 25; i++)
    {
       tmp->tabela[i]= newLista();
    }
    
return tmp;
}

int getHash(int altura)
{
    return altura%25;
}

void inserirHash(Jogador *jogador, Hash *hash)       
{
    int pos = getHash(jogador->altura);
    InserirLista(hash->tabela[pos], jogador->nome);
}


bool pesquisar(char nome[], Hash *hash)
{
    bool resp = false;

    for (int i = 0; i < hash->tt; i++)
    {
       if(pesquisarLista(nome, hash->tabela[i]))
       {
        resp = true;
        i = hash->tt;
       }
    }
    return resp;
    
}


//-------------------------------MAIN---------------------------------//
int main()
{

    char charProblematico[2] = {13};
    char id[50];
    char nome[50];
    Jogador jogadores[3922];
    Hash *hash = newHash();

    FILE *file = fopen("/tmp/players.csv", "r");
    do
    {
        scanf("%s", id);
        id[strcspn(id, charProblematico)]='\0';
        if (strcmp(id, "FIM") != 0 && strcmp(id, "fim") != 0)
        {
            int identificador = atoi(id);
            ler(jogadores, file);
            inserirHash(&jogadores[identificador], hash);
        }
    } while ((strcmp(id, "FIM") != 0) && (strcmp(id, "fim") != 0));

      do
    {

        scanf(" %[^\n]", nome);
        nome[strcspn(nome, charProblematico)]='\0';

        if (strcmp(nome, "FIM") != 0 && strcmp(nome, "fim") != 0)
        {

            printf("%s", nome);
            
            if (pesquisar(nome, hash))
            {
                printf(" SIM\n");
            }
            else
            {
                printf(" NAO\n");
            }
        }
    } while (strcmp(nome, "FIM") != 0 && strcmp(nome, "fim") != 0);

    fclose(file);



  
}