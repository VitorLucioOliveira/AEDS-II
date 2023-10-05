import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



public class ExemploBufferedReader {
    
    public static void main(String args[]) throws IOException
    {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
       
        System.out.print("Me fala uma String: ");
        String string = read.readLine();

        System.out.print("Me fala um Inteiro: ");
        int inteiro = Integer.parseInt(read.readLine());

        System.out.print("Me fala um Real: ");
        Double real = Double.parseDouble(read.readLine());

        System.out.println("-----------------------");
        System.out.println("String: "+string+ " Inteiro: "+inteiro+" Real: "+real);

    }
}
