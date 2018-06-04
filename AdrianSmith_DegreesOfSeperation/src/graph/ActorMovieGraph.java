package graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class ActorMovieGraph {
    private HashMap<String, Integer> table; // Index of Actors to their index
    private String[] keys; //
    private HashSet<String> actors;
    private Graph graph;

    public ActorMovieGraph(FileReader file) throws IOException {
        table = new HashMap<>();
        actors = new HashSet<>();
        String actorLine;
        BufferedReader buffer = new BufferedReader(file);
        buffer.mark(1000000);

        // BUILDS INDEX AND POINTS STRINGS TO INDICES
        while ((actorLine = buffer.readLine()) != null) {
            String[] lineContent = actorLine.split(" \\| ");
            for (int i = 0; i < lineContent.length; i++)
                if (!table.containsKey(lineContent[i]))
                    table.put(lineContent[i], table.size());
        }

        keys = new String[table.size()];
        for (String name : table.keySet())
            keys[table.get(name)] = name;



        graph = new Graph(table.size());
        buffer.reset();
        while ((actorLine = buffer.readLine()) != null) {
            String[] lineContent = actorLine.split(" \\| ");
            actors.add(lineContent[0]);
            int vertex = table.get(lineContent[0]);
            for (int i = 1; i < lineContent.length; i++)
                graph.addEdge(vertex, table.get(lineContent[i]));
        }
        buffer.close();
    }

    public boolean contains(String name)    {   return table.containsKey(name); }

    public int indexOf(String name) {
        if(table.containsKey(name)){
            return table.get(name);
        }

        return -1;
    }

    public String nameOf(int vertex){   return keys[vertex];    }

    public Graph graph()    {   return graph;   }

    public Map<String, Integer> generateActorNumbers(String actorSource){
        if(!table.containsKey(actorSource)) return null;
        int actorNum = indexOf(actorSource);
        BFSPaths paths = new BFSPaths(graph, actorNum);
        Map<String, Integer> actorNumbers = new HashMap<>();
        actorNumbers.put(actorSource, 0);

        for(String actor: actors){
            if(paths.hasPathTo(actorNum)){
                actorNumbers.put(actor, paths.pathTo(indexOf(actor)));
            }
        }

        return actorNumbers;
    }

    public String toString(){
        String result = "";

        for(int i = 0; i < keys.length; i++)
            result += i + ": " + keys[i] + "\n";

        return result;
    }
}





