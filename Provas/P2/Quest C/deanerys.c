#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

//-------------------------CLASSE DRAGON--------------------------
typedef struct Dragon
{
    int diasTreino;
    int multa;
} Dragon;

//----------------------CLASSE FILA---------------------------------
typedef struct Fila
{
    Dragon *array;
    int maxsize, size;

    void (*Inserir)(struct Fila*, Dragon x);
    void (*Remover)(struct Fila*);

} Fila;

void inserirFim(Fila *fila, Dragon dragon)
{
    if (fila->size == fila->maxsize)
    {
        printf("Erro Fila cheia");
        exit(1);
    }

    fila->array[fila->size] = dragon;
    fila->size++;
}

void removerInicio(Fila *fila)
{
    if (fila->size < 0)
    {
        printf("Erro Fila vazia");
        exit(1);
    }


    /*para no size-1 porque quero chegar ate o penultimo algoritimo*/
    for (int i = 0; i < fila->size - 1; i++)
    {
        fila->array[i] = fila->array[i + 1];
    }
    fila->size--;

    
}

// daddy lucas
Fila newFila(int maxsize)
{
    Fila fila;

    fila.size = 0;
    fila.maxsize = maxsize;

    // tipo do array + malloc + tamanho do array + sizeof(Tipo do array)
    fila.array = (Dragon *)malloc(maxsize * sizeof(Dragon));

    fila.Remover = removerInicio;
    fila.Inserir = inserirFim;

    return fila;
}

// multa

int multaDiariaTotal(Fila fila)
{
    int multaDiaria = 0;

    for (int i = 0; i < fila.size; i++)
    {
        multaDiaria += fila.array[i].multa;
    }

    return multaDiaria;
}

void swap(Dragon *x, Dragon *y)
{
    Dragon aux = *x;
    *x = *y;
    *y = aux;
}
// ordenação

void ordenar(Fila fila)
{   
    /*
     *1ª passo: vamos percorrei o array ate a penultima posição com i
     *2ª passo: vamos salvar a posição do i como maior
     *3ª passo: vamos comparar a primeira posição com todas as outras do array, garantindo que o primeiro valor vai ter a maior multa
     *Resultado: Assim, vamos garantir que o elemento i, tenha a maior multa, i+1 a segunda maior e assim em diante
    */
    for (int i = 0; i < fila.size - 1; i++)//1º
    {
        for (int j = i + 1; j < fila.size; j++)
        {   
            int Maior = i;//2º

            if (fila.array[Maior].multa < fila.array[j].multa)//3º
            {
                Maior = j;
            } 
            if (Maior!=i)
            {
                swap(&fila.array[Maior], &fila.array[i]);//3º
            }
            
            
        }
    }
}



//----------------------- MAIN -----------------------

int main()
{

    // iniciei a fila com o numero maximo
    Fila fila = newFila(105);

    int valorTotalMultas = 0;

    Dragon dragon;

    do
    {
        // Le os dias que precisa pra treinar e a multa do Dragon
        scanf("%i %i", &dragon.diasTreino, &dragon.multa);
        fila.Inserir(&fila, dragon);

        /*
         *1º passo: Iniciamos um for depois de termos inseridos o dragão na fila
         *2º passo: Vamos treinar o primeiro dragão ate acabar os dias dele e acumulando as multas
         *3º passo: Quando os dias acabarem, o dragão n precisa mais treinar e vamos remover ele
         */
        for (int i = 0; i < fila.size; i++)
        {
            fila.array[0].diasTreino--;

            valorTotalMultas += multaDiariaTotal(fila);

            if (fila.array[0].diasTreino == 0)
            {
                fila.Remover(&fila);
                ordenar(fila);
            }
        }

    } while (dragon.diasTreino + dragon.multa != 0);

    printf("%d", valorTotalMultas);
    return 0;
}
