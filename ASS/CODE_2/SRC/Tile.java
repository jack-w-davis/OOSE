import java.util.*;

public class Tile 
{
    private Map2D<Integer,Node<String>> spaces = new Map2D<>();

    public Tile()
    {
        
    }

    public void put(int row, int col, Node<String> inSpace)
    {
        spaces.put(row, col, inSpace);
    }

    public Node<String> get(int row,int col)
    {
        return spaces.get(row,col);
    }

    public Set<Integer> key1Set()
    {
        return spaces.key1Set();
    }

    public Set<Integer> key2Set(int row)
    {
        return spaces.key2Set(row);
    }

}
