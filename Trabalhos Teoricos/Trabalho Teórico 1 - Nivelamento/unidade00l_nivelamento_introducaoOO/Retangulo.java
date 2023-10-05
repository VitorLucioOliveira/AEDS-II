/**
 * Retangulo
 */
public class Retangulo {

    private double base;
    private double altura;



    // constrrutores
    Retangulo(){}

    Retangulo(double base, double altura){}


    //setter e getters

    public void setBase(double base)
    {
        this.base=base;
    }

     public void setAltura(double altura)
    {
        this.altura=altura;
    }

    public double getBase()
    {
        return this.base;
    }

     public double getAltura()
    {
        return this.altura;
    }


    //Funções
    public double getArea(double base, double altura)
    {
        double area= base *altura;
        return area;
    }

    public double  getPerimetro(double base, double altura)
    {   
        double perimetro= Math.pow(base, 2)+Math.pow(altura, 2);
        return perimetro;

    }

    public double getDiagoanl(double base, double altura)
    {

        double diagonal= Math.sqrt(Math.pow(base, 2) + Math.pow(altura, 2));
        return diagonal;
    }

















}