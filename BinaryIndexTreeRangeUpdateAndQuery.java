import java.awt.*;
import java.io.*;
import java.util.*;

public class BinaryIndexTreeRangeUpdateAndQuery {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int a[]=new int[n];
        for (int i=0;i<n;i++)a[i]=sc.nextInt();
        init(a);
        System.out.println(sum(0,n-1));
        update(0,n-1,1);
        System.out.println(sum(0,n-1));
    }
    /*
     if we update(l,r,x) then we have to add x to arr[l] and -x to arr[r+1] and do prefix(arr[0...i])
     to get the current value at index i. So, we use b1[] and update all indices that contain index l
     of arr[] in b1 by x and do similar for index r+1 of arr[].
     Let arr2[] (initialized with 0) be another array then:
     Now to sum(arr[0...i]):
     1) we add x*(l-1) to arr2[l] and -x*r to arr2[r+1]
     2) sum(arr[0...i])= (current val. of arr[i])*i - (current val. of arr2[i])
     Note: current val. of arr[i]=prefix of arr[i] and current val. of arr[i]=prefix of arr2[i]
     we use b2[] for arr2[] to store and calculate current values
     In short: After update(l,r,x):
     sum(l,r)=x*i-(l-1)*x if l<=i<=r , calculate arr[i] i.e.,x form b1[] and multiply it with i
                                       and (l-1)*x is already updated in b2[]
     sum(l,r)=0*i-((l-1)*x - x*r) if i>r
     sum(l,r)=0 if i<l
     */
    static int b1[];// 1-based (binary index tree to get current value)
    static int b2[];// 1-based
    static void init(int arr[]){// arr[] is 0-based
        int n=arr.length;
        b1=new int[n+1];
        b2=new int[n+1];
        for (int i=0;i<n;i++){// nlogn pre_process
            update(i,i,arr[i]);
        }
    }
    static int sum(int b[],int i){//sum(b[1...i])
        int res=0;
        for (;i>0;i-=(i&(-i))){
            res+=b[i];
        }
        return res;
    }
    static void inc(int b[],int i,int val){//add val to b[i]
        for (;i<b.length;i+=(i&(-i))){
            b[i]+=val;
        }
    }
    static void update(int l,int r,int val){// increase arr[l...r] by val
        l++;r++;// because arr[] is 0-based
        inc(b1,l,val);
        inc(b1,r+1,-val);
        inc(b2,l,val*(l-1));
        inc(b2,r+1,-val*(r));
    }
    static int prefix(int i){// sum(arr[0...i])
        i++;// because arr[] is 0-based
        return sum(b1,i)*i - sum(b2,i);
    }
    static int sum(int l,int r){
        return prefix(r)-prefix(l-1);
    }
}
