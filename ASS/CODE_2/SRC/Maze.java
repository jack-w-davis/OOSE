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

    public int getNumRows()
    {
        return numRows;
    }

    public int getNumCols()
    {
        return numCols;
    }

    public void setCols(int dim)
    {
        numCols = dim;
    }

    //TODO: RENAME ME TO FITLER TYPE OR SOMETHING
    // @SuppressWarnings("unchecked")
    public <T extends GameObj> Map2D<Integer,T> getDrawable(Class<T> type)
    {
        // Map2D<Integer,T> clone = new Map2D<>();
        // Map2D<Integer,T> copy = objMap.filterByType(type);
        
        // for(int key1: copy.key1Set())
        // {
        //     for(int key2: copy.key2Set(key1))
        //     {
        //         List<T> list = new ArrayList<>();
        //         for(T obj: copy.getValue(key1, key2))
        //         {
        //             try
        //             {
        //                 clone.put(key1, key2,(T) obj.clone());
        //             }catch(CloneNotSupportedException e)
        //             {}
        //         }
        //     }
        // }
        return objMap.filterByType(type);
    }

}
