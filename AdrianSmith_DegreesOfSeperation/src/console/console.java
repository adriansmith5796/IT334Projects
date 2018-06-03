package console;

import graph.ActorMovieGraph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.Scanner;

public class console {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        ActorMovieGraph graph = null;

        System.out.println("Welcome to the Six-Degrees of Separation Game!");

        // Get Choice

        boolean valid = false;
        int choice = 0;
        while(!valid) {
            try {
                System.out.println("Please choose one of the following files: ");
                System.out.println("1. 20_actors_100_movies.txt");
                System.out.println("2. 300_actors_50_movies.txt");
                System.out.println("3. 900_actors_100_movies.txt\n");
                //choice = console.nextInt();
                choice = 1;
                if(choice <= 3 && choice >= 1){
                    valid = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number that corresponds to the choices.");
            }
        }

        String file = "AdrianSmith_DegreesOfSeperation/resources/";
        if(choice == 1)
            file += "20_actors_100_movies.txt";
        else if(choice == 2)
            file += "300_actors_50_movies.txt";
        else if(choice == 3)
            file += "900_actors_100_movies";

        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            graph = new ActorMovieGraph(100, reader);
        }catch(Exception e){
            System.out.println("FILE: " + file + " NOT FOUND");
        }




        System.out.print("Enter a source actor: ");
        String srcActor = console.nextLine();

    }
}
