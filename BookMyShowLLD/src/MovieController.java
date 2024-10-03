import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieController {
    Map<City, List<Movies>> cityMovies= new HashMap<>();
    public void addMovies(City city, Movies movie)
    {
        List<Movies> movies=cityMovies.getOrDefault(city, new ArrayList<>());
        movies.add(movie);
        cityMovies.put(city, movies);
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
