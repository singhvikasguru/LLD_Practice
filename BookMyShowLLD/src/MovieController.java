import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieController {
    Map<City, List<Movies>> cityMovies= new HashMap<>();
    List<Movies> allMovies;
    public void addMovies(City city, Movies movie)
    {
        List<Movies> movies=cityMovies.getOrDefault(city, new ArrayList<>());
        movies.add(movie);
        cityMovies.put(city, movies);
    }

    Movies getMovieByName(String movieName) {

        for(Movies movie : allMovies) {
            System.out.println(movie.getName());
            if((movie.getName()).equals(movieName)) {
                return movie;
            }
        }
        return null;
    }


    public List<Movies> getMoviesForCity(City city)
    {
        return cityMovies.get(city);
    }

    public Map<City, List<Movies>> getCityMovies() {
        return cityMovies;
    }

    public void setCityMovies(Map<City, List<Movies>> cityMovies) {
        this.cityMovies = cityMovies;
    }
}
