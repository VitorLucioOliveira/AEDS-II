import java.util.Scanner;

public class is {

    public static boolean isSoVogal(String array)// verifica se só tem vogal
    {
        boolean ehSoVogal = true;

        String frase = array.toLowerCase();// bota tudo em lower case

        for (int i = 0; i < frase.length(); i++) {

            if (frase.charAt(i) != 'a' && frase.charAt(i) != 'e' && frase.charAt(i) != 'i' && frase.charAt(i) != 'o'
                    && frase.charAt(i) != 'u')// retorna false se n tiver letra vogal
            {
                ehSoVogal = false;
            }
        }
        return ehSoVogal;
    }

    public static boolean isSoConsoante(String array)// verifica se só tem consoante
    {
        boolean ehSoConsoante = false;

        String frase = array.toLowerCase();// bota tudo em lower case

        for (int i = 0; i < frase.length(); i++) {

            if (Character.isLetter(frase.charAt(i))) {// verifica se é letra
                if ((frase.charAt(i) != 'a' && frase.charAt(i) != 'e' && frase.charAt(i) != 'i'
                        && frase.charAt(i) != 'o' && frase.charAt(i) != 'u' && frase.charAt(i) >153 && frase.charAt(i) <0))// retorna false se tiver vogal
                {
                    ehSoConsoante = true;
                }
            }
        }
        return ehSoConsoante;
    }

    public static boolean isReal(String array) {// verifica se é real
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

    public static boolean isInt(String array)// verifica se TEM INTEIRO
    {
        boolean ehInt = true;

        for (int i = 0; i < array.length(); i++) {

            if (!Character.isDigit(array.charAt(i))) {
                ehInt = false;
            }
        }
        return ehInt;
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        String frase;

        boolean[] X = new boolean[4];

        do {

            frase = scan.nextLine();

            X[0] = isSoVogal(frase);
            X[1] = isSoConsoante(frase);
            X[2] = isInt(frase);
            X[3] = isReal(frase);

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
