
import java.awt.*;
import java.util.*;

public class Abc {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        
    }

    /*
        Use more than one hashFunction to reduce collision
    */
    static class Hash {
        int hashs[];
        int pows[];
        long mod;
        long p;

        Hash(String s, long mod, long p) {
            hashs = new int[s.length()];
            pows = new int[s.length()];
            //hash(s)=(s[0]+s[1]*p+s[2]*p^2+...+s[n−1]*p^(n−1))%mod
            this.mod = mod;//some large integer prime
            long p_pow = 1;
            this.p = p;//37,29// some prime
            long hash = 0;
            for (int i = 0; i < s.length(); i++) {
                hash = (hash + (s.charAt(i) - 'a' + 1) * p_pow) % mod;
                this.hashs[i] = (int) hash;
                pows[i] = (int) p_pow;
                p_pow = (p_pow * p) % mod;
            }
        }

        //      hash(s[i…j])=(s[i]+s[i+1]*p+...+s[j]*p^(j-i))mod m=(hash(s[0…j])−hash(s[0…i−1]))*inv(p^i)(mod m)
        int getHash(int i, int j) {// log(m) per query
            long inv = 0;
            if (i != 0) {
                long p_powi = modPow(p, i, mod);
                inv = inv(p_powi, mod);
            }
            if (i == 0) return hashs[j];
            else return (int) (((hashs[j] - hashs[i - 1]) * inv) % mod);
        }

        long inv(long a, long m) {// find a^(-1) ?
//          m is prime. (Note: gcd(a,m)==1 => as m is prime, m!=a)
            return modPow(a, m - 2, m);
        }

        /*
       comparing two substrings in o(1):
       we know, hash(s[i…j])*p^i=(hash(s[0…j])−hash(s[0…i−1]))(mod m)[cp-algo]
       so,if two substrings are equal, then iff i<j
       hash(s[i...k])=hash(s[j...x]) => hash(s[i...k])*(p^i)*(p^(j-i))=hash(s[j...x])(p^j)
       Similar technique can be used in Rabin-Karp i.e., For searching pattern t in string s:
       check if hash(s[i...(i+t.length-1)])==hash(t[0...(t.length-1)])*(p^i))
       This is Rabin Karp...
         */
        boolean compare(int l1,int r1,int l2,int r2){// assumption l1>=l2
            if (l1<l2){
                //swap l1,r1 with l2,r2
                int tmp=l1;
                l1=l2;
                l2=tmp;
                tmp=r1;
                r1=r2;
                r2=tmp;
            }
            long hash1,hash2;
            if (l1==0)hash1=hashs[r1];
            else hash1=(hashs[r1]-hashs[l1-1]+mod)%mod;
            if (l2==0)hash2=(hashs[r2]*(long) pows[l1-l2] + mod)%mod;
            else hash2=((((hashs[r2]-hashs[l2-1]))*(long)pows[l1-l2])%mod + mod)%mod;
            return hash1==hash2;
        }
        long modPow(long a, long pow, long m) {
            if (pow == 0) return 1;
            long res = modPow(a, pow / 2, m) % m;
            if (pow % 2 == 0) {
                res = (res * res) % m;
            } else res = ((res * res) % m * a) % m;
            return res % m;
        }
    }
}
