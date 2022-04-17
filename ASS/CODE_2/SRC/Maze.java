import java.util.*;

public class Maze
{
    private int numRows = 0;
    private int numCols = 0;

    //The starting position of the character
    
    //TODO: Explain how i work lol

    private Map2D<Integer,GameObj> objMap = new Map2D<>();
        
    public Maze()
    {}

    public void put(GameObj obj)
    {   
        int row = obj.getRow();
        int col = obj.getCol();
        objMap.put(row, col, obj);
    }

    public void setRows(int dim)
    {
        numRows = dim;
    }

    public void setCols(int dim)
    {
        numCols = dim;
    }

}
