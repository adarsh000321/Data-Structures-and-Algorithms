
import java.awt.*;
import java.util.*;

public class Abc {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        for (int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                adj[i][j]=sc.nextInt();
            }
        }
        ArrayList<Point> mst=prim();
        for (int i=0;i<mst.size();i++){
            Point edge=mst.get(i);
            System.out.println(edge.x+" "+edge.y);
        }
    }
    /*
Prim's Algo for MST in Dense Graph O(n^2)
In prims algorithm, we provide weights to each vertex like dijkstra
Graph: set INF to adj[u][v] if u & v aren't connected by edge directly
1.Make weight of a random vertex (say 0) 0 before looping
2.Select minimum weight vertex(say v) form not yet selected vertex
3.Mark that vertex selected and update all unselected vertices adj. to v
note: mst is only for undirected graphs
*/

    static long adj[][];// adj. matrix graph(dense graph)
    static long INF=Long.MAX_VALUE;
    static class Edge{
        long w =INF;
        int to=-1;

    }
    static ArrayList<Point> prim(){
        ArrayList<Point> result=new ArrayList<>();
        int n=adj.length;// no.of vertices
        boolean selected[]=new boolean[n];
        Edge[] min_e=new Edge[n];//min_e[from].to : min w vertex connected to 'from' which is not yet selected vertex, min_e[from].w : w of edge: from-to
        long cost=0;
//        Arrays.fill(min_e,new Edge());//wrong because it fills it with same object
        for (int i=0;i<n;i++)min_e[i]=new Edge();
        min_e[0].w =0;
        for (int i=0;i<n;i++){
            int v=-1;
            for (int j=0;j<n;j++){
                if (!selected[j] && (v==-1 || min_e[v].w >min_e[j].w))v=j;
            }
            cost+=min_e[v].w;
            selected[v]=true;
            if (min_e[v].to!=-1){// adds only selected pair of vertices
                result.add(new Point(min_e[v].to,v));//add to result
            }
            if (result.size()==n-1)break;//added later
            for (int to=0;to<n;to++){
                if (adj[v][to]<min_e[to].w){//update
                    min_e[to].to=v;
                    min_e[to].w =adj[v][to];
                }
            }
        }
        System.out.println(cost);// min cost of mst
        return result;
    }
}
