/**
 * project01-MovieWallGenerator
 *
 * @author Katherine Anthony 10-18-2022
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Java class MovieWallGenerator reads files, stores actors and their movies,
 * takes user input and prints out movies for desired user.
 */
public class MovieWallGenerator {

    private static ArrayList<Actor> actors = new ArrayList<>();

    /**
     * This method uses Math.abs and compareTo to loop through the ArrayList of
     * actors to find the actor that is closest alphabetically to the userInput
     * when the userInput does not match anything in the actor ArrayList. It returns
     * the index of the closest Actor.
     *
     * @param userInput of desired Actor's name
     * @return index of closet alphabetical user
     */
    public int searchForSimilarActor(String userInput) {

        int closest = Math.abs(actors.get(1).getName().compareToIgnoreCase(userInput));
        int smallestIndex = 0;
        for (int i = 0; i < actors.size(); i++) {
            if (actors.get(i).getName() != null) { // ignore null cases
                if (userInput.contains(actors.get(i).getName()) || actors.get(i).getName().contains(userInput))
                    return i;
                if (Math.abs(actors.get(i).getName().compareToIgnoreCase(userInput)) < closest) {
                    closest = Math.abs(actors.get(i).getName().compareToIgnoreCase(userInput));
                    smallestIndex = i;
                }
            }
        }
        return smallestIndex;
    }

    /**
     * This method takes the userInput and loops over the ArrayList of movies
     * to print all the movies that the actor has been in. If the actor hasn't
     * been in any movies, return false, return true otherwise.
     *
     * @param userInput of desired Actor's name
     * @return boolean if actor was or wasn't found
     */
    public boolean searchForActor(String userInput) {
        System.out.println("You said: " + userInput);
        boolean hasActor = false;
        for (int i = 0; i < actors.size(); i++) {
            if (userInput.equalsIgnoreCase(actors.get(i).getName())) {
                actors.get(i).getMovies(); // print the movies if found
                hasActor = true;
            }
        }
        if (!hasActor)
            return false;
        else
            return true;
    }

    /**
     * This method takes a filepath and reads the file line by line. Splitting
     * each line first by commas to get the movie title, then by open braces to
     * get each actor and crew member, and then by comma again to get each individual
     * actor's components (character/job and name).
     *
     * This method then adds a new Actor and Movie object to the appropriate
     * ArrayLists.
     *
     * @param filePath of file to be read
     */
    public void readFile(String filePath) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String header = br.readLine();
            String line = br.readLine();
            while (line != null) {
                String[] movieTitle = line.split(",");
                String[] allActors = line.split("\\{");

                for (int j = 0; j < allActors.length; j++) {
                    Movie movie = new Movie();
                    Actor actor = new Actor();
                    String[] actorComponents = allActors[j].split(",");

                    // we're within one actor:
                    for (int i = 0; i < actorComponents.length; i++) {
                        if (actorComponents[i].contains("character") || actorComponents[i].contains("job")) {
                            int index = actorComponents[i].indexOf(":");
                            String temp = actorComponents[i].substring(index + 4);
                            String role = "";
                            if (temp.contains("\""))
                                role = temp.substring(0, temp.indexOf("\""));
                            else
                                role = actorComponents[i].substring(index + 4, actorComponents[i].length() - 2);
                            if (role.equals(""))
                                role = "unknown";
                            movie.setTitle(movieTitle[1]);
                            movie.setRole(role);
                            actor.addMovie(movie);
                        } else if (actorComponents[i].contains("name")) {
                            int index = actorComponents[i].indexOf(":");
                            String temp = actorComponents[i].substring(index + 4, actorComponents[i].length() - 2);
                            String name;
                            if (temp.contains("\""))
                                name = temp.substring(0, temp.indexOf("\""));
                            else
                                name = actorComponents[i].substring(index + 4, actorComponents[i].length() - 2);
                            if (name != null)
                                actor.setName(name);
                        }
                    }
                    actors.add(actor);
                }
                try {
                    line = br.readLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        // set up:
        MovieWallGenerator generator = new MovieWallGenerator();
        String file = args[0] + "/tmdb_5000_credits.csv";
        generator.readFile(file);

        System.out.println("Welcome to the Movie Wall!");

        // user interaction:
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter an actor's name " +
                "(or \"EXIT\" to quit): ");
        String userInput = scan.nextLine();
        while (!userInput.equalsIgnoreCase("exit")) {
            if (!generator.searchForActor(userInput)) {
                System.out.println("No such actor. Did you mean: ");
                int suggested = (generator.searchForSimilarActor(userInput));
                System.out.println(actors.get(suggested));
                System.out.println("Enter Y or N:");
                userInput = scan.nextLine();
                if (userInput.compareTo("Y") == 0) {
                    userInput = actors.get(suggested).getName();
                    generator.searchForActor(userInput);
                }
            }
            System.out.println("Enter an actor's name " +
                    "(or \"EXIT\" to quit): ");
            userInput = scan.nextLine();
        }
        System.out.println("Thank you for using the movie wall!");
    }
}

