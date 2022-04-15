import java.util.*;

public class MazeParser 
{
    public static final String DELIMIT_PAT = "\\s+";
    
    public static Maze parseFile(List<String> file)
    {
        Maze maze = parseMapDimensions(file.remove(0));
        
        for(String s: file)
        {
            Property p = parseLine(s.trim()
                                    .toUpperCase());
        }

        return maze;
    }

    private static Maze parseMapDimensions(String line)
    {
        Maze maze = null;
        if(line.matches(Maze.getFilePattern()))
        {
            int[] dim = Arrays.stream(line.split(DELIMIT_PAT))
            .mapToInt(Integer::valueOf)
            .toArray();
            
            maze = new Maze(dim[0],dim[1]);
        }
        //TODO: Throw Exception here

        return maze;
    }

    private static Property parseLine(String line)
    {
        Property obj = null;
        String[] params = line.split(DELIMIT_PAT);

        if(line.matches(Door.getFilePattern()))
        {
            obj = new Door(params);
        }
        else if(line.matches(Wall.getFilePattern()))
        {
            obj = new Wall(params);
        }
        else if(line.matches(Key.getFilePattern()))
        {
            obj = new Key(params);
        }

        return obj;
    }
    
    
}
