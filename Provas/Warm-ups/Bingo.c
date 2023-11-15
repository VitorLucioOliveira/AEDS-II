#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
//------------------------FUNÇÕES------------------------//

void Rolou(int bingo, int **cartelas, int linhas, int colunas)
{

    for (int i = 0; i < linhas; i++)
    {
        for (int j = 0; j < colunas; j++)
        {
            if (bingo == cartelas[i][j])
            {
                cartelas[i][j] = 0;
            }
        }
    }
}

bool venceu(int **cartelas, int linhas, int colunas)
{
    bool venceu = false;
    

    for (int i = 0; i < linhas; i++)
    {   int contador = 0;
        
        for (int j = 0; j < colunas; j++)
        {
            if (cartelas[i][j] == 0)
            {
                contador++;
            }
        }

        if (contador == colunas)
        {
            printf("%i ", i + 1);
            venceu = true;
        }
        contador = 0;
    }
    return venceu;
}

//-----------------------MAIN-----------------------//
int main()
{

    // Pega o valor da quantidade de cartelas , o valor de numeros por cartela, e o valor maximo de numeros
    int linhaCartela;
    int culunaCartela;
    int valorMax;
    scanf("%i %i %i", &linhaCartela, &culunaCartela, &valorMax);

    // Cria a matriz de linhaCa e prenche com os valores
    int **cartelas = (int **)malloc(linhaCartela * sizeof(int *));
    for (int i = 0; i < linhaCartela; i++)
    {
        cartelas[i] = (int *)malloc(culunaCartela * sizeof(int));
        for (int j = 0; j < culunaCartela; j++)
        {
            scanf("%i", &cartelas[i][j]);
        }
    }

    // Cria o vetor de valores sorteados e preenche com os valores
    int bingo[valorMax];
    for (int i = 0; i < valorMax; i++)
    {
        scanf("%i", &bingo[i]);
    }

    // Verifica se os valores sorteados estão nas cartelas
    bool TemVencedor = false;
    for (int i = 0; i < valorMax && TemVencedor == false; i++)
    {
        Rolou(bingo[i], cartelas, linhaCartela, culunaCartela);
        TemVencedor = venceu(cartelas, linhaCartela, culunaCartela);
    }

    //Liberar variaveis
    for (int i = 0; i < linhaCartela; i++)
    {
        free(cartelas[i]);
    }
    free(cartelas);

    return 0;
}