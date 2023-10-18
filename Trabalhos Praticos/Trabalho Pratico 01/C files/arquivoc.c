#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Função para ler um número e gravá-lo em um arquivo
void writeToFile(int n) {
    // Declaração de variáveis
    FILE *file;
    double data;
    long position;

    // Verificando se o número digitado é válido
    if (n >= 0) {
        // Abrindo arquivo
        file = fopen("data.txt", "wb+");

        // Lendo dados do teclado e gravando no arquivo
        for (int i = 0; i < n; i++) {
            scanf("%lf", &data);
            fprintf(file, "%lf\n", data);

            // Indo para a posição de leitura do próximo dado
            position = ((i + 1) * sizeof(double));
            fseek(file, position, SEEK_SET);
        }

        position = (n - 1) * sizeof(double);
        double readData;

        for (int i = 0; i < n; i++) {
            // Posicionando o cursor no local de leitura
            fseek(file, position, SEEK_SET);

            // Lendo o dado do arquivo
            fscanf(file, "%lf", &readData);

            // Verificando se é um número inteiro e imprimindo na tela
            int integerValue = (int)readData;
            if (readData == integerValue) {
                printf("%d\n", integerValue);
            } else {
                printf("%g\n", readData);
            }

            // Movendo a posição para o próximo dado
            position = position - sizeof(double);
            fseek(file, position, SEEK_SET);
        }

        // Fechando o arquivo
        fclose(file);
    }
}

// Função principal
int main() {
    int n;
    scanf("%d", &n);

    writeToFile(n);
    return 0;
}
