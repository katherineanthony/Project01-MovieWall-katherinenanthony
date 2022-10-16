public class Movie {
    private String title;
    private String role;

    public Movie (String title, String role) {
        this.title = title;
        this.role = role;
    }

    public Movie(String title) {
        this.title = title;
    }

    public Movie() {
        this.title = null;
        this.role = null;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // may not need the following methods...
    public String getTitle() {
        return title;
    }

    public String getRole() {
        return role;
    }
}
