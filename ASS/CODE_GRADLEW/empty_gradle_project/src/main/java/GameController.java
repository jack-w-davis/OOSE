package davis.jack.mazegame;


public class GameController 
{
    private static final String MOVE_REGEX = "(N|S|E|W)";
    private Maze maze;
    private Displayer dis;

    public GameController(Displayer inDis, Maze inMaze)
    {
        dis = inDis;
        maze = inMaze;
    }    

    public void startGame()
    {
        while(maze.hasWonGame() == false)
        {
            drawToScreen();
            char dir = getPlayerInput();
            movePlayer(dir);
        }
    }

    private void drawToScreen()
    {
        Grid<String> g = dis.performOperation(maze);

        print(g);
        
    }

    private char getPlayerInput()
    {
        boolean isMatch = false;
        String dir = "";
        do
        {
            System.out.println("Please enter a direction. N,E,S,W");
            dir = IOUtils.getUserInput();
            isMatch = dir.toUpperCase().matches(MOVE_REGEX);
            if(isMatch == false)
            {
                System.out.println("Error, please enter a valid direction. N,E,S,W");
            }
        }
        while(isMatch == false);

        return dir.trim().charAt(0);

    }

    public void movePlayer(char input)
    {
        int nextRow = maze.getPlayerRow();
        int nextCol = maze.getPlayerCol();

        switch(input)
        {
            case 'N':
                nextRow -=1;
                break;
            
            case 'E':
                nextCol += 1;
                break;
            
            case 'S':
                nextRow +=1;
                break;

            case 'W':
                nextCol -= 1;
                break;
        }
        checkMovement(nextRow,nextCol);
    }

    public void checkMovement(int nextRow, int nextCol)
    {
        int curRow = maze.getPlayerRow();
        int curcol = maze.getPlayerCol();

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
