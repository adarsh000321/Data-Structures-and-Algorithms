
import java.util.*;

public class Abc {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        parent=new int[n];
        rank=new int[n];
        for (int i=0;i<n;i++)makeSet(i);
        int m=sc.nextInt();
        for (int i=0;i<m;i++){
            int a=sc.nextInt(),b=sc.nextInt();
            union(a,b);
        }
    }
    static int parent[];
    static int rank[];// use depth OR size of tree as its rank;

    static void makeSet(int v){// add vertex v to new set
        parent[v]=v;
        rank[v]=1;// if we use size of tree as rank
//      rank[v]=0; // if we use depth as rank
    }
    static int findSet(int v){// returns root(representative) of v
        if (parent[v]==v)return v;
        return parent[v]=findSet(parent[v]);// path compression
    }
    static void union(int a,int b){//attach 2nd tree(set with b in it) into 1st one(set with a in it)
        a=findSet(a);
        b=findSet(b);
        if (a!=b){
            if (rank[a]<rank[b]){//attach lower rank tree with bigger rank
                int tmp=a;
                a=b;
                b=tmp;
            }
            parent[b]=a;
            rank[a]+=rank[b];//if we use size of tree as rank
//          if (rank[a]==rank[b])rank[a]++;//if we use depth as rank
        }
    }
// Note: You wont get correct no. of components when adding values of parent[] in set and getting its size
//Note:o(n) per query without path compression and rank optimization, o(logn) per query without either one, o(1) avg. per query and o(lgn) worst caseif both optimizations present

}
