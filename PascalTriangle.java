
import java.util.*;

public class Abc {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

    }
    static long[][] pascalTriangle(int n,int m,int mod){//arr[n][r]=(nCr)%mod       //1
        long arr[][]=new long[n][m+1];                                          //1 1
        for (int i=0;i<n;i++){                                                  //1 2 1
            for (int j=0;j<=Math.min(i,m);j++){                                 //1 3 3  1
                if (i==0||j==0)arr[i][j]=1;                                     //1 4 6  4  1
                else arr[i][j]=(arr[i-1][j]+arr[i-1][j-1])%mod;                 //1 5 10 10 5
            }                                                                   //1 6 15 20 15
        }                                                                       //1 7 21 35 35
        return arr;                                                             //up_to n rows and m columns
    }
}
