import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieWallGenerator {

    private static ArrayList<Actor> actors = new ArrayList<>();
    private static ArrayList<String> movies = new ArrayList<>();

    public int searchForSimilarActor(String userInput) {
        //System.out.println(actors.get(1).getName());
        int closest = Math.abs(actors.get(1).getName().compareToIgnoreCase(userInput));
        int smallestIndex = 0;
        for (int i = 0; i < actors.size(); i++) {
            if (actors.get(i).getName() != null) {
                if (Math.abs(actors.get(i).getName().compareToIgnoreCase(userInput)) < closest) {
                    closest = Math.abs(actors.get(i).getName().compareToIgnoreCase(userInput));
                    smallestIndex = i;
                }
            }
        }
        return smallestIndex;
    }

    public boolean searchForActor(String userInput) {
        System.out.println("You said: " + userInput);
        boolean hasActor = false;
        for (int i = 0; i < actors.size(); i++) {
            if (userInput.equalsIgnoreCase(actors.get(i).getName())) {
                actors.get(i).getMovies();
                hasActor = true;
            }
        }
        if (!hasActor) {
            return false;
        }
        else
            return true;
    }

    public void readFile(String filePath) {
        int numMovies = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String header = br.readLine();
            String line = br.readLine();
            while (line != null) {
                String[] movieTitle = line.split(",");
                movies.add(numMovies, movieTitle[1]); // add movie to array at index of movie #
                String[] allActors = line.split("\\{");

                for (int j = 0; j < allActors.length; j++) {
                    Movie movie = new Movie(); // does this need to be moved?
                    Actor actor = new Actor(); // is this in the wrong spot?
                    String[] actorComponents = allActors[j].split(",");

                    // we're within one actor:
                    for (int i = 0; i < actorComponents.length; i++) {
                        if (actorComponents[i].contains("character") || actorComponents[i].contains("job")) {
                            int index = actorComponents[i].indexOf(":");
                            String temp = actorComponents[i].substring(index + 4);
                            String role = "";
                            if (temp.contains("\"")) {
                                role = temp.substring(0, temp.indexOf("\""));
                            } else {
                                role = actorComponents[i].substring(index + 4, actorComponents[i].length() - 2);
                            }
                            if (role.equals("")) {
                                role = "unknown";
                            }
                            movie.setTitle(movieTitle[1]);
                            movie.setRole(role);
                            actor.addMovie(movie);
                        } else if (actorComponents[i].contains("name")) {
                            int index = actorComponents[i].indexOf(":");
                            String temp = actorComponents[i].substring(index + 4, actorComponents[i].length() - 2);
                            String name;
                            if (temp.contains("\"")) {
                                name = temp.substring(0, temp.indexOf("\""));
                            } else {
                                name = actorComponents[i].substring(index + 4, actorComponents[i].length() - 2);
                            }
                            actor.setName(name);
                        }
                    }
                    // TODO: check if the actor already exists in the List
                    actors.add(actor);
                }
                numMovies++;
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
        MovieWallGenerator generator = new MovieWallGenerator();
        //String file = args[0] + "/tmdb_5000_credits.csv";
        String file = "/Users/katherineanthony/IdeaProjects/CS245/Project01-MovieWall-katherinenanthony/src/main/resources/tmdb_5000_credits.csv";
        generator.readFile(file);
        System.out.println("Welcome to the Movie Wall!");
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

