import java.io.*;
import java.net.URL;


public class HTML {
    //função para verificar se a palavra é FIM
    public static boolean ehFim(String palavra){
        return (palavra.charAt(0) == 'F' && palavra.charAt(1) == 'I'&& palavra.charAt(2) == 'M' && palavra.length() == 3);
    }

    //função para fazer leitura de nome do site e da URL do site
    public static void leitura()throws FileNotFoundException{

        try{
            String nomeSite = "teste";
            //while para ler todas a linhas até que seja FIM
            while(!ehFim(nomeSite))  {
                //declarando todas as variaveis para salvar a quantidade de letra e acentos
                String linha;
                int x = 0;
                int a = 0 - 1;
                int e = 0 - 1;
                int i = 0;
                int o = 0;
                int u = 0;
                int aAgudo = 0;
                int eAgudo = 0;
                int iAgudo = 0;
                int oAgudo = 0;
                int uAgudo = 0;
                int aTil = 0;
                int oTil = 0;
                int aCrase = 0;
                int eCrase = 0;
                int iCrase = 0;
                int oCrase = 0;
                int uCrase = 0;
                int aFlexo = 0;
                int eFlexo = 0;
                int iFlexo = 0;
                int oFlexo = 0;
                int uFlexo = 0;
                int consoante = 0;
                int table = 0;
                int br = 0;
                //fazendo a leitura do nome do site
                nomeSite = MyIO.readLine();
                //verificando se o nome é FIM para terminar a leitura
                if(ehFim(nomeSite)){
                    break;
                }
                //lendo a URL
                String endereco = MyIO.readLine();
                //guardando a URL em variavel do tipo URL
                URL url = new URL(endereco);
                //lendo pagina HTML
                BufferedReader leitorURL = new BufferedReader(new InputStreamReader(url.openStream()));
                
                while ((linha = leitorURL.readLine()) != null){
                    //percorrendo todo HTML e salvando quantas letras e acentos em suas respectivas variaveis
                    for (x = 0; x < linha.length(); x++){
                        if (linha.charAt(x) == 'a'){
                            a++;
                        }else if(linha.charAt(x) == 'e'){
                            e++;
                        }else if(linha.charAt(x) == 'i'){
                            i++;
                        }else if(linha.charAt(x) == 'o'){
                            o++;
                        }else if(linha.charAt(x) == 'u'){
                            u++;
                        }else if((int) linha.charAt(x) == 225){
                            aAgudo++;
                        }else if((int) linha.charAt(x) == 233){
                            eAgudo++;
                        }else if((int) linha.charAt(x) == 237){
                            iAgudo++;
                        }else if((int) linha.charAt(x) == 243){
                            oAgudo++;
                        }else if((int) linha.charAt(x) == 250){
                            uAgudo++;
                        }else if((int) linha.charAt(x) == 224){
                            aCrase++;
                        }else if((int) linha.charAt(x) == 232){
                            eCrase++;
                        }else if((int) linha.charAt(x) == 236){
                            iCrase++;
                        }else if((int) linha.charAt(x) == 242){
                            oCrase++;
                        }else if((int) linha.charAt(x) == 249){
                            uCrase++;
                        }else if((int) linha.charAt(x) == 227){
                            aTil++;
                        }else if((int) linha.charAt(x) == 245){
                            oTil++;
                        }else if((int) linha.charAt(x) == 226){
                            aFlexo++;
                        }else if((int) linha.charAt(x) == 234){
                            eFlexo++;
                        }else if ((int) linha.charAt(x) == 238){
                            iFlexo++;
                        }else if ((int) linha.charAt(x) == 244){
                            oFlexo++;
                        }else if ((int) linha.charAt(x) == 251){
                            uFlexo++;
                        }else if(linha.charAt(x) >= 'a' && linha.charAt(x) <= 'z' && linha.charAt(x) != 'a'&& linha.charAt(x) != 'e' && linha.charAt(x) != 'i' && linha.charAt(x) != 'o'&& linha.charAt(x) != 'u'|| linha.charAt(x) >= 'A' && linha.charAt(x) <= 'Z' && linha.charAt(x) != 'A'&& linha.charAt(x) != 'E' && linha.charAt(x) != 'I'&& linha.charAt(x) != 'O' && linha.charAt(x) != 'U'){
                            consoante++;
                        };
                        if (linha.charAt(x) == '<' && linha.charAt(x+1) == 't' && linha.charAt(x+2) == 'a' && linha.charAt(x+3) == 'b' && linha.charAt(x+4) == 'l' && linha.charAt(x+5) == 'e' && linha.charAt(x+6) == '>'){
                            table++;
                        };
                        if(linha.charAt(x) == '<' && linha.charAt(x+1) == 'b' && linha.charAt(x+2) == 'r' && linha.charAt(x+3) == '>'){
                            br++;
                        };
                    }
            
                }

        
                //printando quantidade de letras e acentos
                System.out.print("a" + "(" + a + ")");
                System.out.print(" " +"e" + "(" + e + ")");
                System.out.print(" " +"i" + "(" + i + ")");
                System.out.print(" " +"o" +"(" + o + ")");
                System.out.print(" " +"u" + "(" + u + ")");
                System.out.print(" " +(char)225 + "(" + aAgudo + ")");
                System.out.print(" " +(char)233 + "(" + eAgudo + ")");
                System.out.print(" " +(char)237 + "(" + iAgudo + ")");
                System.out.print(" " +(char)243 + "(" + oAgudo + ")");
                System.out.print(" " +(char)250 + "(" + uAgudo + ")");
                System.out.print(" " +(char)224 + "(" + aCrase + ")");
                System.out.print(" " +(char)232 + "(" + eCrase + ")");
                System.out.print(" " +(char)236 + "(" + iCrase + ")");
                System.out.print(" " +(char)242 + "(" + oCrase + ")");
                System.out.print(" " +(char)249 + "(" + uCrase + ")");
                System.out.print(" " +(char)227 + "(" + aTil + ")");
                System.out.print(" " +(char)245 + "(" + oTil + ")");
                System.out.print(" " +(char)226 + "(" + aFlexo + ")");
                System.out.print(" " +(char)234 + "(" + eFlexo + ")");
                System.out.print(" " +(char)238 + "(" + iFlexo + ")");
                System.out.print(" " +(char)244 + "(" + oFlexo + ")");
                System.out.print(" " +(char)251 + "(" + uFlexo + ")");
                System.out.print(" " +"consoante" + "(" + consoante + ")");
                System.out.print(" " +"<br>" + "(" + br + ")");
                System.out.print(" " +"<table>" + "(" + table + ")");
                System.out.print(" " + nomeSite);
                System.out.printf("\n");

            }
            
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

    }
        
    //MAIN
    public static void main(String[] args) throws FileNotFoundException{
        //chamando função leitura
        leitura();
    }
        
}
