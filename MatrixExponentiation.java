
import java.util.*;

public class Abc {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        
    }
    static long[][] matrix2DMultiplication(long a[][],long b[][]){//a*b, not b*a
        long res[][]=new long[a.length][b[0].length];
        for (int i=0;i<a.length;i++){
            for (int j=0;j<b[0].length;j++){
                for (int k=0;k<a[0].length;k++){
                    res[i][j]+=(a[i][k]*b[k][j]); //put mod here if results are big
                }
            }
        }
        return res;
    }
    static long[][] squareMatrixExponentiation(long a[][],long pow){//logn : same as binary exponentiation
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
            long res[][]=squareMatrixExponentiation(a,pow/2);
            return matrix2DMultiplication(res,res);
        }
        return matrix2DMultiplication(a,squareMatrixExponentiation(a,pow-1));
    }
}
