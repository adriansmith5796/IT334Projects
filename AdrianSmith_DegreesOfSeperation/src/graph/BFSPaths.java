package graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BFSPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private final int src;

    BFSPaths(Graph graph, int src) {
        marked = new boolean[graph.V()];
        edgeTo = new int[graph.V()];
        this.src = src;
        bfs(graph, src);
    }

    private void bfs(Graph graph, int src) {
        Queue<Integer> queue = new LinkedList<>();
        marked[src] = true;
        queue.add(src);

        while (!queue.isEmpty()) {
            int vertex = queue.remove();
            for (int v : graph.adj(vertex)) {
                if (!marked[v]) {
                    edgeTo[v] = vertex;
                    marked[v] = true;
                    queue.add(v);
                }
            }
        }
    }

    public boolean hasPathTo(int vertex){
        return marked[vertex];
    }

    public Iterable<Integer> pathTo(int vertex){
        if(!hasPathTo(vertex))  return null;

        Stack<Integer> path = new Stack<>();
        for(int x = vertex; x != src; x = edgeTo[x])
            path.push(x);
        path.push(src);
        return path;
    }

}