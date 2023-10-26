#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include <time.h>
#include <stdlib.h>
#define MAXTAM 500

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

void splitC(char linha[], char comandos[3][10])
{
    int i = 0;
    char *delimitador = " "; // O espaço em branco é o delimitador

    char *token = strtok(linha, delimitador);

    while (token != NULL && i < 10)
    {
        // Copie o elemento de token para a posição do array
        strcpy(comandos[i], token);

        token = strtok(NULL, delimitador);
        i++;
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

    for (int i = lista->size; i < 0; i--)
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

void CloseListaLinear(Lista *lista)
{
    free(lista->array);
}

Lista newLista()
{

    Lista lista;

    lista.size = 0;
    lista.array = (Jogador *)malloc(500 * sizeof(Jogador));

    lista.InserirInicio = InserirInicioListaLinear;
    lista.InserirFim = InserirFimListaLinear;
    lista.Inserir = InserirListaLinear;

    lista.RemoverInicio = RemoverInicioListaLinear;
    lista.RemoverFim = RemoverFimListaLinear;
    lista.Remover = RemoverListaLinear;

    lista.Mostrar = MostrarListaLinear;
    lista.Close = CloseListaLinear;

    return lista;
}

void doComando(Lista *lista, char comando[3][10], Jogador jogadores[])
{
    // Inicialize uma variável para armazenar o valor convertido de comando[1]
    

    // inserir
    if (strcmp(comando[0], "II") == 0)
    {
         int valor = atoi(comando[1]);
         Jogador x = jogadores[valor];
        InserirInicioListaLinear(&lista, x);
    }

    if (strcmp(comando[0], "IF") == 0)
    {   
        int valor = atoi(comando[1]);
         Jogador x = jogadores[valor];
        InserirFimListaLinear(&lista, x);
    }

    if (strcmp(comando[0], "I*") == 0)
    {
         int valor = atoi(comando[2]);
         Jogador x = jogadores[valor];
         int id = atoi(comando[1]);
        InserirListaLinear(&lista, x, id);
    }

    // remover
    if (strcmp(comando[0], "RI") == 0)
    {
        printf("(R) %s \n", RemoverInicioListaLinear(&lista).nome);
    }

    if (strcmp(comando[0], "RF") == 0)
    {
        printf("(R) %s \n", RemoverFimListaLinear(&lista).nome);
    }

    if (strcmp(comando[0], "R*") == 0)
    {
        int id = atoi(comando[1]);
        printf("(R) %s \n", RemoverListaLinear(&lista, id).nome);
    }
}

int main()
{

    char id[50];
    Jogador jogadores[3922];
    Lista lista = newLista();

    FILE *file = fopen("tmp/players.csv", "r");
    do
    {
        scanf("%s", id);
        if (strcmp(id, "FIM") != 0 && strcmp(id, "fim") != 0)
        {
            int identificador = atoi(id);
            ler(jogadores, file);
            InserirFimListaLinear(&lista, jogadores[identificador]);
        }
    } while ((strcmp(id, "FIM") != 0) && (strcmp(id, "fim") != 0));

    // Ler comandos da entrada padrao
    int pedido;
    scanf("%i", &pedido);
    char comandos[3][10];

    while (pedido > 0)
    {
        char linha[10];
        scanf("%s", linha);
        
        splitC(linha, comandos);

        doComando(&lista, comandos, jogadores);
        pedido--;
    }

    fclose(file);

    MostrarListaLinear(&lista);
    CloseListaLinear(&lista);
}