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
}
