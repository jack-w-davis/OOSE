
//BASE IMEPLEMENTATION
public class MazeDisplayer implements Displayer
{
    private int rowTileSize;
    private int colTileSize;

    public MazeDisplayer(int inRowTileSize, int inColTileSize)
    {
        colTileSize = inColTileSize;
        rowTileSize = inRowTileSize;
    }

    public Grid<String> performOperation(Maze maze)
    {
        int rows = maze.getNumRows();
        int cols = maze.getNumCols();
        // return new Grid(rowTileSize,colTileSize,rows,cols);
        return null;
    }

}