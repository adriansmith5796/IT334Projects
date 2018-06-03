package representations;


// Weighted Undirected Graph written in class on 5/23/18
// Allows max of two edges (u,v) and (v,u) between any pair of vertices, u and v.
// Incoming vertices are represented by integers
// Edges will have weights as doubles
// Store edges in adjacency list
// Allows self loops

import java.util.NoSuchElementException;
import java.util.Scanner;

public class WeightedGraph {
    // FIELDS
    private ENode[] adjList; // List of vertices and their edges
    private int V; // vertices
    private int E; // edges


    // Constructor: Graph of Vertices and no edges

    public WeightedGraph(int v) {
        V = v;
        adjList = new ENode[V];
    }


    // Constructor: Read input from scanner and create vertices and edges
    public WeightedGraph(Scanner input){
        // TODO complete
    }

    /**************** PRIMARY METHODS *********************/

    /**
     *
     * @return True if added and false if updated
     */
    public boolean addEdge(int from, int to, double weight){
        // validate that the vertices from and to exist in the correct range
        isValid(from);
        isValid(to);

        // determine if the vertices dont' have edges yet
        ENode current = adjList[from];

        if(current == null){ // Vertex doesn't exist yet
            adjList[from] = new ENode(from, to, weight, current);

            // make sure not a loop
            if(from != to){
                adjList[to] = new ENode(to, from, weight, adjList[to]);  // why is next adjList[to]
            }
            E++;
            return true;
        }

        boolean result = false;

        current = getEdge(from, to);


        // Add the from edge
        if(current == null){
            // just add new edge to front of list
            adjList[from] = new ENode(from, to, weight, adjList[from]);
            result = true;
            E++;
        }else{ // update existing edge
            current.weight = weight;
        }

        // Add the to edge
        current = getEdge(to, from);

        if(current == null){
            adjList[to] = new ENode(to, from, weight, adjList[to]);
        }else{
            current.weight = weight;
        }

        return result;
    }

    /**
     *
     * @param to
     * @param from
     * @return
     */
    private ENode getEdge(int from, int to) {
        // Validate the vertices
        isValid(from);
        isValid(to);

        // Search
        ENode current = adjList[from];

        while(current != null && current.to != to){
            current = current.next;
        }


        return current;
    }

    public boolean hasEdge(int from, int to){
        if(!hasVertex(from) || !hasVertex(to))  return false;

        ENode current = adjList[from];

        while(current != null){
            if(current.to == to)    return true;
            current = current.next;
        }

        return false;
    }

    public int vertexSize(){
        return V;
    }

    public int edgeSize(){
        return E;
    }

    // hasEdge(vertex)

    // hasEdges(vertex)

    // getEdgeWeight()

    // printGraph()

    // depth-first search (DFS)

    // findPath()

    /****************** HELPER METHODS ********************/

    /**
     * Validates that a vertex is in the graph.
     * @param vertex
     * @throws IllegalArgumentException if vertex is not in graph
     * @return true if in graph
     */
    public boolean isValid(int vertex){
        if(!hasVertex(vertex)){
            throw new IllegalArgumentException("Vertex " + vertex + " is not between 0 and " + (V - 1));
        }

        return true;
    }

    /**
     * Indicate if vertex is in range of graph
     * @param vertex
     * @return true if between 0 and V-1
     */
    public boolean hasVertex(int vertex){
        return vertex >= 0 && vertex < this.V;
    }

    public boolean hasEdges(int vertex){
        isValid(vertex);

        return !(adjList[vertex] == null);
    }

    public double getEdgeWeight(int from, int to){
        ENode edge = getEdge(from, to);

        if(edge == null)    throw new NoSuchElementException("Edge " + from + "->" + to + " does not exist in the graph.");

        return edge.weight;
    }

    //*************** VIEWING INTERNAL DATA ***************//
    public void printGraph(){
        for(int i = 0; i < adjList.length; i++){
            printEdges(i);
        }
    }

    public void printEdges(int vertex) {
        String edges = "V" + vertex + ": {";

        ENode current = adjList[vertex]; // get first edge
        while(current != null){
            edges += "(" + current.from + " -> " + current.to + ", " + current.weight + "), ";
            current = current.next;
        }

        if(!edges.endsWith("{"))
            edges = edges.substring(0, edges.length() - 2);

        edges += "}";

        System.out.println(edges);
    }


    /**************** INNER CLASSES ***********************/

    // Represents an edge
    private class ENode{
        int from;
        int to;
        double weight;
        ENode next;

        public ENode(int from,int to,double weight, ENode next){
            this.from = from;
            this.to = to;
            this.weight = weight;
            this.next = next;
        }


    }
}
