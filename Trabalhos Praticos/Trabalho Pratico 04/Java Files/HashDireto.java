import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//-----------------------CLASSE JOGADOR---------------------------//
class Jogador {

    // instancias
    private int id;
    private String nome;
    private int altura;
    private int peso;
    private String universidade;
    private int anoNascimento;
    private String cidadeNascimento;
    private String estadoNascimento;

    // construtores

    public Jogador(String pedido, File tabela) {
        try {
            Scanner scan = new Scanner(tabela);

            while (scan.hasNextLine()) {
                String linha = scan.nextLine(); // le a linha
                String[] elementos = linha.split(",", -1); // divide a linha pela virgula e faz um array

                for (int i = 0; i < elementos.length; i++) {
                    if (elementos[i].isEmpty()) {
                        elementos[i] = "nao informado";
                    }
                }

                if (pedido.equals(elementos[0]) && elementos.length == 8) { // olha o id do pedido feito e completa as
                                                                            // informações
                    setId(Integer.parseInt(elementos[0]));
                    setNome(elementos[1]);
                    setAltura(Integer.parseInt(elementos[2]));
                    setPeso(Integer.parseInt(elementos[3]));
                    setUniversidade(elementos[4]);
                    setAnoNascimento(Integer.parseInt(elementos[5]));
                    setCidadeNascimento(elementos[6]);
                    setEstadoNascimento(elementos[7]);
                }
            }

            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();

            System.out.println("arquivo não encontrado");
        }
    }

    public Jogador() {
    }

    // funções set

    public void setId(int id) {
        this.id = id;
    }

    private void setNome(String nome) {
        this.nome = nome;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public void setUniversidade(String universidade) {
        this.universidade = universidade;
    }

    public void setAnoNascimento(int anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public void setCidadeNascimento(String cidadeNascimento) {
        this.cidadeNascimento = cidadeNascimento;
    }

    public void setEstadoNascimento(String estadoNascimento) {
        this.estadoNascimento = estadoNascimento;
    }

    // funções gets

    public int getId() {
        return this.id;
    }

    public int getAltura() {
        return this.altura;
    }

    public String getNome() {
        return this.nome;
    }

    public int getPeso() {
        return this.peso;
    }

    public String getUniversidade() {
        return this.universidade;
    }

    public int getAnoNascimento() {
        return this.anoNascimento;
    }

    public String getCidadeNascimento() {
        return this.cidadeNascimento;
    }

    public String getEstadoNascimento() {
        return this.estadoNascimento;
    }

    // funções normais

    public String dados() { // print todos os dados do jogador
        return (

        " ## " +
                getNome() +
                " ## " +
                getAltura() +
                " ## " +
                getPeso() +
                " ## " +
                getAnoNascimento() +
                " ## " +
                getUniversidade() +
                " ## " +
                getCidadeNascimento() +
                " ## " +
                getEstadoNascimento() +
                " ##");
    }

    public int getMod() {return (this.altura % 21);}
    
}


//-----------------------CLASSE HASH---------------------------//
class Hash{

    /*
     * t1 = tamanho da tabela;
     * t2 = tamanho da reserva;
     * tt = tamanho total somando as duas;
     * reserva = quantidade de elementos na reserva;
    */
    Jogador[] tabela;
    int t1, t2, tt, reserva;


    public Hash(){
        this(21, 9);
    }

    public Hash(int t1, int t2){
        this.t1 = t1;
        this.t2 = t2;
        this.tt = t1 + t2;
        this.tabela = new Jogador[tt];
        this.reserva = 0;
    }

    public boolean inserir(Jogador jogador)
    {   
        /*
         * 1º: Se o jogador não for nulo, definimos o a posição baseada no mod da altura;
         * 2º: Apos definar a posição, verificamos se ela esta livre na tabela, se tiver inserimos;
         * 3º: Se não tiver livre, colocamos ela na parte das posições de reserva;
         * Resultado: Retornaremos True se a inserção funcionou e False se falhou;
         */
        boolean resp = false;
        
        //1º
        if (jogador != null) {
            
            int pos = jogador.getMod();

            //2º
            if(tabela[pos]==null)
            {
                tabela[pos]=jogador;
                resp=true;
            }
            //3º
            else if(reserva<t2)
            {
                tabela[t1+reserva] = jogador;
                reserva++;
                resp = true;
            }
            
        }
        
        return resp;
    }

    public void pesquisar(String nome)
    {
        if(pesquisar2(nome))
        {
            System.out.println(nome+" SIM");
        }
        else{
            System.out.println(nome+ " NAO");
        }
    }
    
    private boolean pesquisar2(String nome)
    {
        boolean resp = false;

        for(int i=0; i<tt; i++)
        {
            if(tabela[i]!= null)
            {
                if(tabela[i].getNome().equals(nome))
                {
                    resp=true;
                }
            }
        }

        return resp;
    }
}

//-----------------------MAIN---------------------------//
public class HashDireto {


    public static void main(String[] args) throws Exception {

        // variaveis e objetos
        Scanner scan = new Scanner(System.in);
        File tabela = new File("/tmp/players.csv");
        Hash hash = new Hash();


        /* Leitura do jogador e incerção na Lista */
        String pedido = scan.nextLine();
        while (!pedido.equalsIgnoreCase("FIM")) {
            Jogador player = new Jogador(pedido, tabela); // cria um jogador novo e leva o pedido para o construtor
            hash.inserir(player);
            pedido = scan.nextLine();
        }

        pedido = scan.nextLine();
        while (!pedido.equalsIgnoreCase("FIM")) {
            hash.pesquisar(pedido);
            pedido = scan.nextLine();
        }

        scan.close();
        //SO PRA  MUDAR O COMMIT
    }
}