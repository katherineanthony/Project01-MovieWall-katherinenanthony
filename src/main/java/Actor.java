import java.util.ArrayList;

/**
 * Actor class has an ArrayList of Movie objects, and a String name.
 */
public class Actor {
    private String name;
    private ArrayList<Movie> movies;

    public Actor() {
        movies = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public void getMovies() {
        for (int i = 0; i < movies.size(); i++)
            System.out.println(movies.get(i));
    }
}
