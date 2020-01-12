
import java.util.*;

public class Abc {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        
    }
    static ArrayList<Integer> primeFactors(int x){
        ArrayList<Integer> arr=new ArrayList<>();
        for (int i=2;i*i<=x;i++){
            if (x%i==0){
                arr.add(i);
                while (x%i==0){
                    x=x/i;
                }
            }
        }
        if (x>1)arr.add(x);
        return arr;
    }
}
