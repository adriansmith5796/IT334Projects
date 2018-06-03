package graph;

import java.util.HashSet;

public class Graph {
    private int V;
    private int E;
    private HashSet<Integer>[] adj;

    public Graph(int V) {
        this.V = V;
        adj = (HashSet<Integer>[]) new HashSet[V];
        for (int v = 0; v < V; v++)
            adj[v] = new HashSet<>();
    }

    public void addEdge(int from, int to) {
        adj[from].add(to);
        adj[to].add(from);
    }

    public Iterable<Integer> adj(int vertex) {
        return adj[vertex];
    }

    public int V()  {   return this.V;  }
    public int E()  {   return this.E;  }
}