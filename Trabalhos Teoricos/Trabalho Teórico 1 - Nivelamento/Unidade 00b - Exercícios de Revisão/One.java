public class One{

public  static void main(String args[])
{
    int[] array={1,2,3,4,5,6};
    int x= 3;
    
    boolean temX = haveX(array, x);
    
    System.out.println("Tem  " + x + " ? " + temX);

}/*Main usada para testes */

public static boolean haveX(int[] array, int x)
{
    boolean tem=false;

    for(int num:array)
    {
        if(num==x)
        {
            tem=true;
        }
    }
    return tem;
}

}