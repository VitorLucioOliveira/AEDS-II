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

    public Jogador(String nome) {
        
        this.nome = nome;
        this.altura = 0;
        this.anoNascimento = 0;
        this.cidadeNascimento= "0";
        this.estadoNascimento= "0";
        this.id =0;
        this.peso= 0;
        this.universidade= "0";


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
}

// -----------------------CLASSE NO---------------------------//
class NoAN {
  public boolean cor;
  public Jogador elemento;
  public NoAN esq, dir;

  public NoAN() {
    this(null);
  }

  public NoAN(Jogador elemento) {
    this(elemento, false, null, null);
  }

  public NoAN(Jogador elemento, boolean cor) {
    this(elemento, cor, null, null);
  }

  public NoAN(Jogador elemento, boolean cor, NoAN esq, NoAN dir) {
    this.cor = cor;
    this.elemento = elemento;
    this.esq = esq;
    this.dir = dir;
  }
}

// -----------------------CLASSE ArvoreBinaria---------------------------//
class Alvenegra {

   private NoAN raiz;

    public Alvenegra() {
        raiz = null;
    }




    // INSERIR
    public void inserir(Jogador jogador) throws Exception {
        
        /*
         * 1º: Se a arvore estiver vazia, crie a raiz
         * 2º: Se a arvore tiver so raiz, bota na esq ou dir
         * 3º: Se a arvore tiver um filho[dir], julga e bota de acordo
         
         //3.obs: trocaremos o valor da raiz para balancear, deixando altura 1//
         
         * 4º: Definimos a cor dos filhos para VERMELHO
         * 5º: Se a arvore tiver um filho[esq], julga e bota de acordo
         
         //5.obs: trocaremos o valor da raiz para balancear, deixando altura 1//
         
         * 6º: Definimos a cor dos filhos para VERMELHO
         * 7º: Se a arvore tiver mais que 3 nos chama um inserir "especial"
        */

        //1º
        if(raiz==null)
        {
            raiz= new NoAN(jogador);
        }
        
        //2º
        else if (raiz.dir==null && raiz.esq==null) 
        {

            if(jogador.getNome().compareTo(raiz.elemento.getNome())<0)
            {
                raiz.esq= new NoAN(jogador);//insere esq
            }
            else
            {
                raiz.dir = new NoAN(jogador);//insere dir
            }
            
        }

        //3º
        else if(raiz.esq==null)
        {
            if(jogador.getNome().compareTo(raiz.elemento.getNome())<0)
            {
                raiz.esq= new NoAN(jogador);
            }
            else if (jogador.getNome().compareTo(raiz.dir.elemento.getNome())<0)
            {
                //3.obs
                raiz.esq = new NoAN(raiz.elemento);
                raiz.elemento = jogador;
            }
            else
            {   
                //3.obs
                raiz.esq = new NoAN(raiz.elemento);//raiz desce[nova esq]
                raiz.elemento = raiz.dir.elemento;//direita sobe[nova raiz]
                raiz.dir.elemento = jogador;//nova dir
            }
            
            //4º
            raiz.esq.cor = raiz.dir.cor =false;


        }
        
        //5º
        else if(raiz.dir==null)
        {
            if(jogador.getNome().compareTo(raiz.elemento.getNome())>0)
            {
                raiz.dir= new NoAN(jogador);
            }
            else if (jogador.getNome().compareTo(raiz.esq.elemento.getNome())>0)
            {
                //5.obs
                raiz.dir = new NoAN(raiz.elemento);
                raiz.elemento = jogador;
            }
            else
            {   
                //5.obs
                raiz.dir = new NoAN(raiz.elemento);//raiz desce[novo dir]
                raiz.elemento = raiz.esq.elemento;//esquerda sobe[nova raiz]
                raiz.esq.elemento = jogador;//nova esq
            }
            
            //6º
            raiz.esq.cor = raiz.dir.cor =false;


        }

        else//7º
        {
            inserir(jogador,null, null, null, raiz);
        }

        raiz.cor=false;

    }

     private void balancear(NoAN bisavo, NoAN avo, NoAN pai, NoAN i) 
    {
        
        /*
         * 1º: Se o Pai for PRETO, reaqulibbramos a arvore rotacionando o Avo
         * 2º: Escolher qual rotação DIREITA fazer;
         * 3º: Escolher qual rotação ESQUERDA fazer;
        */
        //1º
        if(pai.cor==true)
        {

            //2º - ROTAÇÕES DIREITA ou ESQUERDA-DIREITA
            if(pai.elemento.getNome().compareTo(avo.elemento.getNome()) > 0){
                
                if(i.elemento.getNome().compareTo(pai.elemento.getNome()) > 0)
                {
                    avo = rotacaoESQ(avo);// simples
                }
                else
                {
                     avo = rotacaoDirESQ(avo);// dupla
                }
            }

            //3º - ROTAÇÕES ESQUERDA ou DIREITA-ESQUERDA
            else
            {
                
                if(i.elemento.getNome().compareTo(pai.elemento.getNome()) < 0)
                {
                    avo = rotacaoDIR(avo);// simples
                }
                else
                {
                      avo = rotacaoEsqDIR(avo);// dupla
                }
            }
        }

        if (bisavo == null) 
        {
            raiz = avo;
        } 
        else if (avo.elemento.getNome().compareTo(bisavo.elemento.getNome()) < 0) {
            bisavo.esq = avo;
        } 
        else 
        {
            bisavo.dir = avo;
        }
        
        avo.cor = false;
        
        if(avo.esq!=null){avo.esq.cor= true;}
        if(avo.dir!=null){avo.dir.cor= true;}
    } 
        

    
    //ROTAÇÕES
    
