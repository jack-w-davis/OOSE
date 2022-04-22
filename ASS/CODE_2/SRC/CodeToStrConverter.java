import java.util.*;

public class CodeToStrConverter extends DisplayerDecorator
{
    private Map<String,Character> map = new HashMap<>();

    public CodeToStrConverter(String filePath,Displayer displayer)
    {
        setNext(displayer);
        readFile(filePath);
    }

    public Grid<String> performOperation(Maze maze)
    {
        Grid<String> grid = getNext().performOperation(maze);

        convertStringCode(grid);

        return grid;
    }

    private void convertStringCode(Grid<String> grid)
    {
        for(int y = 0; y < grid.getYGridSize(); y++)
        {
            for(int x = 0; x < grid.getXGridSize(); x++)
            {
                String code = grid.getSpace(y, x).getValue();
                grid.getSpace(y, x).setValue(get(code));
            }
        }
    }

    public void put(String key, char value)
    {
        map.put(key, value);
    }

    private void readFile(String filePath)
    {
        List<String> charContent = IOUtils.readFile(filePath);

        for(String s: charContent)
        {
            String[] parts = s.split("\\s+");
            put(parts[0],parts[1].charAt(0));
        }
    }

    private String get(String key)
    {
        String ret = key;

        if(map.containsKey(key))
            ret = map.get(key).toString();

        return ret;

    }
}
