import java.util.*;
import java.util.stream.Collectors;

public class Game
{
    public static void main(String[] args)
    {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));

        List<String> fileContent = IOUtils.readFile("../RESOURCES/example_map.txt");

        Maze m = MazeParser.parseFile(fileContent);
    }

    //TODO: Ask dave if this is ok
    //TODO: Make me work with maps
    // public static <T extends Tile> List<Tile> getObj(Class<T> type)
    // {
    //     @SuppressWarnings("unchecked")
    //     List<Tile> arr = test.stream()
    //              .filter(type::isInstance)
    //              .map(type::cast)
    //              .collect(Collectors.toList());

    //     return arr;
    // }

    // public static <T extends Tile> void printArr(List<T> arr)
    // {
    //     for(T ele: arr)
    //     {
    //         System.out.println(ele.getTest());
    //     }
    // }


}