    private NoAN rotacaoESQ(NoAN no) {
        
        NoAN noDir = no.dir;
        NoAN noDirEsq = noDir.esq;
        
        noDir = no;
        no.dir = noDirEsq;
        
        return noDir;
    }
    
    private NoAN rotacaoDIR(NoAN no) {
        NoAN noEsq = no.esq;
        NoAN noEsqDir = noEsq.dir;

        noEsq.dir = no;
        no.esq = noEsqDir;

        return noEsq;
    }

    private NoAN rotacaoDirESQ(NoAN no) {
        no.dir = rotacaoDIR(no.dir);
        return rotacaoESQ(no);
    }

    private NoAN rotacaoEsqDIR(NoAN no) {
        no.esq = rotacaoESQ(no.esq);
        return rotacaoDIR(no);
    }


    private void inserir(Jogador jogador, NoAN bisavo, NoAN avo, NoAN pai, NoAN i) throws Exception {
        /*
         * 1º: Se o No i for nulo, insiro baseado no No PAI;
         
         //1.obs: O No criado vai ser PRETO, e depois vamos balancear//

         * 2º: Caso o No não seja nulo;
         * 3º: Se o No tiver 2 filhos PRETOS, o No fica preto e os filhos VERMELHOS;
         * 4º: Se o No for igual a raiz, o No fica VERMELHO e se não, balanceamos;
        */
        
        // 1º - CASO BASE
         if (i == null)
        {
             if(jogador.getNome().compareTo(pai.elemento.getNome())<0)
             {
                i = pai.esq= new NoAN(jogador, true);
             }
             else
             {
                i = pai.dir= new NoAN(jogador, true);
             }
             
             //1.obs
             if(pai.cor==true)
             {
                balancear(bisavo,avo,pai,i);
             }
        }
        
        //2º - CASO BASE
        else
        {   
            //3º
            if(i.esq!=null && i.dir!=null && i.esq.cor==true && i.dir.cor==true)
            {
                i.cor=true;
                i.esq.cor= i.dir.cor =false;

                //4º
                 if(i==raiz)
                {
                i.cor=false;
                }
                else if (pai.cor==true)
                {
                balancear(bisavo, avo, pai, i);
                }
            }
        
        //CHAMADAS RECURSIVAS PARA CHEGAR NO CASO BASE
        if (jogador.getNome().compareTo(i.elemento.getNome()) < 0) {// 2º
           
            inserir(jogador,avo,pai,i ,i.esq);

        } else if (jogador.getNome().compareTo(i.elemento.getNome()) > 0) {// 3º
            
            inserir(jogador,avo,pai,i , i.dir);

        } else {
            throw new Exception("Erro ao inserir (elemento repetido)");
        }
    }
    }
 
    // PESQUISAR
    public void pesquisar(Jogador jogador) {
        
        System.out.print(" raiz");
        if( pesquisar(jogador, raiz)){System.out.println(" SIM");}
        else{System.out.println(" NAO");}
       
    }
    

    private boolean pesquisar(Jogador jogador, NoAN i) {
        /*
         * 1º: Verifica se a arvore não esta vazia;
         * 2º: Se o nome do elemento for o mesmo da Raiz, returna true;
         * 3º: Se o nome do elemento for menor da Raiz, vai pra esquerda da arvore;
         * 4º: Se o nome do elemento for meaior da Raiz, vai pra direita da arvore;
         * OBS: se não achar nada, returna false;
         */
        boolean resp = false;

        if (i == null) {// 1º
            resp = false;
        } else if (jogador.getNome().compareTo(i.elemento.getNome()) == 0)// 2º
        {
            resp = true;
        } else if (jogador.getNome().compareTo(i.elemento.getNome()) < 0) // 3º
        {   System.out.print(" esq");
            resp = pesquisar(jogador, i.esq);
            
        } else// 4º
        {   
            System.out.print(" dir");
            resp = pesquisar(jogador, i.dir);
        }

        return resp;
    }

    

    // MOSTRAR-CAMINHAR
    public void mostrar() {
        mostrar(raiz);
    }

    private void mostrar(NoAN i) {
        /*
         * 1º: printa o nome da celula
         * 2º: vai pra celula mais a esquerda possivel, e recursivamente printa;
         * 3º: vai pra celula mais a direita possivel, e recursivamente printa;
         */

        if (i != null) {
            System.out.println(i.elemento.getNome());// 1º
            mostrar(i.esq);// 2º
            mostrar(i.dir);// 3°
        }
    }



}

// -------------------Main---------------------------//
public class ArvoreAlvinegra {
    public static void main(String[] args) throws Exception {
        
        //variaveis e objetos
        Scanner scan = new Scanner(System.in);
        File tabela = new File("/tmp/players.csv");
        Alvenegra arvore = new Alvenegra();


        /*Leitura do jogador e incerção na Arvore */
        String pedido = scan.nextLine();
        while (!pedido.equalsIgnoreCase("FIM")) {
            Jogador player = new Jogador(pedido, tabela); // cria um jogador novo e leva o pedido para o construtor
            arvore.inserir(player);// inserir o jogador na Arvore
            pedido = scan.nextLine();
        }

        pedido = scan.nextLine();
        while (!pedido.equalsIgnoreCase("FIM")) {
            Jogador player = new Jogador(pedido);
            System.out.print(player.getNome());
            arvore.pesquisar(player);
           
            pedido = scan.nextLine();
        }
        scan.close();
    }
}
