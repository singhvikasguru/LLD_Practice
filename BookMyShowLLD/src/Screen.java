import java.util.ArrayList;
import java.util.List;

public class Screen {
    String id;
    List<Seat> seats;

    public void addSeats(Seat seat)
    {
        seats.add(seat);
    }
    public Screen(String id) {
        this.id = id;
        seats= new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
}
