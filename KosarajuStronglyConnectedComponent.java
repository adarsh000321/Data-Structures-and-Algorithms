/*
  Strongly Connected component(SCC): Algorithm
  Src: cp algo...
  1. Sort all vertices in order of exiting time i.e., in reversed topological order (Read THm. in cp algo if forgot).
  2. Reverse the original graph to get every vertex of some particular SCC by not going to other components.
  3. visit unvisited vertices in exiting time order i.e., in reversed topological order.
  4. Each dfs run will give one component. We get condensed graph in topological order.
  Condensed Graph: Each vertex of this graph represents one strongly connected component.

 */
 public class CandidateCode {
     static boolean vis[];
     static ArrayList<Integer> g[],gr[];
     static Stack<Integer> order;
     static ArrayList<Integer> components;
    public static void main(String[] args) {
        FastReader sc = new FastReader();
        int n=sc.nextInt();
        int cost[]=new int[n];
        for (int i=0;i<n;i++)cost[i]=sc.nextInt();
        g=new ArrayList[n];
        gr=new ArrayList[n];
        components=new ArrayList<>();
        for (int i=0;i<n;i++){
            g[i]=new ArrayList<>();
            gr[i]=new ArrayList<>();
        }
        int m=sc.nextInt();
        order=new Stack<>(); // tout order: pop gives vertex with max tout
        vis=new boolean[n];
        for (int i=0;i<m;i++){
            int a=sc.nextInt()-1,b=sc.nextInt()-1;
            g[a].add(b);
            gr[b].add(a); //reverse graph
        }
        for (int i=0;i<n;i++){
            if (!vis[i]){
                dfs1(i);
            }
        }
        vis=new boolean[n];
        while (!order.isEmpty()){
            int i=order.pop();
            if (!vis[i]){
                dfs2(i);
                //print components
                components.clear();
            }
        }


    }
    static void dfs1(int u){// topological sort
        vis[u]=true;
        for (int v:g[u]){
            if (!vis[v]){
                dfs1(v);
            }
        }
        order.push(u);
    }
    static void dfs2(int u){// topological in reverse graph
        vis[u]=true;
        components.add(u);
        for (int v:gr[u]){
            if (!vis[v]){
                dfs2(v);
            }
        }
    }
}
