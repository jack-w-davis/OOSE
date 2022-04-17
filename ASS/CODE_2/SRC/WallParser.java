class WallParser extends TileParser
{
    public static final String PATTERN = "(W[VH])(\\s+\\d){2}";

    public boolean validateLine(String line)
    {
        return line.matches(PATTERN);
    }

    public void parseArgs(String[] inArgs, Maze maze)
    {
        GameObj obj = new Wall();
        obj.setRow(Integer.parseInt(inArgs[1]));
        obj.setCol(Integer.parseInt(inArgs[2]));
        maze.put(obj);
    }
}