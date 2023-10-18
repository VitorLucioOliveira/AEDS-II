#include <stdio.h>
#include <string.h>

void altera(char a[],int c,char y,char b){
    if(c>=strlen(a)){
        return;
    }
    else{
        if(a[c]==y)a[c]=b;
        printf("%c",a[c]);
        altera(a,c+1,y,b);
    }
}
int main(){
   
    char a,b;
    char palavra[1000];
    char final[]="FIM";
    int fim;
     do{
            srand(4);
            scanf(" %[^\r\n]%*c", palavra);
            fflush(stdin);
            fim=strcmp(palavra,final);
            if(fim!=0){
                a = rand() % 26 + 97; 
                b = rand() % 26 + 97; 
                altera(palavra,0,a,b);
                printf("\n");
            }
           
        }while(fim!=0);  
    
}