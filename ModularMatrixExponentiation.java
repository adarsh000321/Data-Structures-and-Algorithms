
import java.util.*;

public class Abc {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println(fib(100,1000000007));
    }
    static long[][] matrix2DModularMultiplication(long a[][], long b[][], long mod){//a*b, not b*a
        long res[][]=new long[a.length][b[0].length];
        for (int i=0;i<a.length;i++){
            for (int j=0;j<b[0].length;j++){
                for (int k=0;k<a[0].length;k++){
                    res[i][j]+=(a[i][k]*b[k][j])%mod; //put mod here if results are big
                }
            }
        }
        return res;
    }
    static long[][] squareMatrixModularExponentiation(long a[][], long pow, long mod){//logn : same as binary exponentiation
        if (pow==0){
            long res[][]=new long[a.length][a.length];//returns identity matrix
            for (int i=0;i<a.length;i++){
                for (int j=0;j<a.length;j++){
                    if (i==j)res[i][j]=1;
                }
            }
            return res;
        }
        if (pow%2==0){
            long res[][]= squareMatrixModularExponentiation(a,pow/2,mod);
            return matrix2DModularMultiplication(res,res,mod);
        }
        return matrix2DModularMultiplication(a, squareMatrixModularExponentiation(a,pow-1,mod),mod);
    }

    static long fib(long n,long mod){//nth fib in o(logn)
        /*MatrixRelation : [ f(n)   ] = power({[1 1]      [f(1)]
                          [ f(n-1) ]           [1 0]},n-1)[f(0)]
         */
        return matrix2DModularMultiplication(squareMatrixModularExponentiation(new long[][]{{1,1},{1,0}},n-1,mod),new long[][]{{1},{0}},mod)[0][0];

    }
}
