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

public class console {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        ActorMovieGraph graph = null;

        System.out.println("Welcome to the Six-Degrees of Separation Game!");

        // Get Choice
        int choice = getChoice(console);

        graph = populateGraph(choice);


        System.out.print("Enter a source actor(Last name, First Name):  ");
        String srcActor = console.nextLine();

        try{
            Map<String, Integer> map = graph.generateActorNumbers(srcActor);
            int totalNum = 0;
            for (Map.Entry<String, Integer> actor : map.entrySet()) {
                System.out.println(actor.getKey() + ": " + actor.getValue());
                totalNum += actor.getValue();
            }

            System.out.println("The average \"Actor Number\" is: " + (totalNum / map.keySet().size()));
        }catch(NullPointerException e){
            System.out.println("That actor is not in the file you selected!");
        }

    }

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

        ActorMovieGraph graph = null;

        try(FileReader reader = new FileReader(file)){
            graph = new ActorMovieGraph(reader);
        }catch(FileNotFoundException e){
            System.out.println("Something went wrong... Maybe the path to the files on line 38 is wrong?");
        }catch(IOException f){
            System.out.println("Something went wrong reading the file... " + f.getMessage());
        }

        return graph;
    }
}
