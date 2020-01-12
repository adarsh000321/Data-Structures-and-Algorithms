
import java.awt.*;
import java.util.*;

public class Abc {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String s=sc.next();
        int arr[]=zFunc(s);
    }

    /*
    let [l,r] be the rightmost segment that matches with prefix [0,r-l+1]
    Let i be current index:
    if i>r then we calculate z[i] manually by comparing it with prefix
    else if i<=r then z[i]>= min(r-i+1,z[l-i]) then after that check if z[i] can be increased
     */
    // z[i] is the max. len of suffix [i,n-1] that matches with prefix 
    static int[] zFunc(String s){
        int n=s.length();
        int z[]=new int[n];// z[0]=0 always
        int l=0,r=0;
        for (int i=1;i<n;i++){
            if (i<=r)z[i]=Math.min(r-i+1,z[i-l]);
            while (i+z[i]<n && s.charAt(z[i])==s.charAt(i+z[i])){
                z[i]++;
            }
            if (i+z[i]-1>r){
                l=i;r=i+z[i]-1;
            }
        }
        return z;
    }
}
