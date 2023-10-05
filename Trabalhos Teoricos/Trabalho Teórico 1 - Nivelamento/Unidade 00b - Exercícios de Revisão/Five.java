public class Five {
    
    public static void main(String args[])
    {
    }

    boolean isConsoante(String s, int n){
        boolean resp= true;


        if (n<=s.length())// ERRO corrigido  (n != s.length()))
        {
            if (s.charAt(n)<'0' || s.charAt(n)>'9')// verifica se não é numero
            {

                 if (isVogal (s.charAt(n))){resp= false;} // vogais maiusculas e minuscula
        
                else{
                
                resp=isConsoante(s, n+1);}
            } 
        
        
            else { resp=false;}
        }
        
        
        return resp;
        }
        
        boolean isVogal (char c){
            c = Character.toUpperCase(c);
            return (c =='A' || c =='E' || c =='I' || c =='O' || c =='U');
            }
}

/*o ERRRO DESSA FUNÇÃO É QUE N TEM NADA QUE GARANTE QUE O N SEJA MAIOR QUE O NUMEMRO DE ELEMENTOS DA STRING */
