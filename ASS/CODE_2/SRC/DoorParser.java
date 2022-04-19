public class DoorParser extends TileParser
{
    public static final String PATTERN = "(D[HV])(\\s+\\d){3}";

    public boolean validateLine(String line)
    {
        return line.matches(PATTERN);
    }

    public void parseArgs(String line, Maze maze)
    {
        String[] args = line.split("\\s+");
        Door obj = new Door();
        obj.setOri(parseOrientation(   args[0]));
        obj.setRow(Integer.parseInt(   args[1]));
        obj.setCol(Integer.parseInt(   args[2]));
        obj.setColour(Integer.parseInt(args[3]));
        maze.put(obj);
    }

    private Orientation parseOrientation(String code)
    {
        Orientation ori = null;
        if(code.matches(".H")){
            ori = Orientation.HORIZONTAL;
        }else if(code.matches(".V")){
            ori = Orientation.VERTICAL;
        }

        return ori;
    }
}