import java.util.*;
import java.util.stream.Collectors;

public class Game
{

    public static ArrayList<Tile> test = new ArrayList<>();
    public static void main(String[] args)
    {
        // List<GameOBJ> test = new ArrayList<>();

        test.add(new GameOBJ(1));
        test.add(new GameOBJ(2));
        test.add(new GameOBJ(3));

        test.add(new Wall(4));
        test.add(new Wall(5));
        test.add(new Wall(6));

        test.add(new Door(7));
        test.add(new Door(8));
        test.add(new Door(9));

        List<Tile> temp = getObj(Pickupable.class)
                          .stream()
                          .forEach(s -> {
                              String tst = s.getTest();
                              System.out.println(tst);
                          });
    }

    //TODO: Ask dave if this is ok
    //TODO: 
    public static <T extends Tile> List<Tile> getObj(Class<T> type)
    {
        @SuppressWarnings("unchecked")
        List<Tile> arr = test.stream()
                 .filter(type::isInstance)
                 .map(type::cast)
                 .collect(Collectors.toList());

        return arr;
    }


    public static <T extends Tile> void printArr(List<T> arr)
    {
        for(T ele: arr)
        {
            System.out.println(ele.getTest());
        }
    }
}