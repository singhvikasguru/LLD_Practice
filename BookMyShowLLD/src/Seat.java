public class Seat {
    String id;
    float price;
    boolean isOccupied;

    public Seat(String id, float price, boolean isOccupied) {
        this.id = id;
        this.price = price;
        this.isOccupied = isOccupied;
    }

    public Seat() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }
}
