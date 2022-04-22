package davis.jack.mazegame;

public class App 
{
    public static void main(String[] args)
    {
        MazeParser<GameObjParser> parser = initParser();
        Maze maze = parser.parseFileContent(IOUtils.readFile("../RES/demoMap.txt"));

        Displayer displayer = initDisplayer();
        Grid<String> g = displayer.performOperation(maze);
        print(g);

        maze.removeKeysAtTile(1,1);

        g = displayer.performOperation(maze);
        print(g);
    }

    public static MazeParser<GameObjParser> initParser()
    {
        MazeParser<GameObjParser> parser = new MazeParser<>();
        parser.addParser(new DoorParser());
        parser.addParser(new WallParser());
        parser.addParser(new KeyParser());

        return parser;
    }

    public static Displayer initDisplayer()
    {

        //TODO: Rename displayer to something better, I.E. wall, door and key
        //      dont display them, they get the code
        ColourDisplayer displayer =
            new ColourDisplayer(
            new CodeToStrConverter("../RES/map_values.txt",
            new KeyDisplayer(
            new DoorDisplayer(
            new WallDisplayer(1, 3)))));

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

        return displayer;
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
