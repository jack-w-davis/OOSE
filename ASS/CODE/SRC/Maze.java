import java.util.*;

public class Maze
{
    private static final String FILE_PATTERN = "(\\d\\s+\\d)*";
    private static final int minRows = 1;
    private static final int minCols = 1;

    private int numRows = 0;
    private int numCols = 0;

    //The starting position of the character
    private int startRow = 0;
    private int startCol = 0;
    
    //TODO: Explain how i work lol
    
    private Map2D<Integer,Property> gameMap = new Map2D<>();
        

    public Maze(int inRows, int inCols)
    {
        setRows(inRows);
        setCols(inCols);
    }

    public void putObj(int row, int col, Property obj)
    {   
        gameMap.put(row, col, obj);
    }

    public static String getFilePattern()
    {
        return FILE_PATTERN;
    }

    private void setRows(int dim)
    {
        numRows = dim;
    }

    private void setCols(int dim)
    {
        numCols = dim;
    }

}
