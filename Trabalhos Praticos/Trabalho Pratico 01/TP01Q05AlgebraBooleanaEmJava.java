class TP01Q05AlgebraBooleanaEmJava {
   //função para substituir expressão
   public static String substituiExpressaoBoo(String palavra){    
      String novaPalavra="";
      char quantidade;
      quantidade = palavra.charAt(0);
      int tam = palavra.length();

      if(quantidade =='2'){
         for(int i=5; i<tam; i++){
              //ignora os primeiros caracteres da String
            if(palavra.charAt(i)=='A'){
               novaPalavra = novaPalavra + palavra.charAt(2);  //altera valores das strings
            }else if(palavra.charAt(i)=='B'){
               novaPalavra = novaPalavra + palavra.charAt(4);
            }else if(palavra.charAt(i)=='C'){
               novaPalavra = novaPalavra + palavra.charAt(6);
            }else{
               novaPalavra = novaPalavra + palavra.charAt(i);
            }
         }
      }else if(quantidade=='3'){
         for(int i=7; i<tam; i++){  
            //ignora os primeiros carcteres da palavraing
            if(palavra.charAt(i)=='A'){
               novaPalavra = novaPalavra + palavra.charAt(2);  //altera valores das strings
            }else if(palavra.charAt(i)=='B'){
               novaPalavra = novaPalavra + palavra.charAt(4);
            }else if(palavra.charAt(i)=='C'){
               novaPalavra = novaPalavra + palavra.charAt(6);
            }else{
               novaPalavra = novaPalavra + palavra.charAt(i);
            }
         }
      }

      return novaPalavra;
   }
   //função para retornar ultimo parenteses
   public static int retornarUltimoParentese (String palavra){    
      int posi = -1;
      int tam = palavra.length();

      for(int i=0; i < tam; i++){
         //if para se achar (, retorna a posiçao do ultimo parentese 
         if(palavra.charAt(i)=='('){
            posi=i;
         }
      }

      return posi;
   }
   //função q resolve a expressão
   public static String resolveExpressaoBoo (String palavra, int posi){    
      String novaPalavra = "";
      char result;
      int j;
      int posiParentese = 0;
      int tam = palavra.length();

      if(palavra.charAt(posi-1) == 't'){
         //percorre toda expressão e resolve
         for(int i=0 ; i < tam ; i++){
            if(i == posi - 3){
               
               if(palavra.charAt(posi+1) == '1'){
                  novaPalavra = novaPalavra + '0';
               }else{
                  novaPalavra = novaPalavra + '1';
               }

               for(j=posi-2; j<tam; j++){
                  //acha a posiçao do parentese fechado
                  if(palavra.charAt(j) == ')'){
                     novaPalavra =novaPalavra +"";
                     posiParentese=j;
                     j = tam;
                  }
               }

               i = posiParentese;
            }else{
               novaPalavra = novaPalavra + palavra.charAt(i);
            }   
         }


      }else if(palavra.charAt(posi-1) == 'd'){
         //resolve expressão
         result = '1';
         for(int i = posi ; i < tam ; i++){
            if(palavra.charAt(i) == ')'){
               i = tam;
            }else if(palavra.charAt(i) == '0'){
               result = '0';
               i = tam;
            }
         }
         //forma nova palavra
         for(int i = 0 ; i < tam ; i++){
            if(i == posi - 3){
               novaPalavra = novaPalavra + result;
               for(j= posi - 2 ; j < tam ; j++){
                  if(palavra.charAt(j) == ')'){ 
                     novaPalavra = novaPalavra + "";
                     posiParentese = j;
                     j = tam;
                  }
               }
               //passa a copiar a palavra a partir do primeiro parentese )
               i = posiParentese; 
            }else{
               novaPalavra = novaPalavra + palavra.charAt(i);
            }
         }

      }else{
         //resolve expressao
         result = '0';
         //percorre expressão
         for(int i = posi; i < tam; i++){
            if(palavra.charAt(i) == ')'){
               i = tam;
            }else if(palavra.charAt(i) == '1'){
               result = '1';
               i = tam;
            }
         }
         //forma nova palavra com o resultado
         for(int i=0 ;i<tam; i++){
            if(i==posi-2){
               novaPalavra = novaPalavra + result;
               for(j= posi - 1 ; j < tam ; j++){
                  if(palavra.charAt(j) == ')'){ 
                     novaPalavra = novaPalavra + "";
                     posiParentese = j;
                     j = tam;
                  }
               }
               i = posiParentese;
            }else{
               novaPalavra = novaPalavra + palavra.charAt(i);
            }
         }
      }

      return novaPalavra ;
   }

   //função para verificar se palavra é o fim
   public static boolean ehFim(String palavra){
      if(palavra.charAt(0) == '0'){
         return true;
      }else{
         return false;
      }
   }

   //MAIN
   public static void main (String[] args){
      String[] entrada = new String[1000];
      int numEntrada = 0;
      String novaPalavra, palavraAux;
      int posicao;
      //Leitura
      do {
         entrada[numEntrada] = MyIO.readLine();
      }while(ehFim(entrada[numEntrada++]) == false);

      numEntrada--;
       
      for(int i = 0; i < numEntrada; i++){ 
         //chama o metodo que trocara as letras A, B e C por numeros
         novaPalavra = substituiExpressaoBoo(entrada[i]); 
         do{
            //chama o metodo que retorna a posissão do ultimo parenteses
            posicao = retornarUltimoParentese(novaPalavra); 
            if(posicao>0){
               //chama o metodos que resolve a expressao
               palavraAux = resolveExpressaoBoo(novaPalavra, posicao); 
               novaPalavra = palavraAux;
            }
         }while(posicao>0);

         MyIO.println(novaPalavra);
      }
   }
}