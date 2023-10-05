/*• Um estudante de Algoritmos e Estruturas de Dados (em JAVA) implementou uma
classe Hora, cujo construtor recebe e armazena uma hora, minuto e segundo. O que
acontece se a classe X abaixo for colocada na mesma pasta que a classe Hora? */

class X {
 public static void main (String[] args){
 Hora h1 = new Hora(12, 30, 30);
 Hora h2 = new Hora(12, 30, 30);
 if (h1 == h2)
 System.out.println(“Identicos!”);
 else
 System.out.println(“Diferentes!”);
 }
}


/*B) Escreve na tela “Diferentes”.

A razão para isso é que a comparação usando == entre objetos em Java verifica se os dois objetos são exatamente os mesmos na 
memória, ou seja, se eles referenciam exatamente a mesma instância de objeto. No caso do código dado, embora h1 e h2 tenham 
os mesmos valores (12 horas, 30 minutos e 30 segundos), eles são instâncias diferentes da classe Hora e, portanto,
 a comparação h1 == h2 resultará em falso. */