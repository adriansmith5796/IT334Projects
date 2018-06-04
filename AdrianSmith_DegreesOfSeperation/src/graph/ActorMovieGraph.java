package graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * This class keeps a symbol table that indexes what Actor/Movie is stored in what index of the adjacency list
 * stored in the Graph object. It holds an array of Strings representing what index stores which actor/movie. The
 * actors HashSet stores a list of actors to make it easier to calculate ActorNumbers. The Graph is a seperate class
 * instead of a private inner class to help keep code cleaner.
 */
public class ActorMovieGraph {
    private HashMap<String, Integer> table; // Index of Actors to their index
    private String[] keys; //
    private HashSet<String> actors;
    private Graph graph;

    /**
     * Constructs the graph using only a BufferedReader object so that it can be reset instead of re-instantiating it.
     * It first builds the index of actors and the index they're stored in and then goes over the keys array and does
     * the same thing but backwards. Lastly, it actually adds the edges to the Graph object.
     * @param file BufferedReader object being read from
     * @throws IOException
     */
    public ActorMovieGraph(BufferedReader file) throws IOException {
        table = new HashMap<>();
        actors = new HashSet<>();
        String actorLine;
        file.mark(1000000);

        // BUILDS INDEX AND POINTS STRINGS TO INDICES
        while ((actorLine = file.readLine()) != null) {
            String[] lineContent = actorLine.trim().split(" \\| ");
            for (int i = 0; i < lineContent.length; i++)
                if (!table.containsKey(lineContent[i]))
                    table.put(lineContent[i], table.size());
        }

        // BUILDS ARRAY THAT POINTS INDICES TO STRINGS
        keys = new String[table.size()];
        for (String name : table.keySet())
            keys[table.get(name)] = name;

        // CONSTRUCTS THE GRAPH
        graph = new Graph(table.size());
        file.reset(); // resets reader so that actors & movies can be added to graph
        while ((actorLine = file.readLine()) != null) {
            String[] lineContent = actorLine.split(" \\| ");
            actors.add(lineContent[0]);
            int vertex = table.get(lineContent[0]);
            for (int i = 1; i < lineContent.length; i++)
                graph.addEdge(vertex, table.get(lineContent[i]));
        }
        file.close();
    }

    /**
     * Checks if the table contains the given String value
     * @param name String(Actor/Movie) being searched for
     * @return true if table has value, false if not
     */
    public boolean contains(String name) {
        return table.containsKey(name);
    }

    /**
     * Returns the index of the given actor/movie
     * @param name actor/movie being searched for
     * @return int value of what index the actor/movie is stored in
     */
    public int indexOf(String name) {
        return table.get(name);
    }

    /**
     * Gets the name of the actor/movie stored in the index passed in
     * @param vertex The index being evaluated
     * @return name of actor/movie stored in the index
     */
    public String nameOf(int vertex) {
        return keys[vertex];
    }

    /**
     * Returns the Graph object being stored by the class
     * @return Graph object
     */
    public Graph graph() {
        return graph;
    }

    /**
     * Generates the actor numbers associated relative to the actorSource.
     * @param actorSource The actor that is the center of the numbers being generated
     * @return Map of actor names to the actor numbers
     */
    public Map<String, Integer> generateActorNumbers(String actorSource) {
        if (!table.containsKey(actorSource)) return null;
        int actorNum = indexOf(actorSource);
        BFSPaths paths = new BFSPaths(graph, actorNum);
        Map<String, Integer> actorNumbers = new HashMap<>();
        actorNumbers.put(actorSource, 0);

        for (String actor : actors) {
            int path = paths.pathTo(indexOf(actor));
            if(path != 0)
                actorNumbers.put(actor, path);
        }

        return actorNumbers;
    }

    /**
     * String representation of the internal Graph object
     * @return representation of the Graph object
     */
    public String toString() {
        String result = "";

        for (int i = 0; i < keys.length; i++)
            result += i + ": " + keys[i] + "\n";

        return result;
    }
}





