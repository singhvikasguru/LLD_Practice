import java.util.Random;
import java.util.Set;

public class RandomFireStrategy implements Strategy{
    @Override
    public int[] fireMissile(int X, int Y, Set<String> firedCoordinates)
    {
        int x; int y;
        String coordinate="";
        Random rand = new Random();
        do{
            x= rand.nextInt(X);
            y=rand.nextInt(Y);
            coordinate=x+","+y;
        }while(firedCoordinates.contains(coordinate));
        return  new int[]{x, y};
    }
}
