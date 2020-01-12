
import java.awt.*;
import java.util.*;

public class Abc {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

    }
    /*
    1.Floyd Warshall Shortest Path ALgo: finds shortest distance b/w every pair of vertices
    2.Time: O(V^3) , V=total no. of vertices
    3.Before k-th phase (k=1…n), d[i][j] for any vertices i and j stores the length of the
      shortest path between the vertex i and vertex j, which contains only
      the vertices {1,2,...,k−1} as internal vertices in the path.
    4.One can print path by storing previous vertex in p[][]. Then use recursion 
*/
    static int d[][];// d[i][j] stores length of shortest distance from i to j ( i->j ).
    static int n;// no. of vertices
    static int inf=Integer.MAX_VALUE;// initially, fill d[][] with inf
    static void floyd(){// O(n^3)
        // in kth step, d[i][j] only uses vertices from 0 to k to find shortest distance
        // from i to j(the beginning(i) and end(j) of the path are not restricted by this property)
        for (int k=0;k<n;k++){
            for (int i=0;i<n;i++){
                for (int j=0;j<n;j++){
                    if (d[i][k]<inf && d[k][j]<inf){
                        d[i][j]=Math.min(d[i][j],d[i][k]+d[k][j]);
                    }
                }
            }
        }
    }
}
