/*
    Adrian Smith
    IT334 Algorithms
    This program demonstrates degrees of seperation between actors using the movies they acted in.
    The console program loads in a file specified by the user through a numerical input. Reads in the file they selected
    and constructs an adjacency list to represent a graph data structure.
 */
package console;

import graph.ActorMovieGraph;

import java.io.*;
import java.util.Map;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

/**
 * This class is the console for the 6 Degrees of Seperation game. Gets the user's choice, populates a graph based on
 * the file choice and then prints actor numbers for the actor specified by the user.
 */
public class Console {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in); // For reading input
        ActorMovieGraph graph;

        System.out.println("Welcome to the Six-Degrees of Separation Game!");

        // Get Choice
        int choice = getChoice(console);

        // Populates the graph based on file choice
        graph = populateGraph(choice);

        // Gets and displays the details of the actor
        System.out.println(getDetails(graph, console));
    }

    /**
     * This method gets the user's choice for which file they'd like to read.
     * @param console For reading user input
     * @return an integer representing the user's file choice
     */
    public static int getChoice(Scanner console){
        boolean valid = false;
        int choice = 0;

        while(!valid) {
            try {
                System.out.println("Please choose one of the following files: ");
                System.out.println("1. 20_actors_15_movies.txt");
                System.out.println("2. 20_actors_100_movies.txt");
                System.out.println("3. 50_actors_5_movies.txt");
                System.out.println("4. 300_actors_5_movies.txt");
                System.out.println("5. 300_actors_15_movies.txt");
                System.out.println("6. 300_actors_50_movies.txt");
                System.out.println("7. 900_actors_100_movies.txt\n");

                choice = parseInt(console.nextLine());

                if(choice <= 7 && choice >= 1)
                    valid = true;
            } catch (Exception e) {
                System.out.println("Please enter a number that corresponds to the choices.");
            }
        }

        return choice;
    }

    /**
     * Depending on the user's file choice, populates a graph with the
     * file's contents of actors and movies they played in.
     * @param choice
     * @return a new, populated ActorMovieGraph object
     */
    public static ActorMovieGraph populateGraph(int choice){
        String file = "AdrianSmith_DegreesOfSeperation/resources/";

        if(choice == 1)
            file += "20_actors_15_movies.txt";
        else if(choice == 2)
            file += "20_actors_100_movies.txt";
        else if(choice == 3)
            file += "50_actors_5_movies.txt";
        else if(choice == 4)
            file += "300_actors_5_movies.txt";
        else if(choice == 5)
            file += "300_actors_15_movies.txt";
        else if(choice == 6)
            file += "300_actors_50_movies.txt";
        else if(choice == 7)
            file += "900_actors_100_movies.txt";


        try(FileReader reader = new FileReader(file)){
            return new ActorMovieGraph(new BufferedReader(reader));
        }catch(FileNotFoundException e){
            System.out.println("Something went wrong... Maybe the path to the files on line 38 is wrong?");
        }catch(IOException f){
            System.out.println("Something went wrong reading the file... " + f.getMessage());
        }

        return null;
    }

    /**
     * Gets the details of an actor and how far away they are from all of the other actors that they are connected to
     * @param graph The graph object being examined
     * @param console Scanner object for getting user input of which actor to search
     * @return String representation of the actor's details
     */
    public static String getDetails(ActorMovieGraph graph, Scanner console){
        System.out.print("Enter a source actor(Last name, First Name):  ");
        String srcActor = console.nextLine();
        String result = "";

        try{
            Map<String, Integer> map = graph.generateActorNumbers(srcActor);
            double totalNum = 0; // Used for calculating average actor number

            for (Map.Entry<String, Integer> actor : map.entrySet()) {
                result += actor.getKey() + ": " + actor.getValue() + "\n";
                totalNum += actor.getValue();
            }

            result += "The average \"Actor Number\" is: " + (totalNum / (map.keySet().size() - 1)) + "\n";
        }catch(NullPointerException e){
            result += "That actor is not in the file you selected!" + "\n";
        }

        return result;
    }
}
