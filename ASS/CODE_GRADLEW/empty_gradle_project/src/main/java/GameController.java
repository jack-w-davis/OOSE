

public class GameController 
{
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
            
        }
    }

    private void drawToScreen()
    {
        dis.performOperation(maze);
    }
}
