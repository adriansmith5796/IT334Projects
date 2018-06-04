package graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class ActorMovieGraph {
    private HashMap<String, Integer> table;
    String[] keys;
    private Graph graph;

    public ActorMovieGraph(FileReader file) throws IOException {
        table = new HashMap<>();
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
            System.out.println("READING " + lineContent[0]);
            int vertex = table.get(lineContent[0]);
            for (int i = 1; i < lineContent.length; i++)
                graph.addEdge(vertex, table.get(lineContent[i]));
        }
        buffer.close();
    }

    public boolean contains(String name)    {   return table.containsKey(name); }

    public int indexOf(String name) {   return table.get(name); }

    public String nameOf(int vertex){   return keys[vertex];    }

    public Graph graph()    {   return graph;   }

    public String getDetails(String srcActor) {
        String result = srcActor + ": 0";
        BFSPaths paths = new BFSPaths(graph, indexOf(srcActor));


        return "";
    }

    public Map<String, Integer> generateActorNumbers(String actorSource){



        return null;
    }

    public String toString(){
        String result = "";

        for(int i = 0; i < keys.length; i++){
            result += i + ": " + keys[i] + "\n";
        }

        return result;

        //return result;
    }
}





