import java.util.ArrayList;
import java.util.List;

public class Player {
    String name;
    String id;
    List<Ship> ships;
    Strategy stg;

    public Player(String name, String id, Strategy stg) {
        this.name = name;
        this.ships= new ArrayList<>();
        this.id = id;
        this.stg = stg;
    }

    public void addShip(Ship sh)
    {
        this.ships.add(sh);
    }
    public Strategy getStg() {
        return stg;
    }

    public void setStg(Strategy stg) {
        this.stg = stg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
