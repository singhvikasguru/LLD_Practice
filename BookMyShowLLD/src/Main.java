import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    MovieController movieController;
    TheatreController theatreController;

    Main() {
        movieController = new MovieController();
        theatreController = new TheatreController();
    }


    private List<Screen> createScreen() {

        List<Screen> screens = new ArrayList<>();
        Screen screen1 = new Screen("1");
        screen1.setId("1");
        screen1.setSeats(createSeats());
        screens.add(screen1);

        return screens;
    }
    private Shows createShows(String showId, Screen screen, Movies movie, int showStartTime) {

        Shows show = new Shows();
        show.setId(showId);
        show.setScreen(screen);
        show.setMovie(movie);
        show.setStartTime(showStartTime); //24 hrs time ex: 14 means 2pm and 8 means 8AM
        return show;
    }

    private List<Seat> createSeats() {

        //creating 100 seats for testing purpose, this can be generalised
        List<Seat> seats = new ArrayList<>();

        //1 to 40 : SILVER
        for (int i = 0; i < 40; i++) {
            Seat seat = new Seat();
            seat.setId(""+i);
            seat.setOccupied(false);
            seat.setPrice(100.0f);
//            seat.setSeatCategory(SeatCategory.SILVER);
            seats.add(seat);
        }

        //41 to 70 : SILVER
        for (int i = 40; i < 70; i++) {
            Seat seat = new SilverSeat(""+i, 200.0f, false, "regular");
            seats.add(seat);
        }
        return seats;
    }
    private void createMovies() {
        Movies avengers = new Movies();
        avengers.setId("1");
        avengers.setName("AVENGERS");
        avengers.setDuration(128);

        //create Movies2
        Movies baahubali = new Movies();
        baahubali.setId("2");
        baahubali.setName("BAAHUBALI");
        baahubali.setDuration(180);

//        movieController.addMovies(avengers);
//        movieController.addMovies();
    }

    private void initialize() {

        //create movies
        createMovies();

        //create theater with screens, seats and shows
//        createTheatre();
        createCity();
    }

    private  void createCity()
    {
        City Banglore= new City();
        Banglore.setId("1");
        Banglore.setName("Banglore");

        City Delhi = new City();
        Delhi.setId("2");
        Delhi.setName("Delhi");
    }


    private void createTheatre(MovieController movieController, TheatreController theatreController, City Banglore, City Delhi) {

        Movies avengerMovie = movieController.getMovieByName("AVENGERS");
        Movies baahubali = movieController.getMovieByName("BAAHUBALI");

        Theatre inoxTheatre = new Theatre();
        inoxTheatre.setId("1");
        inoxTheatre.setScreens(createScreen());
        inoxTheatre.setCity(Banglore);
        List<Shows> inoxShows = new ArrayList<>();
        Shows inoxMorningShow = createShows("1", inoxTheatre.getScreens().get(0), avengerMovie, 8);
        Shows inoxEveningShow = createShows("2", inoxTheatre.getScreens().get(0), baahubali, 16);
        inoxShows.add(inoxMorningShow);
        inoxShows.add(inoxEveningShow);
        inoxTheatre.setShows(inoxShows);


        Theatre pvrTheatre = new Theatre();
        pvrTheatre.setId("2");
        pvrTheatre.setScreens(createScreen());
        pvrTheatre.setCity(Delhi);
        List<Shows> pvrShows = new ArrayList<>();
        Shows pvrMorningShow = createShows("3", pvrTheatre.getScreens().get(0), avengerMovie, 13);
        Shows pvrEveningShow = createShows("4", pvrTheatre.getScreens().get(0), baahubali, 20);
        pvrShows.add(pvrMorningShow);
        pvrShows.add(pvrEveningShow);
        pvrTheatre.setShows(pvrShows);

        theatreController.addTheatre(Banglore, inoxTheatre);
        theatreController.addTheatre(Delhi, pvrTheatre);

    }

    private void createBooking(City userCity, String movieName) {


        //1. search movie by my location
        List<Movies> movies = movieController.getMoviesForCity(userCity);

        //2. select the movie which you want to see. i want to see Baahubali
        Movies interestedMovie = movieController.getMovieByName("BAAHUBALI");
        for (Movies movie : movies) {

            if ((movie.getName()).equals(movieName)) {
                interestedMovie = movie;
            }
        }

        //3. get all show of this movie in Bangalore location
        List<Shows> showsTheatreWise_ = theatreController.getAllShows(userCity);
//        Movies finalInterestedMovie = interestedMovie;
        List<Shows> showsTheatreWise=showsTheatreWise_.stream().filter(v->v.getMovie().getName().equals("BAAHUBALI")).collect(Collectors.toList());

        //4. select the particular show user is interested in
//        Map.Entry<Theatre,List<Shows>> entry = showsTheatreWise.entrySet().iterator().next();
//        List<Shows> runningShows = entry.getValue();
        Shows interestedShow = showsTheatreWise.get(0);

        //5. select the seat
        if(!interestedShow.getScreen().getSeats().get(30).isOccupied())
            interestedShow.getScreen().getSeats().get(30).setOccupied(true);
         else
         {
            //throw exception
            System.out.println("seat already booked, try again");
            return;
        }

        System.out.println("BOOKING SUCCESSFUL");
    }


    public static void main(String[] args) {
//        BookMyShow bookMyShow= new BookMyShow();
        Main obj= new Main();
        obj.initialize();

        City Banglore= new City();
        Banglore.setId("1");
        Banglore.setName("Banglore");

        City Delhi = new City();
        Delhi.setId("2");
        Delhi.setName("Delhi");

        Movies avengers = new Movies();
        avengers.setId("1");
        avengers.setName("AVENGERS");
        avengers.setDuration(128);

        //create Movies2
        Movies baahubali = new Movies();
        baahubali.setId("2");
        baahubali.setName("BAAHUBALI");
        baahubali.setDuration(180);
//        obj.movieController.addMovies(Banglore, b);

        obj.movieController.addMovies(Banglore, avengers);
        obj.movieController.addMovies(Banglore, baahubali);

        obj.createTheatre(obj.movieController, obj.theatreController, Banglore, Delhi);

        obj.createBooking(Banglore, "BAAHUBALI");
        obj.createBooking(Banglore, "BAAHUBALI");

    }
}