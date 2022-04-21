public class Grid
{
    private static final int OVERLAP = 1;
    private static final String DEFAULT_STR = "";
    private int xSize;
    private int ySize;
    private int numRows;
    private int numCols;

    //A mapping of all the grid spaces
    private Map2D<Integer,Node<String>> spaces = new Map2D<>();
    
    public Grid(int yTileSize,int xTileSize,int inNumRows, int inNumCols)
    {
        xSize   = xTileSize;
        ySize   = yTileSize;
        numRows = inNumRows;
        numCols = inNumCols;
        initSpaces();
        initTiles();
    }

    private void initSpaces()
    {
        int xTotalSize = getXSize();
        int yTotalSize = getYSize();
        
        for(int y = 0; y < yTotalSize; y++){
            for(int x = 0; x < xTotalSize; x++){
                putSpace(y,x,new Node<String>(DEFAULT_STR));
            }
        }
    }

    private void initTiles()
    {
        for(int row = 0; row < numRows; row++){
            for(int col = 0; col < numCols; col++){
                initTileAt(row, col);
            }
        }
    }

    private void initTileAt(int curRow, int curCol)
    {
        int yOffset = (OVERLAP + ySize) *  curRow;
        int xOffset = (OVERLAP + xSize) *  curCol;

        Tile t = new Tile();

        // System.out.printf("TILE %d %d:\n",curRow,curCol);
        for(int y = 0; y < ((OVERLAP * 2) + ySize); y++)
        {
            for(int x = 0; x < ((OVERLAP * 2) + xSize); x++)
            {
                // System.out.printf("    - %d %d = ",y,x);
                // System.out.printf("%d %d \n",y+yOffset,x + xOffset);
                Node<String> value = spaces.get(y+yOffset,x+xOffset);
                t.put(y,x, value);
            }
        }
        tiles.put(curRow,curCol,t);
    }

    public int getXSize()
    {
        return ((1 + xSize) * numCols) + 1;
    }

    public int getYSize()
    {
        return ((1 + ySize) *  numRows) + 1;
    }

    public void putSpace(int y, int x, Node<String> value)
    {
        if(! spaces.containsKeys(y, x))
        {
            spaces.put(y, x, value);
        }
    }

    public Node<String> getSpace(int row, int col)
    {
        return spaces.get(row,col);
    }

    public Node<String> getTileSpace(int row, int col, int y, int x)
    {
        return tiles.get(row, col).get(y,x);
    }

    public Tile getTile(int row, int col)
    {
        return tiles.get(row, col);
    }

    public String[][] toStringArr()
    {
        String[][] arr = new String[getYSize()][getXSize()];

        for(int y: spaces.key1Set())
        {
            for(int x: spaces.key2Set(y))
            {
                arr[y][x] = getSpace(y,x).getValue();
            }
        }
        return arr;
    }

    public void print()
    {
        for(int y = 0; y < getYSize(); y++)
        {
            for(int x = 0; x < getXSize(); x++)
            {
                System.out.print(getSpace(y, x).getValue());
            }
            System.out.printf("\n");
        }
    }
}