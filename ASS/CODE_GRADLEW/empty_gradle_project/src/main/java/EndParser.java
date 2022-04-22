package davis.jack.mazegame;
public class EndParser extends GameObjParser 
{
    public static final String PATTERN = "(E)(\\s+\\d){2}";
    
    @Override
    public boolean validateLine(String line)
    {
        return line.matches(PATTERN);
    }
    
    @Override
    public void parseArgs(String line, Maze maze)
    {
        String[] args = line.split("\\s+");
        GameEnd obj = new GameEnd();
        obj.setRow(Integer.parseInt(args[1]));
        obj.setCol(Integer.parseInt(args[2]));
    }
}
