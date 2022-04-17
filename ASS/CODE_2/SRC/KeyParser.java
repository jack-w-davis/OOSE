class KeyParser extends TileParser
{
    public static final String PATTERN = "(K)(\\s+\\d){3}";

    public boolean validateLine(String line)
    {
        return line.matches(PATTERN);
    }

    public void parseArgs(String[] inArgs, Maze maze)
    {
        GameObj obj = new Key();
        obj.setRow(Integer.parseInt(inArgs[1]));
        obj.setCol(Integer.parseInt(inArgs[2]));
        maze.put(obj);
    }
}
