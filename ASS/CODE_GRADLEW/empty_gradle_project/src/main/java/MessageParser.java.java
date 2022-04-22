package davis.jack.mazegame;

class MessageParser extends GameObjParser
{
    public static final String PATTERN = "(M)(\\s+\\d){2}(\\s+).+";

    @Override
    public boolean validateLine(String line)
    {
        return line.matches(PATTERN);
    }

    @Override
    public void parseArgs(String line, Maze maze)
    {
        String[] args = line.split("\\s+",3);
        Message obj = new Message();
        obj.setRow(Integer.parseInt(args[1]));
        obj.setCol(Integer.parseInt(args[2]));
        obj.setText(args[3]);
    }

}