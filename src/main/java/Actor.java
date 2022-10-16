import java.util.ArrayList;
import java.util.List;

public class Actor {
    // actor has name, ArrayList<> of all their movies
    private String name;
    private List<Movie> movies = new ArrayList<>();

    public Actor() {

    }
    public Actor(String name) {
        this.name = name;
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

    // do we need to sort the movies at some point?

    public String getMovie(String role) {
        for (int i = 0; i < this.movies.size(); i++) {
            if (movies.get(i).getRole().compareTo(role) == 0) {
                return movies.get(i).getTitle();
            }
        }
        return null;
    }

    public ArrayList<Movie> getMovies(ArrayList<Movie> movies) {
        // print out the movies that the actor has been in
        for (int i = 0; i < this.movies.size(); i++) {
            movies.add(this.movies.get(i));
        }
        return movies;
    }
}
