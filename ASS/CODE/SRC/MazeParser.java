import java.security.KeyStore.Entry;
import java.util.*;

public class MazeParser 
{
    //The pattern used to split the file line, it just splits it by spaces
    private static final String DELIMIT_PAT = "\\s+";

    //TODO: STRATEGY PATTERN


    public static Maze parseFile(List<String> file)
    {
        //This line is assuming the first line of the file MUST contain
        //2 digits, number of Rows and number of Cols
        Maze maze = parseMapDimensions(file.remove(0));
        
        for(String s: file)
        {
            //Parse each line within the file, then adds it to map
            Property p = parseLine(s.trim()
                                    .toUpperCase());

            int[] loc = parseDigits(s);

            maze.put(loc[0], loc[1], p);
        }

        System.out.println("break at me");

        //TODO: Validate maze here, check for things like walls and doors being
        //in same tile
        return maze;
    }

    private static Maze parseMapDimensions(String line)
    {
        Maze maze = null;
        if(line.matches(Maze.getFilePattern()))
        {
            int[] dim = parseDigits(line);
            
            maze = new Maze(dim[0],dim[1]);
        }
        //TODO: Throw Exception here

        return maze;
    }

    private static Property parseLine(String line)
    {
        Property obj = null;
        String[] params = line.split(DELIMIT_PAT);

        //Creates the following:
        // - Door


        if(line.matches(Door.getFilePattern())){
            obj = new Door(params);
        }
        // - Wall
        else if(line.matches(Wall.getFilePattern())){
            obj = new Wall(params);
        }
        // - Key
        else if(line.matches(Key.getFilePattern())){
            obj = new Key(params);
        }

        return obj;
    }

    private static int[] parseDigits(String line)
    {
        int[] loc = Arrays.stream(line.split(DELIMIT_PAT))
                          .filter(s -> s.matches("\\d+"))
                          .mapToInt(Integer::valueOf)
                          .toArray();

        return loc;
    }
    
    
}
