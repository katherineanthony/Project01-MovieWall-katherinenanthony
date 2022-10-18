/**
 * Movie class has String title and String role.
 */
public class Movie {
    private String title;
    private String role;

    /**
     * constructor for Movie, sets title and role to null
     */
    public Movie() {
        this.title = null;
        this.role = null;
    }

    /**
     * setter for Movie title
     * @param title of Movie
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * setter for Movie role
     * @param role for Actor in Movie
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * returns Movie with its title and role
     * @return String
     */
    @Override
    public String toString() {
        return "Movie: " + title + " as " + role;
    }
}
