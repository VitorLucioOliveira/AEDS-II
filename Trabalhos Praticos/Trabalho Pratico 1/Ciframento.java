import java.util.Scanner;

public class Ciframento {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        String frase = scan.nextLine();// pegando a frase

       while (!frase.equalsIgnoreCase("FIM")) {// Ate ser digitado FIM (independente do CAPSLOOK), para o laço
           
            String fraseNova = "";

            for (int i = 0; i < frase.length(); i++) {
                
                if (frase.charAt(i) <=153 && frase.charAt(i) >=0) { 
                
                char xifrado = (char) (frase.charAt(i) + 3);// Garente que n ta na tabela Unicode

                fraseNova += xifrado;}
                
            else {
                
                char xifrado = (char) (frase.charAt(i));// mantem a letra

                fraseNova += xifrado;}

            }

            System.out.println(fraseNova);

             frase = scan.nextLine();
        }
        scan.close();

    }
}
