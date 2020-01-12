
import java.util.*;

public class Abc {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println(binaryExponentiation(2,2));
    }
    static long binaryExponentiation(long a,long pow){
        if (pow==0)return 1;
        if (pow%2==0){
            long x=binaryExponentiation(a,pow/2);
            return x*x;
        }
        return a*binaryExponentiation(a,pow-1);
    }
}
