package tests;

import representations.WeightedGraph;

import java.util.NoSuchElementException;

public class TestWeightedGraph {
    public static void main(String[] args) {

        WeightedGraph graph = new WeightedGraph(9);

        System.out.println("Adding 1->2" + graph.addEdge(1, 2, 1));
        System.out.println("Adding 1->4" + graph.addEdge(1, 4, 3));
        System.out.println("Adding 2->1" + graph.addEdge(2, 1, 4));
        System.out.println("Adding 2->3" + graph.addEdge(2, 3, 4));
        System.out.println("Adding 2->5" + graph.addEdge(2, 5, 6));
        System.out.println("Adding 2->6" + graph.addEdge(2, 6, 8));
        System.out.println("Adding 4->2" + graph.addEdge(4, 2, 3));
        System.out.println("Adding 4->5" + graph.addEdge(4, 5, 2));
        System.out.println("Adding 5->7" + graph.addEdge(5, 7, 5));
        System.out.println("Adding 5->8" + graph.addEdge(5, 8, 7));
        System.out.println("Adding 7->5" + graph.addEdge(7, 5, 7));

        graph.printGraph();

        System.out.println("HAS EDGE 5->7: " + graph.hasEdge(5, 7));
        System.out.println("HAS EDGE 8->7: " + graph.hasEdge(8, 7));
        System.out.println("HAS EDGE 12->2: " + graph.hasEdge(12, 2));

        System.out.println("# of vertices: " + graph.vertexSize());
        System.out.println("# of edges: " + graph.edgeSize());

        System.out.println("Weight of edge 2->5: " + graph.getEdgeWeight(2, 5));

        try{
            System.out.println("Weight of edge 6->5: " + graph.getEdgeWeight(6, 5));
        }catch(NoSuchElementException e){
            System.out.println(e.getMessage());
        }

        try{
            System.out.println("Vertex 6 has edges: " + graph.hasEdges(6));
        }catch(NoSuchElementException e){
            System.out.println(e.getMessage());
        }
    }

}
