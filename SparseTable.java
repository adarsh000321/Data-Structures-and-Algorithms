
import java.awt.*;
import java.util.*;

public class Abc {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();
        arr=new int[n];
        for (int i=0;i<n;i++)arr[i]=sc.nextInt();
        st=new int[arr.length][25];
        sparse();
        System.out.println(min(0,arr.length-1));

    }
    /*
    1.st[i][j] = stores answer for range [i,i+(2^j)-1] i.e., of length 2^j starting from index i
    2.for minimum, we can take minimum of two intersecting ranges eg. min([1,6])=min(min([1,4]),min([3,6]))
    3.split range into two halves of equal length: [i,i+(2^j)-1]=union([i,i+(2^(j-1))-1] , [i+2^(j-1),i+(2^j)-1])
*/
    static int st[][];// st[n][k]
    static int arr[];
    static int log[];//floor(log(i) base 2)
    static int k=25; //choose k>=ceil(log(n)+1);
    static int n;// length of arr;
    static void sparse(){// pre_process in o(nlogn)
        for (int i=0;i<n;i++){
            st[i][0]=arr[i];
        }
        for (int j=1;j<k;j++){
            for (int i=0;i+(1<<(j-1))<n;i++){
                st[i][j]=Math.min(st[i][j-1],st[i+(1<<(j-1))][j-1]);// pt 3
            }
        }
        log=new int[n+1];// log.length>=n+1 for storing logs of n lengths
        log[1]=0;
        for (int i=2;i<=n;i++){//floor(log(i) base 2)
            log[i]=log[i/2]+1;
        }
    }
    static int min(int l,int r){// O(1)
        int j=log[r-l+1];// to make it power of 2
        return Math.min(st[l][j],st[r-(1<<j)+1][j]);// pt 2
    }
    static int sum(int l,int r){// O(logn) eg: sum(1,6)=sum(1,4)+sum(5,6)
        // length can be split into decreasing powers of 2 eg. 6=4+2
        int sum=0;
        for (int j=k;j>=0;j--){
            if ((1<<j)<=(r-l+1)){
                sum+=st[l][j];
                l+=(1<<j);
            }
        }
        return sum;
    }
}
