//BFS

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Drogon {
    public static void main(String[] args) {
        Graph g=new Graph(6);
        g.addEdge(0,1);
        g.addEdge(1,2);
        g.addEdge(0,4);
        g.addEdge(2,4);
        g.addEdge(3,2);
        g.addEdge(2,3);
        g.addEdge(3,5);
        g.addEdge(3,4);

        g.bfs(0);
        g.printDist();
        g.printPath(5);
    }
}
class Graph{
    private LinkedList<Integer>[] adj;
    private int V;
    private boolean visited[];
    private int dist[];
    private int parent[];
    private int src;
    public Graph(int V){
        this.V=V;
        adj=new LinkedList[V];
        visited=new boolean[V];
        for (int i=0;i<V;i++){
            adj[i]=new LinkedList<>();
        }
        dist=new int[V];
        parent=new int[V];
        for (int i=0;i<V;i++){
            parent[i]=Integer.MIN_VALUE;
            dist[i]=Integer.MIN_VALUE;
        }
    }

    public void addEdge(int u,int v){
        adj[u].add(v);
        adj[v].add(u);
    }

    void bfs(int src){
        this.src=src;
        Queue<Integer> q=new LinkedList<>();
        q.add(src);
        visited[src]=true;
        parent[src]=src;
        dist[src]=0;
        while (!q.isEmpty()){
            int v=q.remove();
            System.out.print(v+" ");
            for (int w : adj[v]){
                if (!visited[w]) {
                    q.add(w);
                    visited[w]=true;
                    dist[w]=dist[v]+1;
                    parent[w]=v;
                }
            }
        }
        System.out.println();
    }
    void printDist(){
        for (int i=0;i<V;i++){
            System.out.println("Node "+i+" has distance from "+src+": "+dist[i]);
        }
    }

    void printPath(int dest){
        System.out.print(dest+" <- ");
        for (int i=parent[dest];i!=parent[src];i=parent[i]){
            System.out.print(i+" <- ");
        }
        System.out.println(parent[src]);
    }
}
