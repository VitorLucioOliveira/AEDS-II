import java.util.Scanner;

public class isRecursivo {

    public static boolean isSoVogal(String array, int index)// verifica se só tem vogal
    {
        boolean ehSoVogal = true;

        String frase = array.toLowerCase();// bota tudo em lower case

       if (index<array.length()) {

            if (frase.charAt(index) != 'a' && frase.charAt(index) != 'e' && frase.charAt(index) != 'i' && frase.charAt(index) != 'o'
                    && frase.charAt(index) != 'u')// retorna false se n tiver letra vogal
            {
                ehSoVogal = false;
            }

            isSoVogal(array, index+1);
        }
        return ehSoVogal;
    }

    public static boolean isSoConsoante(String array, int index)// verifica se só tem consoante
    {
        boolean ehSoConsoante = false;

        String frase = array.toLowerCase();// bota tudo em lower case

         if (index<array.length()) {

            if (Character.isLetter(frase.charAt(index))) {// verifica se é letra
                if ((frase.charAt(index) != 'a' && frase.charAt(index) != 'e' && frase.charAt(index) != 'i'
                        && frase.charAt(index) != 'o' && frase.charAt(index) != 'u' && frase.charAt(index) >153 && frase.charAt(index) <0))// retorna false se tiver vogal
                {
                    ehSoConsoante = true;
                }

                isSoConsoante(array, index+1);
            }
        }
        return ehSoConsoante;
    }

    public static boolean isReal(String array, int index) {// verifica se é real
        boolean ehReal;
    
        String frase = array.toLowerCase();
    
        try {
            Double.parseDouble(frase);
            ehReal = true;
        } catch (NumberFormatException e) {
            ehReal = false;
        }
    
        return ehReal;
    }

    public static boolean isInt(String array, int index)// verifica se TEM INTEIRO
    {
        boolean ehInt = true;

         if (index<array.length()) {

            if (!Character.isDigit(array.charAt(index))) {
                ehInt = false;
            }
            isInt( array, index+1);
        }
        return ehInt;
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        String frase;

        boolean[] X = new boolean[4];

        do {

            frase = scan.nextLine();

            X[0] = isSoVogal(frase, 0);
            X[1] = isSoConsoante(frase, 0);
            X[2] = isInt(frase, 0);
            X[3] = isReal(frase, 0);

            for (int i = 0; i < 4; i++) {

                if (frase.equalsIgnoreCase("FIM"))
                {
                    scan.close();
                    return;
                }
                
                if (X[i]) {
                    System.out.print("SIM ");
                }

                if (!X[i]) {
                    System.out.print("NAO ");
                }
                
            }

             System.out.println("");

        } while (!frase.equalsIgnoreCase("FIM"));

        scan.close();
    }

}
