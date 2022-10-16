import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MovieWallGenerator {

    private static ArrayList<Actor> actors = new ArrayList<>();
    private static ArrayList<String> movies = new ArrayList<>();

    public static void readFile(String filePath) {
        int numMovies = 0;
        int numActors = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line = br.readLine();
            while (line != null) {
                String[] title = line.split(",");
                // this gets you the movie title at value[1]
                //System.out.println(title[1]); // this is the movie title
                movies.add(numMovies, title[1]); // add movie to array at index of movie #

                for (int i = 0; i < title.length; i++) {
                    String[] part = title[i].split(",");
                    //System.out.println(Arrays.toString(part));
                    for (int j = 0; j < part.length; j++) {
                        Actor actor = new Actor();
                        //System.out.println(part[j]);
                        if (part[j].contains("character")) {
                            //System.out.println("part at j: " + j + " : " + part[j]);
                            // how do you get what is after??
                            int index = part[j].indexOf(":");
                            String temp = part[j].substring(index + 4);
                            String role;
                            if (temp.contains("\"")) {
                                role = temp.substring(0, temp.indexOf("\""));
                                //System.out.println("role: " + role);
                            } else {
                                role = part[j].substring(index + 4, part[j].length() - 2);
                                //System.out.println("role: " + role);
                            }
                            Movie movie = new Movie(title[1], role);
                            actor.addMovie(movie);
                            //System.out.println(actor.getMovie(role));
                        } else if (part[j].contains("name")) {
                            int index = part[j].indexOf(":");
                            String temp = part[j].substring(index + 4, part[j].length() - 2);
                            String name;
                            if (temp.contains("\"")) {
                                name = temp.substring(0, temp.indexOf("\""));
                                //System.out.println("name: " + name);
                            } else {
                                name = part[j].substring(index + 4, part[j].length() - 2);
                                //System.out.println("name: " + name);
                            }
                            actor.setName(name);
                        }
                        if (actor.getName() != null) {
                            numActors++;
                            actors.add(actor);
                        }
                    }
                }
                numMovies++; // we get the correct values of movies
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
        //System.out.println(numMovies);
    }

    public static void main(String[] args) throws FileNotFoundException {
        // read file
        // take user input and search movie wall
        /*Movie movie = new Movie("Unbearable", "Himself");
        Actor actor = new Actor("Nicolas Cage");
        actor.addMovie(movie);
        System.out.println(actor.getMovie("Himself"));*/

        String filePath = "/Users/katherineanthony/IdeaProjects/CS245/Project01/src/main/resources/tmdb_5000_credits.csv";
        readFile(filePath);
        //System.out.println(actors);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an actor's name: ");

        String actor = scanner.nextLine();
        System.out.println(actor);
        ArrayList<Movie> toBePrinted = new ArrayList<>();
        System.out.println(actors.toString());
        for (int i = 0; i < actors.size(); i++) {
            if (actors.get(i).getName().compareTo(actor) == 0) {
                actors.get(i).getMovies(toBePrinted);
            }
        }
        if (toBePrinted.size() != 0) {
            for (int i = 0; i < actors.size(); i++) {
                System.out.println(toBePrinted.get(i));
            }
        }
        else {
            System.out.println("There are no movies with this actor. Did you mean: ");
        }
    }
}

