import java.util.*;

public class Tile<T>
{
    private Map2D<Integer,Node<T>> spaces = new Map2D<>();
    private int xOffset;
    private int yOffset;

    public Tile(int inyOffset, int inxOffset)
    {
        yOffset = inyOffset;
        xOffset = inxOffset;
    }

    public void put(int y, int x, Node<T> inSpace)
    {
        spaces.put(y, x, inSpace);
    }

    public Node<T> get(int y,int x)
    {
        return spaces.get(y,x);
    }

    public Set<Integer> key1Set()
    {
        return spaces.key1Set();
    }

    public Set<Integer> key2Set(int y)
    {
        return spaces.key2Set(y);
    }

}
