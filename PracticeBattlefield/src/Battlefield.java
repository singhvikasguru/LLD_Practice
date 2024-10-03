import java.util.Set;

public class Battlefield {
    int n;
    int[][] grid;

    Set<String> firedCoordinates;

    public Battlefield(int n, Set<String> firedCoordinates) {
        this.n = n;
        grid= new int[n][n];
        this.firedCoordinates = firedCoordinates;
    }
}
