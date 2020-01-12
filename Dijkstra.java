import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;



class Edge {
    int source;
    int destination;
    int weight;

    public Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
}

class Graph {
    int vertices;
    LinkedList<Edge>[] adjacencylist;

    Graph(int vertices) {
        this.vertices = vertices;
        adjacencylist = new LinkedList[vertices];
        //initialize adjacency lists for all the vertices
        for (int i = 0; i < vertices; i++) {
            adjacencylist[i] = new LinkedList<>();
        }
    }

    public void addEdge(int source, int destination, int weight) {
        Edge edge = new Edge(source, destination, weight);
        adjacencylist[source].addFirst(edge);

        edge = new Edge(destination, source, weight);
        adjacencylist[destination].addFirst(edge); //for undirected graph
    }

    public void dijkstra_GetMinDistances(int sourceVertex) {

        boolean[] SPT = new boolean[vertices];
        //distance used to store the distance of vertex from a source
        int[] distance = new int[vertices];

        //Initialize all the distance to infinity
        for (int i = 0; i < vertices; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        //Initialize priority queue
        //override the comparator to do the sorting based keys
        PriorityQueue<Node> pq = new PriorityQueue<>(vertices, new Node());
        //create the pair for for the first index, 0 distance 0 index
        distance[sourceVertex] = 0;
        //add it to pq
        pq.offer(new Node(0, distance[0]));
        while (!pq.isEmpty()) {
            //extract the min Vertex
            int u = pq.poll().node;

            if (!SPT[u]) {
                SPT[u] = true;
                //iterate through all the adjacent vertices and update the keys
                LinkedList<Edge> list = adjacencylist[u];
                for (int i = 0; i < list.size(); i++) {
                    Edge edge = list.get(i);
                    int destination = edge.destination;
                    //only if edge destination is not present in mst
                    if (!SPT[destination] ) {
                        ///check if distance needs an update or not
                        //means check total weight from source to vertex_V is less than
                        //the current distance value, if yes then update the distance
                        int newKey = distance[u] + edge.weight;
                        int currentKey = distance[destination];
                        if (currentKey > newKey) {
                            Node p = new Node(destination, newKey);
                            pq.offer(p);
                            distance[destination] = newKey;
                        }
                    }
                }
            }
        }
        //print Shortest Path Tree
        printDijkstra(distance, sourceVertex);
    }

    public void printDijkstra(int[] distance, int sourceVertex) {
        System.out.println("Dijkstra Algorithm: (Adjacency List + Priority Queue)");
        for (int i = 0; i < vertices; i++) {
            System.out.println("Source Vertex: " + sourceVertex + " to vertex " + +i +
                    " distance: " + distance[i]);
        }


    }
}

class Node implements Comparator<Node> {
    public int node;
    public int cost;

    public Node() {
    }

    public Node(int node, int cost) {
        this.node = node;
        this.cost = cost;
    }

    @Override
    public int compare(Node node1, Node node2) {
        return node1.cost-node2.cost;
    }

}
public class Main {

    public static void main(String[] args) {
        int vertices = 6;
        Graph graph = new Graph(vertices);
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 3);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 2);
        graph.addEdge(2, 3, 4);
        graph.addEdge(3, 4, 2);
        graph.addEdge(4, 5, 6);
        graph.dijkstra_GetMinDistances(0);

    }
}
