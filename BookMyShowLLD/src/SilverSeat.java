public class SilverSeat extends Seat{
    String mealType;

    public SilverSeat(String id, float price, boolean isOccupied, String mealType) {
        super(id, price+100, isOccupied);
        this.mealType = mealType;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }
}
