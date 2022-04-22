import java.util.*;

public class Tile<T>
{
    private Map2D<Integer,Node<T>> spaces = new Map2D<>();
    private int xSize;
    private int ySize;

    public Tile(int inYSize, int inXSize)
    {
        ySize = inYSize;
        xSize = inXSize;
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

    public List<Node<T>> getInnerCellNodes()
    {
        List<Node<T>> innerCells = new ArrayList<>();

        for(int y = 1; y <= ySize; y++){
            for(int x = 1; x <= xSize; x++){
                innerCells.add( get(y, x) );
            }
        }

        return innerCells;
    }
    

}
