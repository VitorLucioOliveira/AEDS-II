import java.util.Random;
import java.util.Scanner;

public class Aleatorio {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        Random gerador = new Random();

        gerador.setSeed(4);

        String frase = scan.nextLine();

        while(!frase.equalsIgnoreCase("FIM"))// Ate ser digitado FIM (independente do CAPSLOOK), para o laço
        {

            char random1 = ((char)('a' + (Math.abs(gerador.nextInt()) % 26)));// numero aleatorio de 1 a 26 e depois somado pelo 97(a em uinicode), garantindo que é minusculo
            char random2 = ((char)('a' + (Math.abs(gerador.nextInt()) % 26)));

            String fraseNova = frase.replace(random1, random2);

            System.out.println(fraseNova);



            frase = scan.nextLine();
        }















        scan.close();
    }
}
