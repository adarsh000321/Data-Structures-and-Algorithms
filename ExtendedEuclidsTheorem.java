
import java.util.Scanner;

public class Abc {
    static int x,y;
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println(extendedGcd(4,6));
        System.out.println(x+" "+y);
    }
    //ax+by=gcd(a,b)
    static int extendedGcd(int a,int b){
       if (a==0){
           x=0;y=1;
           return b;
       }
       int gcd=extendedGcd(b%a,a);
       int x1=y-(b/a)*x;
       int y1=x;
       x=x1;
       y=y1;
       return gcd;
    }
}
