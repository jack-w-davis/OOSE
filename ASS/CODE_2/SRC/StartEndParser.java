public class StartEndParser extends TileParser
{
    public static final String PATTERN = "(S|E)(\\s+\\d){2}";

    public boolean validateLine(String line)
    {
        return line.matches(PATTERN);
    }

    public void parseArgs(String line, Maze maze)
    {
        //TODO: Manip Player object here
        // maze.setRow(Integer.parseInt(inArgs[1]));
        // maze.setCol(Integer.parseInt(inArgs[2]));
    }
    
    private void parseStart()
    {

    }

    private void parseEnd()
    {
        
    }
}
class MessageParser extends TileParser
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
