#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include <time.h>
#include <stdlib.h>
#define MAXTAM 500
#define MAX_ATTRIBUTES 8
#define MAX_LEN 100

int max(int a, int b) {
    return (a > b) ? a : b;
}
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



//-------------------------CLASSE NO------------------------//
typedef struct No
{
    Jogador elemento;     // Elemento inserido na celula.
    struct No *dir, *esq; // Aponta a celula prox.
    int nivel;            // Altura do nó

} No;

No *newNo(Jogador elemento)
{
    No *nova = (No *)malloc(sizeof(No));
    nova->elemento = elemento;
    nova->esq = nova->dir = NULL;
    nova->nivel = 1; // novo nó é inicialmente adicionado nas folhas
    return nova;
}

// Nivel DO NO//
int getNivel(No *no)
{
    return (no == NULL) ? 0 : no->nivel;
}

// Balanceamento DO NO//
int getBalanceamento(No *no)
{
    return (no == NULL) ? 0 : getNivel(no->dir) - getNivel(no->esq);
}

// ROTAÇÕES//
No *rotacaoDireita(No *no)
{
    /*
     * 1º: Guarda o nó esquerdo e o no direito da esquerda
     * 2º: O no esquerdo do no inicial[noEsq], recebe como no dir o no principal[no]
     * 3º: O no principal[no], recebe todos os elementos da direita do no esquerdo[noEsqDir]
     * 4º: Atualiza os niveis dos nos com o maior nivel entre os nos filhos + 1
     * Resultado: O no esquerdo[noEsq] se torna o no principal[no]
     */

    // 1º
    No *noEsq = no->esq;
    No *noEsqDir = noEsq->dir;

    // 2º
    noEsq->dir = no;
    // 3º
    no->esq = noEsqDir;

    // 4º
    no->nivel = max(getNivel(no->esq), getNivel(no->dir)) + 1;
    noEsq->nivel = max(getNivel(noEsq->esq), getNivel(noEsq->dir)) + 1;

    return noEsq;
}
No *rotacaoEsquerda(No *no)
{
    /*
     * 1º: Guarda o nó esquerdo e o no direito da esquerda
     * 2º: O no esquerdo do no inicial[noEsq], recebe como no dir o no principal[no]
     * 3º: O no principal[no], recebe todos os elementos da direita do no esquerdo[noEsqDir]
     * 4º: Atualiza os niveis dos nos com o maior nivel entre os nos filhos + 1
     * Resultado: O no esquerdo[noEsq] se torna o no principal[no]
     */

    // 1º
    No *noDir = no->dir;
    No *noDirEsq = noDir->esq;

    // 2º
    noDir->esq = no;
    // 3º
    no->dir = noDirEsq;

    // 4º
    no->nivel = max(getNivel(no->esq), getNivel(no->dir)) + 1;
    noDir->nivel = max(getNivel(noDir->esq), getNivel(noDir->dir)) + 1;

    return noDir;
}

// INSERIR NA ARVORE//
No *inserir(Jogador jogador, No *i)
{

    /*
     * 1º: Inserção nomal na arvore;
     * 2º: atualiza o nivel do no;
     * 3º: salva o balanceamento do no;
     * 4º: faz as rotacoes necessarias;
     * Resultado: O no esquerdo[noEsq] se torna o no principal[no]
     */

    // 1º
    if (i == NULL)
    {
        i = newNo(jogador);
    }
    else if ((strcmp(jogador.nome, (i)->elemento.nome) < 0))
    {
        i->esq = inserir(jogador, i->esq);
    }
    else if ((strcmp(jogador.nome, i->elemento.nome) > 0))
    {
        i->dir = inserir(jogador, i->dir);
    }
    else
    {
        printf("Erro ao inserir!");
    }

    // 2º
    i->nivel = 1 + max(getNivel(i->esq), getNivel(i->dir));

    // 3º
    int balanceamento = getBalanceamento(i);

    // 4º

    // Rotacao simples a direita
    if (balanceamento > 1 && (strcmp(jogador.nome, i->dir->elemento.nome) > 0))
    {
        i = rotacaoEsquerda(i);
    }
    // Rotacao simples a esquerda
     if (balanceamento < -1 && (strcmp(jogador.nome, i->esq->elemento.nome) < 0))
    {
        i = rotacaoDireita(i);
    }
    // Rotacao dupla a direita
    if (balanceamento > 1 && (strcmp(jogador.nome, i->dir->elemento.nome) < 0))
    {
        i->dir = rotacaoDireita(i->dir);
        i = rotacaoEsquerda(i);
    }
    // Rotacao dupla a esquerda
     if (balanceamento < -1 && (strcmp(jogador.nome, i->esq->elemento.nome) > 0))
    {
        i->esq = rotacaoEsquerda(i->esq);
        i = rotacaoDireita(i);
    }

    return i;
}

// PESQUISAR NA ARVORE//
bool pesquisar(char nome[], No *i)
{
    bool resp = false;

    if (i == NULL)
    {
        resp = false;
    }
    else if ((strcmp(nome, i->elemento.nome) == 0))
    {
        resp = true;
    }
    else if (strcmp(nome, i->elemento.nome) < 0)
    {
        printf(" esq");
        resp = pesquisar(nome, i->esq);
    }
    else if ((strcmp(nome, i->elemento.nome) > 0))
    {
        printf(" dir");
        resp = pesquisar(nome, i->dir);
    }

    return resp;
}


//-------------------------CLASSE ARVORE------------------------//
typedef struct ArvoreAVL
{
    No *raiz;

} ArvoreAVL;


//-------------------------------MAIN---------------------------------//
int main()
{

    char id[50];
    char nome[50];
    Jogador jogadores[3922];
    ArvoreAVL arvore;
    arvore.raiz = NULL;

    FILE *file = fopen("/tmp/players.csv", "r");
    do
    {
        scanf("%s", id);
        if (strcmp(id, "FIM") != 0 && strcmp(id, "fim") != 0)
        {
            int identificador = atoi(id);
            ler(jogadores, file);
            arvore.raiz = inserir(jogadores[identificador], arvore.raiz);
        }
    } while ((strcmp(id, "FIM") != 0) && (strcmp(id, "fim") != 0));

    fclose(file);

    do
    {

        scanf(" %[^\n]", nome);

        if (strcmp(nome, "FIM") != 0 && strcmp(nome, "fim") != 0)
        {

            printf("%s raiz", nome);
            if (pesquisar(nome, arvore.raiz))
            {
                printf(" SIM\n");
            }
            else
            {
                printf(" NAO\n");
            }
        }
    } while (strcmp(nome, "FIM") != 0 && strcmp(nome, "fim") != 0);

    return 0;
}