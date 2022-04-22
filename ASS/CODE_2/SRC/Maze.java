import java.util.*;

public class Maze
{
    //TODO: Replace me with method, or maybe even just leave me, i mean the
    //      number of rows and columns is static 
    private int numRows = 0;
    private int numCols = 0;
    private Boolean gameWon = false;
    private Player player = new Player();

    //TODO: Add player object here
    //TODO: Explain how i work lol
    private Map2DList<Integer,GameObj> objMap = new Map2DList<>();
        
    public Maze()
    {}

    public void put(GameObj obj)
    {   
        int row = obj.getRow();
        int col = obj.getCol();
        objMap.put(row, col, obj);
    }

    public void setPlayerCoords(int row, int col)
    {
        player.setCoords(row, col);
    }

    public void removeKeysAtTile(int row, int col)
    {
        List<GameObj> list = objMap.getValue(row, col);
        
        int index = 0;
        while(index < list.size())
        {
            if(list.get(index) instanceof Key)
            {
                objMap.remove(row, col, index);
            }
            else
            {
                index++;
            }
        }
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

    public <T> Map2DList<Integer,T> filterByType(Class<T> type)
    {
        return objMap.filterByType(type);
    }
}
