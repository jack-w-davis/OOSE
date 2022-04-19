import java.util.*;

public class Maze
{
    //TODO: Replace me with method, or maybe even just leave me, i mean the
    //      number of rows and columns is static 
    private int numRows = 0;
    private int numCols = 0;

    //TODO: Add player object here
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

    //TODO: Change me so that i return an array which is printed out or something
    //      Actually maybe not
    public <T extends GameObj> Map2D<Integer,T> getDrawable(Class<T> type)
    {
        return objMap.filterByType(type);
    }

}
