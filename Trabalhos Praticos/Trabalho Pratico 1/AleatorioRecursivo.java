import java.util.Random;
import java.util.Scanner;

public class AleatorioRecursivo {
    
    
    
    public static void Alele(String frase) {

         Random gerador = new Random();

            gerador.setSeed(4);

            char random1 = ((char)('a' + (Math.abs(gerador.nextInt()) % 26)));// numero aleatorio de 1 a 26 e depois somado pelo 97(a em uinicode), garantindo que é minusculo
            char random2 = ((char)('a' + (Math.abs(gerador.nextInt()) % 26)));

            String fraseNova = frase.replace(random1, random2);

            System.out.println(fraseNova);// print a frase nova

              Scanner scan = new Scanner(System.in); // refaz a main pra ter recursividade

              frase = scan.nextLine();// refaz a main pra ter recursividade

             if (!frase.equalsIgnoreCase("FIM")) {// Ate ser digitado FIM (independente do CAPSLOOK), para o laço
            
                Alele(frase);

                scan.close();
        }

      
    }
    
    
    
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
       

        String frase = scan.nextLine();

   

            Alele(frase);

            
       

        scan.close();
    }
}
