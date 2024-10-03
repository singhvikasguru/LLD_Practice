import java.util.HashSet;
import java.util.Set;

public class Game {
    Battlefield bf;
    Player p1;
    Player p2;
    Game()
    {
        bf= new Battlefield(8, new HashSet<>());
        p1= new Player("A", "pl1A", new RandomFireStrategy());
        p2= new Player("B", "pl2B", new RandomFireStrategy());

        Ship s1= new Ship("Ash-1", 0, 0, 2, true);
        Ship sh2= new Ship("Ash-2", 2, 2, 2, true);

        Ship sh3= new Ship("Bsh-1", 6, 6, 2, true);
        Ship sh4= new Ship("Bsh-2", 4, 4, 2, true);
        p1.ships.add(s1);
        p1.ships.add(sh2);

        p2.addShip(sh3);
        p2.addShip(sh4);
    }
    void aliveStatusUpdate(Player p, int c[])
    {
        for(Ship s:p.ships)
        {
            if(s.isAlive && c[0]>=s.x && c[0]<=s.x+s.size && c[1]>=s.y && c[1]<=s.y+s.size)
            {
                s.isAlive=false;
                System.out.println(s.name+" destroyed at :"+s.x+", "+s.y);
            }
        }
    }

    boolean hasWonTheGame(Player p)
    {
        for(Ship s: p.ships)
        {
            if(s.isAlive)
                return false;
        }
        return true;
    }
    void playGame()
    {
        Set<String> firedCoordinates= new HashSet<>();
        int c[];
        while(!hasWonTheGame(p1) && !hasWonTheGame(p2))
        {
            c=p1.stg.fireMissile(4, 8, firedCoordinates);
            c[0]=c[0]+4;
            System.out.println("A fired at: "+c[0]+","+c[1]);
            firedCoordinates.add(c[0]+","+c[1]);
            aliveStatusUpdate(p2, c);
            if(hasWonTheGame(p2))
            {
                System.out.println("A won");
                break;
            }

            c=p2.stg.fireMissile(4,8, firedCoordinates);
            System.out.println("B fired at: "+c[0]+","+c[1]);
            firedCoordinates.add(c[0]+","+c[1]);
            aliveStatusUpdate(p1, c);
            if(hasWonTheGame(p1))
            {
                System.out.println("B won");
                break;
            }
        }

    }







}
