
import java.awt.*;
import java.util.*;

public class Abc {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println(inv(3,11));
    }

/*
        Fermat's little thm. : IF 'm' is a PRIME number and 'a' is any integer then: a^(m-1) ≡ 1 (mod m)
        a^(m-1) ≡ 1 (mod m) => a^(m-2) ≡ a^(-1) (mod m)  [multiplying both sides with a^-1](Used in finding inverse)
        => a^(-1)=a^(m-2) (mod m)
*/

    static long inv(long a, long m) {// find a^(-1) ?
        // m is prime. (Note: gcd(a,m)==1 => as m is prime, m!=a)
        return modPow(a, m - 2, m);
    }
    static long modPow(long a,long pow,long m){
        if (pow==0)return 1;
        long res=modPow(a,pow/2,m)%m;
        if (pow%2==0){
            res=(res*res)%m;
        }else res=((res*res)%m*a)%m;
        return res%m;
    }
}
