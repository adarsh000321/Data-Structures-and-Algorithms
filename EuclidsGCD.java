
import java.util.Scanner;

public class Abc {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println(gcd(4,6));
    }
    static int gcd(int a,int b){
        if (a==0)return b;
        return gcd(b%a,a);
    }
}
