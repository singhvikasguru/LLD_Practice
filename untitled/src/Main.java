import java.util.*;

// Strategy interface for missile firing
interface MissileStrategy {
    int[] fireMissile(int N, Set<String> firedCoordinates);
}

// Concrete Strategy: Random Coordinate Firing
class RandomMissileStrategy implements MissileStrategy {
    @Override
    public int[] fireMissile(int N, Set<String> firedCoordinates) {
        Random rand = new Random();
        int x, y;
        do {
            x = rand.nextInt(N);
            y = rand.nextInt(N);
        } while (firedCoordinates.contains(x + "," + y));
        return new int[]{x, y};
    }
}

// Concrete Strategy: Diagonal Coordinate Firing
class DiagonalMissileStrategy implements MissileStrategy {
    private int nextIndex = 0;

    @Override
    public int[] fireMissile(int N, Set<String> firedCoordinates) {
        int x, y;
        do {
            x = nextIndex;
            y = nextIndex;
            nextIndex = (nextIndex + 1) % N;
        } while (firedCoordinates.contains(x + "," + y));
        return new int[]{x, y};
    }
}

// Ship Class
class Ship {
    private int x, y, size;
    private String id;

    public Ship(String id, int x, int y, int size) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public boolean isHit(int x, int y) {
        return x >= this.x && x < this.x + size && y >= this.y && y < this.y + size;
    }

    public String getId() {
        return id;
    }

    public int getX()
    {
        return this.x;
    }

    public int getY()
    {
        return this.y;
    }

    public int getSize()
    {
        return this.size;
    }
}

// Battlefield Class
class Battlefield {
    private int N;
    private char[][] grid;
    private List<Ship> ships;

    public Battlefield(int N) {
        this.N = N;
        this.grid = new char[N][N];
        this.ships = new ArrayList<>();
        initializeGrid();
    }

    private void initializeGrid() {
        for (int i = 0; i < N; i++) {
            Arrays.fill(grid[i], '-');
        }
    }

    public boolean addShip(Ship ship) {
        for (int i = ship.getX(); i < ship.getX() + ship.getSize(); i++) {
            for (int j = ship.getY(); j < ship.getY() + ship.getSize(); j++) {
                if (grid[i][j] != '-') {
                    return false; // Overlap detected
                }
            }
        }
        ships.add(ship);
        for (int i = ship.getX(); i < ship.getX() + ship.getSize(); i++) {
            for (int j = ship.getY(); j < ship.getY() + ship.getSize(); j++) {
                grid[i][j] = ship.getId().charAt(0);
            }
        }
        return true;
    }

    public boolean fireMissile(int x, int y) {
        if (grid[x][y] != '-') {
            String hitShipId = null;
            for (Ship ship : ships) {
                if (ship.isHit(x, y)) {
                    hitShipId = ship.getId();
                    break;
                }
            }
//            ships.removeIf(ship -> ship.getId().equals(hitShipId));
            if (hitShipId != null) {
                for (Iterator<Ship> iterator = ships.iterator(); iterator.hasNext(); ) {
                    Ship ship = iterator.next();
                    if (ship.getId().equals(hitShipId)) {
                        iterator.remove();
                        break;
                    }
                }
            }
            System.out.println("Hit! Ship " + hitShipId + " destroyed.");
            return true;
        } else {
            System.out.println("Miss!");
            return false;
        }
    }

    public boolean hasShipsRemaining() {
        return !ships.isEmpty();
    }

    public void display() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int getSize() {
        return N;
    }
}

// Player Class
class Player {
    private String name;
    private Battlefield battlefield;
    private MissileStrategy missileStrategy;
    private Set<String> firedCoordinates;

    public Player(String name, int N, MissileStrategy missileStrategy) {
        this.name = name;
        this.battlefield = new Battlefield(N);
        this.missileStrategy = missileStrategy;
        this.firedCoordinates = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public Battlefield getBattlefield() {
        return battlefield;
    }

    public boolean takeTurn(Player opponent) {
        int[] coordinates = missileStrategy.fireMissile(battlefield.getSize(), firedCoordinates);
        firedCoordinates.add(coordinates[0] + "," + coordinates[1]);
        System.out.println(name + " fires at (" + coordinates[0] + ", " + coordinates[1] + ")");
        return opponent.getBattlefield().fireMissile(coordinates[0], coordinates[1]);
    }
}

// Game Class
class Game {
    private Player playerA;
    private Player playerB;

    public Game(int N, MissileStrategy strategyA, MissileStrategy strategyB) {
        this.playerA = new Player("Player A", N, strategyA);
        this.playerB = new Player("Player B", N, strategyB);
    }

    public void addShipForPlayer(String playerName, Ship ship) {
        if (playerName.equals("A")) {
            playerA.getBattlefield().addShip(ship);
        } else if (playerName.equals("B")) {
            playerB.getBattlefield().addShip(ship);
        }
    }

    public void start() {
        boolean gameOver = false;
        while (!gameOver) {
            gameOver = playerA.takeTurn(playerB);
            if (playerB.getBattlefield().hasShipsRemaining()) {
                gameOver = playerB.takeTurn(playerA);
            }
            if (!playerA.getBattlefield().hasShipsRemaining()) {
                System.out.println("Player B wins!");
                break;
            }
            if (!playerB.getBattlefield().hasShipsRemaining()) {
                System.out.println("Player A wins!");
                break;
            }
        }
    }

    public void displayBattlefields() {
        System.out.println("Player A's Battlefield:");
        playerA.getBattlefield().display();
        System.out.println("Player B's Battlefield:");
        playerB.getBattlefield().display();
    }
}

// Main Class
public class Main {
    public static void main(String[] args) {
        int N = 10;

        // Choose strategies for players
        MissileStrategy strategyA = new RandomMissileStrategy();
        MissileStrategy strategyB = new DiagonalMissileStrategy();

        Game game = new Game(N, strategyA, strategyB);

        game.addShipForPlayer("A", new Ship("A1", 0, 0, 4));
        game.addShipForPlayer("B", new Ship("B1", 5, 5, 3));

        game.start();
        game.displayBattlefields();
    }
}
