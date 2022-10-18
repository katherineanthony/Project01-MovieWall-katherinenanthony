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

    public void searchForActor(String userInput) {
        for (int i = 0; i < actors.size(); i++) {

        }
    }

    public void readFile(String filePath) {
        int numMovies = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line = br.readLine();
            line = br.readLine();
            while (line != null) {
                String[] movieTitle = line.split(",");
                //System.out.println(line);
                movies.add(numMovies, movieTitle[1]); // add movie to array at index of movie #
                Movie movie = new Movie(); // does this need to be moved?
                Actor actor = new Actor(); // is this in the wrong spot?

                // TODO: how do you keep the same actor while trying to find the character?
                // options:
                /*
                - change how we split the line? (maybe at commas within the line, but after
                    the title we can do braces?)
                 */
                String[] allActors = line.split("\\{");
                for (int j = 0; j < allActors.length; j++) {
                    System.out.println(allActors[j]);
                    String[] actorComponents = allActors[j].split(",");
                    // this is actually accurate but can only be used once we're within the actor's
                    // actual {}
                    for (int i = 0; i < actorComponents.length; i++) {
                        if (actorComponents[i].contains("character") || actorComponents[i].contains("job")) {
                            int index = actorComponents[i].indexOf(":");
                            String temp = actorComponents[i].substring(index + 4);
                            String role;
                            if (temp.contains("\"")) {
                                role = temp.substring(0, temp.indexOf("\""));
                            } else {
                                role = actorComponents[i].substring(index + 4, actorComponents[i].length() - 2);
                            }
                            movie.setTitle(movieTitle[1]);
                            movie.setRole(role);
                            actor.addMovie(movie); // this works to some extent
                        } else if (actorComponents[i].contains("name")) { // getting the name is accurate !
                            int index = actorComponents[i].indexOf(":");
                            String temp = actorComponents[i].substring(index + 4, actorComponents[i].length() - 2);
                            String name;
                            if (temp.contains("\"")) {
                                name = temp.substring(0, temp.indexOf("\""));
                            } else {
                                name = actorComponents[i].substring(index + 4, actorComponents[i].length() - 2);
                            }
                            actor.setName(name);
                            actors.add(actor);
                            actor = new Actor();
                        }

//                    if (title[i].contains("character") || title[i].contains("job")) {
//                        int index = title[i].indexOf(":");
//                        String temp = title[i].substring(index + 4);
//                        String role;
//                        if (temp.contains("\"")) {
//                            role = temp.substring(0, temp.indexOf("\""));
//                        } else {
//                            role = title[i].substring(index + 4, title[i].length() - 2);
//                        }
//                        movie.setTitle(title[1]);
//                        movie.setRole(role);
//                        actor.addMovie(movie); // this works to some extent
//                    } else if (title[i].contains("name")) { // getting the name is accurate !
//                        int index = title[i].indexOf(":");
//                        String temp = title[i].substring(index + 4, title[i].length() - 2);
//                        String name;
//                        if (temp.contains("\"")) {
//                            name = temp.substring(0, temp.indexOf("\""));
//                        } else {
//                            name = title[i].substring(index + 4, title[i].length() - 2);
//                        }
//                        actor.setName(name);
//                        actors.add(actor);
//                        actor =
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
    }

    public static void main(String[] args) throws FileNotFoundException {
        MovieWallGenerator generator = new MovieWallGenerator();
        //String file = args[0] + "/tmdb_5000_credits.csv";
        String file = "/Users/katherineanthony/IdeaProjects/CS245/Project01-MovieWall-katherinenanthony/src/main/resources/tmdb_5000_credits.csv";
        generator.readFile(file);
//        for (int i = 0; i < actors.size(); i++) {
//            System.out.println("actor: " + actors.get(i));
//            actors.get(i).getMovies();
//        }
        //System.out.println("actor: " + actors.get(1));
        //actors.get(1).getMovies();
    }
}

        //System.out.println(movies.get(1));
        //System.out.println(actors.get(1));

        /*for (int i = 0; i < actors.size(); i++) {
            System.out.println(actors.get(i));
            actors.get(i).getMovies();
            //System.out.println(actors.get(i).getMovies());
        }*/
        //for (int i = 0; i < movies.size(); i++)
        //    System.out.println(movies.get(i));
        //System.out.println(movies.size());
        //System.out.println(actors.size());

        /*
        - I THINK THE PROBLEM IS WITH ADDING THE MOVIE TO A PERSON
         */

        //System.out.println(actors);
        /*Scanner scanner = new Scanner(System.in);
        //System.out.println("Enter an actor's name: ");

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
        }*/
        /* TODO: i think there's a problem with the naming of the actor/scope. the using
                    or lifetime of Actor is not sustainable because it doesn't live throughout
                    the finding of character and name.
                    for (int j = 0; j < part.length; j++) {
                        if (part[j].contains("character") || part[j].contains("job")) {
                            int index = part[j].indexOf(":");
                            String temp = part[j].substring(index + 4);
                            String role;
                            if (temp.contains("\"")) {
                                role = temp.substring(0, temp.indexOf("\""));
                            } else {
                                role = part[j].substring(index + 4, part[j].length() - 2);
                            }

                            movie.setTitle(title[1]);
                            movie.setRole(role);
                            actor.addMovie(movie); // this works to some extent

                            actor.getMovies();
                        } else if (part[j].contains("name")) {
                            int index = part[j].indexOf(":");
                            String temp = part[j].substring(index + 4, part[j].length() - 2);
                            String name;
                            if (temp.contains("\"")) {
                                name = temp.substring(0, temp.indexOf("\""));
                            } else {
                                name = part[j].substring(index + 4, part[j].length() - 2);
                            }
                            actor.setName(name);
                        }
                        if (actor.getName() != null) {
                            actors.add(actor);
                            //actor.getMovies();
                            actor = null;
                        }
                    }*/


