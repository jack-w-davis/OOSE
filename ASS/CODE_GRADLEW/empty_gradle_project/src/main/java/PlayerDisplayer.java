
public class PlayerDisplayer extends DisplayerDecorator
{
    public static final String CHAR_CODE = "P";

    public PlayerDisplayer(Displayer inNext)
    {
        setNext(inNext);
    }

    @Override
    public Grid<String> performOperation(Maze maze)
    {
        Grid<String> grid = getNext().performOperation(maze);

        int row = maze.getPlayerRow();
        int col = maze.getPlayerCol();

        putPlayer(grid, row, col);

        return grid;
    }

    private void putPlayer(Grid<String> grid, int row, int col)
    {
        int ySize = grid.getYTileSize();
        int xSize = grid.getXTileSize();

        int y = (int) Math.ceil(((float) ySize) / 2.0) - 1;
        int x = (int) Math.ceil(((float) xSize) / 2.0) - 1;

        grid.getTile(row, col).get(y, x).setValue(CHAR_CODE);
    }
}