/**
 * Movie class has String title and String role.
 */
public class Movie {
    private String title;
    private String role;

    public Movie() {
        this.title = null;
        this.role = null;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Movie: " + title + " as " + role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
