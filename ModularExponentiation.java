
import java.util.*;

public class Abc {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println(modularExponentiation(3,2,2));
    }
    static long modularExponentiation(long a, long pow, long mod){
        if (pow==0)return 1;
        if (pow%2==0){
            long x= modularExponentiation(a,pow/2,mod)%mod;
            return (x*x)%mod;
        }
        return ((a%mod)*(modularExponentiation(a,pow-1,mod)%mod))%mod;
    }
}
