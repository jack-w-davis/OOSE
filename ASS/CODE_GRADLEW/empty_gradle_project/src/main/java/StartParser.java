package davis.jack.mazegame;

public class StartParser extends GameObjParser
{
    public static final String PATTERN = "(S)(\\s+\\d){2}";

    @Override
    public boolean validateLine(String line)
    {
        return line.matches(PATTERN);
    }

    @Override
    public void parseArgs(String line, Maze maze)
    {
        String[] args = line.split("\\+S\\+");
        
        maze.setPlayerCoords(Integer.parseInt(args[1]), 
                             Integer.parseInt(args[2]));
    }
}
