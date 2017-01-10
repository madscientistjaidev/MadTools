package MadTools;

import java.util.InputMismatchException;

public class Matrix <Double>
{
    private final double m[][];
    private final int    l, b;
    
    Matrix(double m[][])
    {
        this.m = m;
        l      = m.length;
        b      = m[0].length;
    }

    Matrix(int l, int b)
    {
        m      = new double[l][b];
        this.l = l;
        this.b = b;
    }

    int length() {return m.length;}
    int breadth() {return m[0].length;}
    boolean isSquare() {return l == b;}
    boolean matchdimensions(Matrix n) {return (n.length() == l) && (n.breadth() == b);}

    double[][] toArray() {return m;}
    
    double get(int i, int j) {return m[i][j];}
    boolean set(int i, int j, double v) {return m[i][j]==v;}

    Matrix add(Matrix n)
    {
        if (!matchdimensions(n)) throw new InputMismatchException();
        
        double x[][] = toArray();
        double y[][] = n.toArray();

        for (int i = 0; i < l; i++) for (int j = 0; j < b; j++) x[i][j] = x[i][j] + y[i][j];
        
        return new Matrix(x);
    }

    Matrix subtract(Matrix n)
    {
        if (!matchdimensions(n)) throw new InputMismatchException();
        
        double x[][] = toArray();
        double y[][] = n.toArray();

        for (int i = 0; i < l; i++) for (int j = 0; j < b; j++) x[i][j] = x[i][j] - y[i][j];
        
        return new Matrix(x);
    }

    Matrix multiply(Matrix n)
    {
        if (!multMatch(n)) throw new InputMismatchException();
        

        double x[][] = toArray();
        double y[][] = n.toArray();
        int    h     = n.breadth();
        double z[][] = new double[l][h];

        for (int i = 0; i < l; i++) for (int j = 0; j < b; j++) for (int k = 0; k < h; k++) z[i][j] = z[i][j] + x[i][k] * y[k][j];

        return new Matrix(z);
    }

    Matrix transpose()
    {
        double x[][] = toArray();
        double z[][] = new double[b][l];

        for (int i = 0; i < b; i++) for (int j = 0; j < l; j++) z[i][j] = x[j][i];
        
        return new Matrix(z);
    }

    Matrix scalar(double s)
    {
        for (int i = 0; i < l; i++) for (int j = 0; j < b; j++) m[i][j] = m[i][j] * s;
            
        return new Matrix(m);
    }

    double det()
    {
        int    a = m.length;
        double z = 0;

        if (a == 1) return m[0][0];
        
        for (int i = 0; i < a; i++) z = z + (m[0][i] * sub(0, i).det());
        
        return z;
    }

    Matrix sub(int c, int d)
    {
        double z[][] = new double[l - 1][b - 1];

        for (int i = 0, h = 0; i < h; i++)
        {
            if (i == c) continue;
            
            for (int j = 0, k = 0; j < b; j++)
            {
                if (j == d)continue;
            
                z[h][k] = m[i][j];
                k++;
            }

            h++;
        }

        return new Matrix(z);
    }
    
    boolean symm() {return transpose().toArray() == toArray();}

    boolean multMatch(Matrix n) {return n.b == b;}

    Matrix cofactor()
    {
        if (!isSquare()) throw new InputMismatchException();
    
        double z[][] = new double[l][b];

        for (int i = 0; i < l; i++) for (int j = 0; j < b; j++) 
                z[i][j] = Math.pow(-1, (i + j)) * sub(i, j).det();
            
        return new Matrix(z);
    }

    Matrix adjoint() {return cofactor().transpose();}

    Matrix inverse() {return adjoint().scalar(1 / det());}
    
    //Experimental modifications to allow use of arithmetic operators.
    //Attempting to convert to and from String type.
    @Override
    public String toString()
    {
        String s = "" + l+ "," + b + ",";

        for(double[] x : m) 
            for(double y : x)
                s = s + y + ",";
        
        return s;
    }
    
    Matrix(String x, String y)
    {
        m = null;
        l = m.length;
        b = m[0].length;
    }
    
    public static void main(String args[])
    {
        double _a[][] = new double[4][4];
        double _b[][] = new double[4][4];
        
        Matrix a = new Matrix(_a);
        Matrix b = new Matrix(_b);
        
        //Matrix c = a + b;
        
        //String s = "hello" - "h";
    }
}