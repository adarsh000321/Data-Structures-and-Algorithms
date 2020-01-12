
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
    For Convexity (2nd Derivative) f″(x)>=0
    For Concave function:(Concave function = - Convex Function)
    choose two point m1 and m2 such that l<m1<m2<r
    if f(m1)<f(m2) then ans belongs to [m1,r]
    else if f(m1)>f(m2) then ans belongs to [l,m2]
    else if f(m1)==f(m2) then ans belongs to [m1,m2](this condition can be merged with previous ones)
     */
    static long ternary(){
        int lo=1,hi=1000000000;
        long ans=(long)1e18;
        long val=1;
        for (int it=0;it<100;it++){// use iterations to avoid infinite loop
            int m1=lo+(hi-lo)/3;
            int m2=hi-(hi-lo)/3;
            if (f(m1)<ans){ // define a function f (must be convex or concave)
                ans=f(m1);
                val=m1;
            }
            if (f(m2)<ans){// change less/greater than sign according to convex or concave funct
                ans=f(m2);
                val=m2;
            }
            if (f(m1)>=f(m2)){// to find minimum func. in convex func.
                lo=m1;
            }else {
                hi=m2;
            }
//            if (f(m1)<=f(m2)){ // to find maximum point in concave func.
//                lo=m1;
//            }else {
//                hi=m2;
//            }
        }
        return ans;
    }
    static int f(int m){
        return 1;//return some function 
    }
}
