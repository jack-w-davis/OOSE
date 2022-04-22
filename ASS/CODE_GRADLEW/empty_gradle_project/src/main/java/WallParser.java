class WallParser extends GameObjParser
{
    public static final String PATTERN = "(W[VH])(\\s+\\d){2}";

    @Override
    public boolean validateLine(String line)
    {
        return line.matches(PATTERN);
    }

    @Override
    public void parseArgs(String line, Maze maze)
    {
        String[] args = line.split("\\s+");
        OrientGameObj obj = new Wall();
        obj.setOri(parseOrientation(args[0]));
        obj.setRow(Integer.parseInt(args[1]));
        obj.setCol(Integer.parseInt(args[2]));
        maze.put(obj);
    }

    public Orientation parseOrientation(String code)
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