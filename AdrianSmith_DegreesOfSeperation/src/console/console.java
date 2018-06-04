package console;

import graph.ActorMovieGraph;

import java.io.*;
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

        //graph.getDetails(srcActor);

        System.out.println(graph.graph().toString());


    }

    public static int getChoice(Scanner console){
        boolean valid = false;
        int choice = 0;

        while(!valid) {
            try {
                System.out.println("Please choose one of the following files: ");
                System.out.println("1. 20_actors_100_movies.txt");
                System.out.println("2. 300_actors_50_movies.txt");
                System.out.println("3. 900_actors_100_movies.txt\n");

                choice = parseInt(console.nextLine());

                if(choice <= 3 && choice >= 1)
                    valid = true;
            } catch (Exception e) {
                System.out.println("Please enter a number that corresponds to the choices.");
            }
        }

        return choice;
    }

    public static ActorMovieGraph populateGraph(int choice){
        String file = "AdrianSmith_DegreesOfSeperation/resources/";
        if(choice == 1) {
            file += "20_actors_100_movies.txt";
        } else if(choice == 2) {
            file += "300_actors_50_movies.txt";
        }else if(choice == 3) {
            file += "900_actors_100_movies";
        }

        ActorMovieGraph graph = null;

        try(FileReader reader = new FileReader(file)){
            graph = new ActorMovieGraph(reader);
        }catch(FileNotFoundException e){
            System.out.println("Something went wrong... Maybe the path to the files on line 38 is wrong?");
        }catch(IOException f){
            System.out.println("Something went wrong reading the file..." + f.getMessage());
        }

        return graph;
    }
}
