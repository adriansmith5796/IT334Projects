package graph;

import java.io.BufferedReader;
import java.io.IOException;

public class ActorMovieGraph {
    private boolean[][] adjMatrix;
    private int V;
    private int E;

    public ActorMovieGraph(int V){
        this.V = V;
        adjMatrix = new boolean[V][V];
    }

    public ActorMovieGraph(int V, BufferedReader content){
        String actorLine;
        try {
            while ((actorLine = content.readLine()) != null) {
                String[] lineContent = actorLine.split(" | ");

            }
        }catch(IOException e){
            System.out.println("Something went wrong!");
        }
    }
//
//    private class EdgeNode {
//        int from
//    }
}
