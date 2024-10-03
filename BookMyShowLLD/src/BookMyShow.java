public class BookMyShow {
    MovieController movieController;
    TheatreController theatreController;

    public void setup()
    {

    }

    public MovieController getMovieController() {
        return movieController;
    }

    public void setMovieController(MovieController movieController) {
        this.movieController = movieController;
    }

    public TheatreController getTheatreController() {
        return theatreController;
    }

    public void setTheatreController(TheatreController theatreController) {
        this.theatreController = theatreController;
    }
}
