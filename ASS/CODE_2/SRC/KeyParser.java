class KeyParser extends TileParser
{
    public static final String PATTERN = "(K)(\\s+\\d){3}";

    public boolean validateLine(String line)
    {
        return line.matches(PATTERN);
    }

    public void parseArgs(String line, Maze maze)
    {
        String[] args = line.split("\\s+");
        Key obj = new Key();
        obj.setRow(Integer.parseInt(   args[1]));
        obj.setCol(Integer.parseInt(   args[2]));
        obj.setColour(Integer.parseInt(args[3]));
        maze.put(obj);
    }
}
