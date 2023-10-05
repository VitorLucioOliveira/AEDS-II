public class Fatorial {

    public static void main(String[] args) {
        int resultado = fatorial(5);
        System.out.println("O fatorial é: " + resultado);
    }

    static int fatorial(int i) {
        int resp;

        if (i == 1) {
            resp = 1;
        } else {
            resp = i * fatorial(i - 1);
        }

        return resp;
    }
}
