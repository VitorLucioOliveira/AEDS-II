public class Two {
    

    public static void main(String args[])
    {

        int[] array = {1,2,3,4,5,6,7,8,9};
        MaiorxMenor(array);


    }/*Main usada para teste */


    public static void MaiorxMenor(int[] array)
    {

        int maior= array[0];
        int menor= array[0];

        for(int num:array)
        {
            if( maior<num){maior=num;}
            if(menor>num){menor=num;}

        }

        System.out.println("O menor numero eh: "+menor);
        System.out.println("O maior numero eh: "+ maior);



    }

















}
