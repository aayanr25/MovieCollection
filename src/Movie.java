public class Movie {
    private String title;
    private String cast;
    private String director;
    private String overview;
    private int runtime;
    private double userRating;

    public Movie(String title, String cast, String director, String overview, int runtime, double userRating) {
        this.title = title;
        this.cast = cast;
        this.director = director;
        this.overview = overview;
        this.runtime = runtime;
        this.userRating = userRating;
    }



    public String getTitle() {
        return title;
    }
    public String getCast() {
        return cast;
    }
    public String[] getCastMembers() {
        return cast.split("|");
    }
    public String getDirector() {
        return director;
    }
    public String getOverview() {
        return overview;
    }
    public int getRuntime() {
        return runtime;
    }
    public double getUserRating() {
        return userRating;
    }

    public String movieInfo() {
        String str = "";
        str += "Title: " + title;
        str += "\nRuntime: " + runtime;
        str += "\nDirected by: " + director;
        str += "\nCast: " + cast;
        str += "\nOverview: " + overview;
        str += "\nUser Rating: " + userRating;
        return str;
    }

    public String toString() {
        return title;
    }
}
