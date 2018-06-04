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

    public int pathTo(int vertex){
        if(vertex < 0 || !hasPathTo(vertex))  return 0;
        int num = 0;

        for(int x = vertex; x != src; x = edgeTo[x])
            num++;

        return (num - 1) / 2;
    }

}