/*
Kruskal Algo for MST : o(eloge), graph shoud be connected
1. sort edges with weights
2.add edges to result in increasing oder of weight if cycle not present(cycle detection with dsu )
note: mst is only for undirected graphs
*/
public class Abc {
    public static void main(String[] args) throws IOException {
//        FastReader sc = new FastReader();
        Scanner sc=new Scanner(System.in);
        int v=sc.nextInt();//no of vertices(from 0 to v-1)
        int e=sc.nextInt();//no of edges
        ArrayList<Edge> edges=new ArrayList<>();
        parent=new int[v];
        rank=new int[v];
        for (int i=0;i<v;i++)makeSet(i);
        for (int i=0;i<e;i++){
            edges.add(new Edge(sc.nextInt(),sc.nextInt(),sc.nextInt()));
        }
        Collections.sort(edges);
        ArrayList<Edge> result=new ArrayList<>();
        long cost=0;
        for (Edge edge:edges){
            if (findSet(edge.v)!=findSet(edge.u)){
                result.add(edge);
                cost+=edge.weight;
                union(edge.v,edge.u);
            }
        }
        System.out.println(cost);

    }
    static class Edge implements Comparable<Edge>{
        int u,v;
        long weight;
        Edge(int u,int v,long weight){
            this.u=u;
            this.v=v;
            this.weight=weight;
        }

        @Override
        public int compareTo(Edge o) {
            long x=this.weight-o.weight;
            if (x<0)return -1;
            if (x>0)return 1;
            return (int) x;
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
}
