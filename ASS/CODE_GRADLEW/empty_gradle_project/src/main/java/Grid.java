package davis.jack.mazegame;
import java.util.*;

public class Grid<T>
{
    private static final int BORDER = 1;

    private T defValue;  //The default value that should be set 
    private int ySize;   //The size of the inner cells rows
    private int xSize;   //The size of the inner cells cols
    private int numRows; //The number of rows
    private int numCols; //The number of columns
    private int xTotalGridSize; //The total size of grid with spaces
    private int yTotalGridSize; 

    //A mapping of all the grid spaces
    private Map2D<Integer,Tile<T>> tiles  = new Map2D<>();
    private Map2D<Integer,Node<T>> spaces = new Map2D<>();
    
    public Grid(Maze maze,int yTileSize,int xTileSize, T inDefValue)
    {
        ySize   = yTileSize;
        xSize   = xTileSize;
        numRows = maze.getNumRows();
        numCols = maze.getNumCols();
        xTotalGridSize = ((1 + xSize) * numCols) + 1;
        yTotalGridSize = ((1 + ySize) * numRows) + 1;
        defValue = inDefValue;
        initSpaces();
        initTiles();
    }

    public Grid(Grid<?> inGrid, T inDefValue)
    {
        ySize = inGrid.ySize;
        xSize = inGrid.xSize;
        numRows = inGrid.numRows;
        numCols = inGrid.numCols;
        defValue = inDefValue;
        xTotalGridSize = ((1 + xSize) * numCols) + 1;
        yTotalGridSize = ((1 + ySize) * numRows) + 1;
        initSpaces();
        initTiles();
    }

    private void initSpaces()
    {   
        for(int y = 0; y < yTotalGridSize; y++){
            for(int x = 0; x < xTotalGridSize; x++){
                putSpace(y,x, new Node<T>(defValue));
            }
        }
    }

    private void initTiles()
    {
        for(int row = 0; row < numRows; row++){
            for(int col = 0; col < numCols; col++){
                initTileAt(row, col);
            }
        }
    }

    private void initTileAt(int curRow, int curCol)
    {
        int yOffset = (BORDER + ySize) *  curRow;
        int xOffset = (BORDER + xSize) *  curCol;

        Tile<T> t = new Tile<>(ySize,xSize);

        // System.out.printf("TILE %d %d:\n",curRow,curCol);
        for(int y = 0; y < ((BORDER * 2) + ySize); y++)
        {
            for(int x = 0; x < ((BORDER * 2) + xSize); x++)
            {
                // System.out.printf("    - %d %d = ",y,x);
                // System.out.printf("%d %d \n",y+yOffset,x + xOffset);
                Node<T> value = spaces.get(y+yOffset,x+xOffset);
                t.put(y,x, value);
            }
        }
        tiles.put(curRow,curCol,t);
    }

    public Set<Integer> key1Set()
    {
        return spaces.key1Set();
    }

    public Set<Integer> key2Set(int y)
    {
        return spaces.key2Set(y);
    }

    public int getXSize()
    {
        return xSize;
    }

    public int getYSize()
    {
        return ySize;
    }

    public int getXTileSize()
    {
        return xSize + (BORDER * 2);
    }

    public int getYTileSize()
    {
        return ySize + (BORDER * 2);
    }

    public int getXGridSize()
    {
        return xTotalGridSize;
    }

    public int getYGridSize()
    {
        return yTotalGridSize;
    }

    public void putSpace(int y, int x, Node<T> value)
    {
        if(! spaces.containsKeys(y, x))
        {
            spaces.put(y, x, value);
        }
    }

    public Node<T> getSpace(int y, int x)
    {
        return spaces.get(y,x);
    }

    public Tile<T> getTile(int row, int col)
    {
        return tiles.get(row, col);
    }
}