import java.util.Scanner;

public class CiframentoRecursivo {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        String frase = scan.nextLine();// pegando a frase

       while (!frase.equalsIgnoreCase("FIM")) {// Ate ser digitado FIM (independente do CAPSLOOK), para o laço
           
           cifrar(frase, 0);// index mantem a recursividade
           System.out.println("");



            frase = scan.nextLine();
        }
        scan.close();

    }

    public static void cifrar(String frase, int index){
         String fraseNova = "";


         if (index < frase.length()) {
            char xifrado;
                
        
           if (frase.charAt(index) <=153 && frase.charAt(index) >=0) { // Garente que n ta na tabela Unicode

                    
                xifrado = (char) (frase.charAt(index) + 3); // pula 3 casas
                
                fraseNova += xifrado;}
                
            else { xifrado = (char) (frase.charAt(index));

                fraseNova += xifrado;}

            

            

            System.out.print(fraseNova);

            cifrar(frase, index+1);// manter a recursividade

            

        }
    }
             
    }


