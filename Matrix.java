package MadTools;

import java.util.InputMismatchException;

public class Matrix <Double>
{
    private final double m[][];
    private final int    l, b;
    
	/**
	 * Initialize from 2D array.
	 * @param m 
	 */
    Matrix(double m[][])
    {
        this.m = m;
        l      = m.length;
        b      = m[0].length;
    }

	/**
	 * Initialize blank Matrix of size l x b.
	 * @param l
	 * @param b 
	 */
    Matrix(int l, int b)
    {
        m      = new double[l][b];
        this.l = l;
        this.b = b;
    }

	/**
	 * Returns length.
	 * @return 
	 */
    int length() {return m.length;}
    
	/**
	 * Returns breadth.
	 * @return 
	 */
	int breadth() {return m[0].length;}
    
	/**
	 * Checks if Matrix is square.
	 * @return 
	 */
	boolean isSquare() {return l == b;}
    
	/**
	 * Checks if dimensions of Matrices match.
	 * @param n
	 * @return 
	 */
	boolean matchdimensions(Matrix n) {return (n.length() == l) && (n.breadth() == b);}
    
	/**
	 * Checks if dimensions match numbers.
	 * @param x
	 * @param y
	 * @return 
	 */
	boolean matchdimensions(int x, int y) {return (x == l) && (y == b);}

	/**
	 * Returns Matrix as 2D array.
	 * @return 
	 */
    double[][] toArray() {return m;}
    
	/**
	 * Gets element.
	 * @param i
	 * @param j
	 * @return 
	 */
    double get(int i, int j) {return m[i][j];}
    
	/**
	 * Sets element.
	 * @param i
	 * @param j
	 * @param v
	 * @return 
	 */
	boolean set(int i, int j, double v) {return m[i][j]==v;}

	/**
	 * Adds Matrices.
	 * @param n
	 * @return 
	 */
    Matrix add(Matrix n)
    {
        if (!matchdimensions(n)) throw new InputMismatchException();
        
        double x[][] = toArray();
        double y[][] = n.toArray();

        for (int i = 0; i < l; i++) for (int j = 0; j < b; j++) x[i][j] += y[i][j];
        
        return new Matrix(x);
    }

	/**
	 * Subtracts Matrices.
	 * @param n
	 * @return 
	 */
    Matrix subtract(Matrix n)
    {
        if (!matchdimensions(n)) throw new InputMismatchException();
        
        double x[][] = toArray();
        double y[][] = n.toArray();

        for (int i = 0; i < l; i++) for (int j = 0; j < b; j++) x[i][j] -= y[i][j];
        
        return new Matrix(x);
    }

	/**
	 * Multiplies Matrices.
	 * @param n
	 * @return 
	 */
    Matrix multiply(Matrix n)
    {
        if (!multMatch(n)) throw new InputMismatchException();

        double x[][] = toArray();
        double y[][] = n.toArray();
        int    h     = n.breadth();
        double z[][] = new double[l][h];

        for (int i = 0; i < l; i++) for (int j = 0; j < b; j++) for (int k = 0; k < h; k++) z[i][j] += x[i][k] * y[k][j];

        return new Matrix(z);
    }

	/**
	 * Transposes Matrix.
	 * @return 
	 */
    Matrix transpose()
    {
        double x[][] = toArray();
        double z[][] = new double[b][l];

        for (int i = 0; i < b; i++) for (int j = 0; j < l; j++) z[i][j] = x[j][i];
        
        return new Matrix(z);
    }

	/**
	 * Multiplies Matrix by scalar.
	 * @param s
	 * @return 
	 */
    Matrix scalar(double s)
    {
        for (int i = 0; i < l; i++) for (int j = 0; j < b; j++) m[i][j] *= s;
            
        return new Matrix(m);
    }

	/**
	 * Calculates determinant of Matrix.
	 * @return 
	 */
    double det()
    {
        int    a = m.length;
        double z = 0;

        if (a == 1) return m[0][0];
        
        for (int i = 0; i < a; i++) z += (m[0][i] * sub(0, i).det());
        
        return z;
    }

	/**
	 * Returns sub Matrix.
	 * @param c
	 * @param d
	 * @return 
	 */
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
    
	/**
	 * Checks if Matrix is symmetric.
	 * @return 
	 */
    boolean symm() {return transpose().toArray() == toArray();}

	/**
	 * Checks if dimensions are suitable for multiplication.
	 * @param n
	 * @return 
	 */
    boolean multMatch(Matrix n) {return n.b == b;}

	/**
	 * Calculates cofactor Matrix.
	 * @return 
	 */
    Matrix cofactor()
    {
        if (!isSquare()) throw new InputMismatchException();
    
        double z[][] = new double[l][b];

        for (int i = 0; i < l; i++) for (int j = 0; j < b; j++) 
                z[i][j] = Math.pow(-1, (i + j)) * sub(i, j).det();
            
        return new Matrix(z);
    }

	/**
	 * Calculates adjoint Matrix.
	 * @return 
	 */
    Matrix adjoint() {return cofactor().transpose();}

	/**
	 * Calculates inverse of Matrix.
	 * @return 
	 */
    Matrix inverse() {return adjoint().scalar(1 / det());}
    
    /**
	 * Experimental modifications to allow use of arithmetic operators.
     * Attempting to convert to and from String type.
	 * @return 
	 */
	@Override
    public String toString()
    {
        String s = "" + l+ "," + b + ",";

        for(double[] x : m) 
            for(double y : x)
                s = s + y + ",";
        
        return s;
    }
    
	/**
	 * Experimental modifications to allow use of arithmetic operators.
     * Attempting to convert to and from String type.
	 * @param x
	 * @param y 
	 */
    Matrix(String x, String y)
    {
        m = null;
        l = m.length;
        b = m[0].length;
    }
    
	/**
	 * Main function for testing.
	 * @param args 
	 */
    public static void main(String args[])
    {
        double _a[][] = new double[4][4];
        double _b[][] = new double[4][4];
        
        Matrix a = new Matrix(_a);
        Matrix b = new Matrix(_b);
    }
}