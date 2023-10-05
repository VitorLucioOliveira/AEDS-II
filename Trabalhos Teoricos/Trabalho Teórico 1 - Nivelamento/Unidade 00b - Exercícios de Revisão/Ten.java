public class Ten {

    public static void main(String args[]) {
        int x = 23, y = 23;
        x = x << 1;
        y = y >> 1;
        System.out.println("[" + x + "-" + y + "]");
    }
}

/*x = x << 1;: Isso realiza um deslocamento de bits para a esquerda em x, multiplicando-o por 2. O valor de x após essa operação se torna 46 (23 * 2).

y = y >> 1;: Isso realiza um deslocamento de bits para a direita em y, dividindo-o por 2. O valor de y após essa operação se torna 11 (23 / 2, considerando a divisão de inteiros). */