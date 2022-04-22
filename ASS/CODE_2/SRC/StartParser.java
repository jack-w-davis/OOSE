public class StartParser extends GameObjParser
{
    public static final String PATTERN = "(S)(\\s+\\d){2}";

    public boolean validateLine(String line)
    {
        return line.matches(PATTERN);
    }

    public void parseArgs(String line, Maze maze)
    {
        String[] args = line.split("\\+S\\+");
        
        maze.setPlayerCoords(Integer.parseInt(args[1]), 
                             Integer.parseInt(args[2]));
    }
}

class MessageParser extends GameObjParser
{
    public static final String PATTERN = "(M)(\\s+\\d){2}(\\s+).+";

    public boolean validateLine(String line)
    {
        return line.matches(PATTERN);
    }

    public void parseArgs(String line, Maze maze)
    {
        String[] args = line.split("\\s+",3);
        Message obj = new Message();
        obj.setRow(Integer.parseInt(args[1]));
        obj.setCol(Integer.parseInt(args[2]));
        obj.setText(args[3]);
    }

}
