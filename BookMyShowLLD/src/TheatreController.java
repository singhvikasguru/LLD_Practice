import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TheatreController {
    Map<City, List<Theatre>> theatreInCity;

    public void addTheatre(City city, Theatre theatre)
    {
        List<Theatre> theatres= theatreInCity.getOrDefault(city, new ArrayList<>());
        theatres.add(theatre);
        theatreInCity.put(city, theatres);
    }
    public List<Shows> getAllShows(City city)
    {
        List<Theatre> theatres=theatreInCity.get(city);
        List<Shows> shows= new ArrayList<>();
        for (Theatre theatre:theatres)
        {
            shows.addAll(theatre.getShows());
        }
        return shows;
    }

    public Map<City, List<Theatre>> getTheatreInCity() {
        return theatreInCity;
    }

    public void setTheatreInCity(Map<City, List<Theatre>> theatreInCity) {
        this.theatreInCity = theatreInCity;
    }
}
