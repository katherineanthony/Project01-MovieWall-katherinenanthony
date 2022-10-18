import java.util.ArrayList;
import java.util.List;

public class Actor {
    // actor has name, ArrayList<> of all their movies
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
        //System.out.println("movie: " + movie);
        boolean hasMovie = false;
        for (int i = 0; i < movies.size(); i++) {
            if (movies.get(i) == movie) {
                hasMovie = true;
                //System.out.println("successful add");
            }
        }
        if (!hasMovie)
            System.out.println("unsuccessful add");
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
