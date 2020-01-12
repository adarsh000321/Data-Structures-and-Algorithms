//BFS

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Abc {
    static LinkedList<Integer>[] adj;
    static int V;
    static boolean visited[];
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        V=sc.nextInt();
        adj=new LinkedList[V];
        for (int i=0;i<V;i++)adj[i]=new LinkedList<>();
        int e=sc.nextInt();
        for (int i=0;i<e;i++){
            int a=sc.nextInt(),b=sc.nextInt();
            adj[a].add(b);adj[b].add(a);
        }
        visited=new boolean[V];
        for (int i=0;i<V;i++){
            if (!visited[i])dfs(i);
        }
    }
    static void dfs(int v){
        if (visited[v])return;
        visited[v]=true;
        for (int u:adj[v])dfs(u);
    }
}
