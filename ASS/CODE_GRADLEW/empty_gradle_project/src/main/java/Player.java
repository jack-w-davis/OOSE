
import java.util.*;

public class Player 
{
    private int row;
    private int col;

    private List<Key> keys = new ArrayList<>();

    public Player()
    {

    }   

    public void setCoords(int inRow, int inCol)
    {
        row = inRow;
        col = inCol;
    }

    public void addKeyToPlayerInv(Key k)
    {
        keys.add(k);
    }

    public int getRow()
    {
        return row;
    }

    public int getCol()
    {
        return col;
    }
    
}
