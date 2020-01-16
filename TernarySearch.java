
import java.awt.*;
import java.util.*;

public class Abc {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        
    }

    /*
    Ternary Search: is used when a function f is (strictly) increases first then (strictly)
    decreases (Concave function) Or when f (strictly) decreases first and then (strictly)
    increases(Convex) in interval [l,r] to find maximum and minimum point respectively .
    For Concave function:(Concave function = - Convex Function)
    choose two point m1 and m2 such that l<m1<m2<r
    if f(m1)<f(m2) then ans belongs to [m1,r]
    else if f(m1)>f(m2) then ans belongs to [l,m2]
    else if f(m1)==f(m2) then ans belongs to [m1,m2](this condition can be merged with previous ones)
     */
    static int tsearch(int d){
        int l=0,r=1000000000;
        int ans=Integer.MAX_VALUE;// min value
        int val=-1;// point of min value
        while (r-l>10){// choose comparatively smaller interval but not much smaller like of size 3
            int m1=l+(r-l)/3;
            int m2=r-(r-l)/3;
            if (f(m1)<ans){ // define a function f (must be convex or concave)
                ans=f(m1);
                val=m1;
            }
            if (f(m2)<ans){ // change less/greater than sign according to convex or concave funct
                ans=f(m2);
                val=m2;
            }
            if (f(m1)>f(m2)){
                l=m1;
            }else if (f(m1)<f(m2)){
                r=m2;
            }else {
                l=m1;
                r=m2;
            }
//            if (f(m1)<f(m2)){ // to find maximum point in concave func.
//                l=m1;
//            }else if (f(m1)>f(m2)){
//                r=m2;
//            }else{
//                l=m1;
//                r=m2;
//            }
        }
        for (int i=l;i<=r;i++){// seach space is now reduced to very short interval [l,r]
            ans=Math.min(ans,f(i,d));
        }
        return ans;
    }
    
    static int f(int m){
        return 1;//return some function 
    }
}
