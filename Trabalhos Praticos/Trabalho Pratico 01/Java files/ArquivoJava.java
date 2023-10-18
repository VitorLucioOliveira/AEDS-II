
import java.net.URL;
import java.util.Scanner;
import java.io.*;



public class ArquivoJava {
    
    public static void leituraarq(int n){
        
        RandomAccessFile file;
        double insercao;
        
        if(n>=0){
            
            try{
                file = new RandomAccessFile("arq.txt", "rw");
                for(int i = 0;i < n;i++){
                    insercao = MyIO.readDouble();

                    ////inserindo dados no arq
                    file.writeDouble(insercao);
                }

                for(int i = 0; i < n;i++){
                    long pos = file.getFilePointer();

                    //leitura dos prox
                    file.seek(pos-8);
                    double x = file.readDouble();
                    file.seek(pos - 8);
                    int y = (int)x;

                    if (x == y) {
                        MyIO.println (y);
                    }else{
                        MyIO.println (x);
                    } 
                }

                file.close();

            }catch(IOException Erro){
                MyIO.print("Erro!");
            }
        }
    }
            
    
    public static void main(String[] args) {
        int n = MyIO.readInt();

        leituraarq(n);
    }
}
