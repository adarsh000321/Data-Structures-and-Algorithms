import java.awt.*;
import java.io.*;
import java.util.*;

public class BinaryIndexTreeZeroBased {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int a[]=new int[n];
        for (int i=0;i<n;i++)a[i]=sc.nextInt();
        init(a);
        System.out.println(sum(0,n-1));
    }
    /*
    In Binary Index Tree:
    1. t[i] stores sum of certain ranges i.e. t[i]=(a[i] + a[i-1] + a[i-2] +...+a[j] (j=(i&(i+1)) is the lower bound)
    2. if we change a[i], we update all ranges of t[] that have a[i]
    3. every index of t[] have a range, that ends in that index
    4. to move one step forward use r=r|(r+1):(set the least unset bit)
    5. to move backward use r=r&(r+1)-1:(unset all trailing 1s if present otherwise this range have only one element
       , we get lower bound of this range. subtract 1 to get to the upper bound of previous range)
    6. say we move forward from index j to j|(j+1)
     */
    static int t[];// t[i] stores sum of diff. ranges of arr(0-based)
    static void init(int arr[]){// nlogn pre-process
        t=new int[arr.length];
        for (int i=0;i<arr.length;i++){
            inc(i,arr[i]);
        }
    }
    static int sum(int r){ // sum from 0 to r of arr
//        (Note: r is the ending point of t[r] i.e. t[r]=a[r]+a[r-1]+...+ a[r&(r+1)]) )
        int sum=0;
        while (r>=0){
            sum+=t[r];
            r=(r&(r+1))-1;
        }
        return sum;
    }
    static int sum(int i,int j){// sum from i to j
        if (i==0)return sum(j);
        return sum(j)-sum(i-1);
    }
    static void inc(int r,int val){ // increase arr[i] to arr[i]+val
        int n=t.length;
        while (r<n){
            t[r]+=val;
            r=(r|(r+1));
        }
    }
}
