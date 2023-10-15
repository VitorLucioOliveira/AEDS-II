#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <stdbool.h>
#define MAX_ATTRIBUTES 8


typedef struct Split {
	int size;
	char linha[MAX_ATTRIBUTES][400];
} Split;

Split newSplit(FILE* tabela, Split split) {


    char elemento[400];


	for (int i = 0; i <MAX_ATTRIBUTES; i++) {
		if (fscanf(tabela, "%[^,\n]", elemento) == 0) {// sepera pela vigula ou \n e na falha faz a divisão colocando em um array
			strcpy(split.linha[i], "nao informado");
		} else {
			strcpy(split.linha[i], elemento);
		}
		fgetc(tabela); // pula uma casa
	}

	return split; // vai retornar uma linha com elementos completos
}


// Definição da estrutura Jogador
typedef struct Jogador
{
    int id;
    char nome[400];
    int altura;
    int peso;
    char universidade[400];
    int anoNascimento;
    char cidadeNascimento[400];
    char estadoNascimento[400];
} Jogador;

void MostrarJogador(Jogador *jogador) {

	printf("[%d ## ", jogador->id);
	printf("%s ## ", jogador->nome);
	printf("%d ## ", jogador->altura);
	printf("%d ## ", jogador->peso);
	printf("%d ## ", jogador->anoNascimento);
	printf("%s ## ", jogador->universidade);
	printf("%s ## ", jogador->cidadeNascimento);
	printf("%s]\n", jogador->estadoNascimento);

}



// Função para preencher os dados do jogador
    void newJogador( Jogador *player, char *pedido)
{
    FILE *tabela = fopen("tmp/players.csv", "r");


    if (tabela == NULL){ printf("erro ao abrir");}


    bool ERRO = false;
    Split virgula = { MAX_ATTRIBUTES };

   do {
        virgula = newSplit(tabela, virgula);

        if (strcmp(pedido, virgula.linha[0] )== 0){ERRO=true; }


   }while(!ERRO);

    player->id = atoi(virgula.linha[0]);
    strcpy(player->nome, virgula.linha[1]);
    player->altura = atoi(virgula.linha[2]);
    player->peso = atoi(virgula.linha[3]);
    player->anoNascimento = atoi(virgula.linha[5]);
    strcpy(player->universidade, virgula.linha[4]);
    strcpy(player->cidadeNascimento, virgula.linha[6]);
    strcpy(player->estadoNascimento, virgula.linha[7]);

    MostrarJogador(player);
    fclose(tabela);


}


int main()
{
    char pedido[400];
    struct Jogador player;

    fgets(pedido, sizeof(pedido), stdin);
    pedido[strcspn(pedido, "\n")] = '\0'; // Remova a quebra de linha da entrada
    for (int i = 0; pedido[i]; i++) {
            pedido[i] = toupper(pedido[i]);
        }


    while (strcmp(pedido, "FIM") != 0)
    {
        newJogador(&player, pedido);

        fgets(pedido, sizeof(pedido), stdin);
        pedido[strcspn(pedido, "\n")] = '\0'; // Remova a quebra de linha da entrada
    }

    return 0;
}
