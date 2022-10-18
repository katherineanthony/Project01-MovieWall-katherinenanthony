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
import java.util.Arrays;
import java.util.Scanner;

/**
 * Java class MovieWallGenerator reads files, stores actors and their movies,
 * takes user input and prints out movies for desired user.
 */
public class MovieWallGenerator {

    private static ArrayList<Actor> actors = new ArrayList<>();

    /**
     * This method compares the userInput by first and last name (part)
     * so that it will return an actor if it has the same first or last name.
     * If there are no actors with the same first or last name, it returns
     * -1 and the other method will try to find another similar user.
     *
     * @param userInput to compare against
     * @return index of most accurate actor in ArrayList (-1 if none)
     */
    public int searchForAlt (String userInput) {
        int closestIndex = -1;
        String[] componentsOfUI = userInput.split(" ");
        for (int i = 0; i < actors.size(); i++) {
            if (actors.get(i).getName() != null) {
                String[] componentsOfI = actors.get(i).getName().split(" ");
                for (int j = 0, k = 0; j < componentsOfI.length && k < componentsOfUI.length; j++, k++) {
                    // if there are any exact matches with first and last names in the right spot,
                    // return that index immediately
                    if (componentsOfI[j].equalsIgnoreCase(componentsOfUI[k]))
                        return i;
                }
                // otherwise, loop over to see if there are matches with first and last names
                // in different orders
                for (int j = 0; j < componentsOfI.length; j++) {
                    for (int k = 0; k < componentsOfUI.length; k++) {
                        if (componentsOfI[j].equalsIgnoreCase(componentsOfUI[k]))
                            closestIndex = i;
                    }
                }
            }
        }
        return closestIndex;
    }

    /**
     * This method first calls searchForAlt to see if the userInput matches any of the
     * actors by first or last name component, and if it returns -1 (meaning that there
     * are no matches by whole names) it continues with the algo.
     *
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

        // check to see if the input matches by component (whole name) before going by Math
        if (searchForAlt(userInput) != -1)
            return searchForAlt(userInput);

        for (int i = 0; i < actors.size(); i++) {
            if (actors.get(i).getName() != null) { // ignore null cases
                // base case: they're off by such a close amount that one of them contains the other
                if (userInput.contains(actors.get(i).getName()) || actors.get(i).getName().contains(userInput))
                    return i;
                // otherwise look for the one that is closest to 0 (compareTo returns > 0 if after in alphabet
                // or < 0 if before in alphabet
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
            String header = br.readLine(); // eat the first line
            String line = br.readLine();
            while (line != null) {
                String[] movieTitle = line.split(","); // split to get the movie title
                String[] allActors = line.split("\\{"); // split to get each actor

                for (int j = 0; j < allActors.length; j++) {
                    Movie movie = new Movie();
                    Actor actor = new Actor();
                    String[] actorComponents = allActors[j].split(","); // split to get actor's components

                    // we're within one actor:
                    for (int i = 0; i < actorComponents.length; i++) {
                        // looking for name, character or job
                        if (actorComponents[i].contains("character") || actorComponents[i].contains("job")) {
                            int index = actorComponents[i].indexOf(":");
                            String temp = actorComponents[i].substring(index + 4);
                            String role = "";
                            if (temp.contains("\"")) // look for special cases with quotes or }
                                role = temp.substring(0, temp.indexOf("\""));
                            else
                                role = actorComponents[i].substring(index + 4, actorComponents[i].length() - 2);
                            if (role.equals("")) // if there's a null at some point
                                role = "unknown";
                            movie.setTitle(movieTitle[1]);
                            movie.setRole(role);
                            actor.addMovie(movie);
                        } else if (actorComponents[i].contains("name")) {
                            int index = actorComponents[i].indexOf(":");
                            String temp = actorComponents[i].substring(index + 4, actorComponents[i].length() - 2);
                            String name;
                            if (temp.contains("\"")) // look for special cases with quotes or }
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

    /**
     * main method reads file calling readFile() and uses Scanner
     * to get userInput and search for the desired Actor and print
     * their movies.
     *
     * @param args takes file path from args (in run config)
     */
    public static void main(String[] args) {
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

