package graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * This class stores the paths from the actorSource passed in to all the other actors.
 */
public class BFSPaths {
    private boolean[] marked; // Determines if actor is connected to another actor in constant time
    private int[] edgeTo; // Shortest path from source to any other actor
    private final int src;

    /**
     * Constructs the paths stored in the arrays.
     * @param graph The graph being evaluated
     * @param src The actor source who all paths are being evaluated to
     */
    BFSPaths(Graph graph, int src) {
        marked = new boolean[graph.V()];
        edgeTo = new int[graph.V()];
        this.src = src;
        bfs(graph, src);
    }

    /**
     * Helper method to make constructor simpler. Fills the arrays with paths and whether an actor is connected or not.
     * @param graph The graph being evaluated
     * @param src The actor source that is having it's actor numbers generated
     */
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

    /**
     * Determines if an actor and the src are connected or not in constant time.
     * @param vertex Actor/Movie being evaluated
     * @return true if actor and source are connected and false if not
     */
    public boolean hasPathTo(int vertex){
        return marked[vertex];
    }

    /**
     * Returns the degree of how far away an actor or movie is
     * @param vertex the actor/movie that is being checked to see how far away it is from the source
     * @return distance from source in number of hops
     */
    public int pathTo(int vertex){
        if(!hasPathTo(vertex))  return 0;
        int num = 0;

        for(int x = vertex; x != src; x = edgeTo[x])
            num += 1;

        return (num / 2);
    }

}