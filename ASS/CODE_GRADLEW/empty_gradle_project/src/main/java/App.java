

public class App 
{
    public static void main(String[] args)
    {
        //Initializes the maze parser
        MazeParser<GameObjParser> parser = initParser();
        
        Maze maze = parser.parseFileContent(IOUtils.readFile(args[1]));
        
        Displayer displayer = initDisplayer(args[2]);

        GameController cg = new GameController(displayer, maze);

    }

    public static MazeParser<GameObjParser> initParser()
    {
        //Creates the mazeParser and adds the individual line parser
        //to it.
        MazeParser<GameObjParser> parser = new MazeParser<>();
        parser.addParser(new DoorParser());
        parser.addParser(new WallParser());
        parser.addParser(new KeyParser());

        return parser;
    }

    public static Displayer initDisplayer(String fileLoc)
    {
        //Creates a nested decoration of 'Displayers', which all in turn
        //affect how the maze is displayed in some way.
        ColourDisplayer displayer =
            new ColourDisplayer(
            new CodeToStrConverter(fileLoc,
            new KeyDisplayer(
            new DoorDisplayer(
            new WallDisplayer(1, 3)))));

        PlayerDisplayer playDis = new PlayerDisplayer(displayer);

        String[] ansiColours = {
            "\033[0;31m", //0: RED
            "\033[0;32m", //1: GREEN 
            "\033[0;33m", //2: YELLOW
            "\033[0;34m", //3: BLUE 
            "\033[0;35m", //4: PURPLE
            "\033[0;36m"  //5: CYAN 
        };

        for(int i = 0; i < ansiColours.length; i++){
            displayer.put((i+1), ansiColours[i]);
        }

        return playDis;
    }

    public static void print(Grid<String> grid)
    {
        for(int y = 0; y < grid.getYGridSize(); y++)
        {
            for(int x = 0; x < grid.getXGridSize(); x++)
            {
                System.out.printf("%s",grid.getSpace(y, x).getValue());
            }
            System.out.printf("\n");
        }
    }
}
