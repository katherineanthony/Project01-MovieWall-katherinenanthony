import java.util.ArrayList;

/**
 * Actor class has an ArrayList of Movie objects, and a String name.
 */
public class Actor {
    private String name;
    private ArrayList<Movie> movies;

    /**
     * constructor for Actor, initializes ArrayList
     */
    public Actor() {
        movies = new ArrayList<>();
    }

    /**
     * setter for name
     * @param name of Actor
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * adds movie to ArrayList of movies
     * @param movie being added
     */
    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    /**
     * getter for name
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     * prints out the Actor's movies
     */
    public void getMovies() {
        for (int i = 0; i < movies.size(); i++)
            System.out.println(movies.get(i));
    }

    /**
     * prints name of Actor when printed
     * @return String name
     */
    @Override
    public String toString() {
        return this.name;
    }
}
