public class Grid
{
    private int xSize;
    private int ySize;
    private int numRows;
    private int numCols;
    private Map2D<Integer,Tile> tiles = new Map2D<>();

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

        System.out.println("break");
    }

    //THIS METHOD ASSUMES THERES AN OVERLAP OF 1
    private void initSpaces()
    {
        int xTotalSize = getXSize();
        int yTotalSize = getYSize();
        int count = 0;
        for(int y = 0; y < yTotalSize; y++)
        {
            for(int x = 0; x < xTotalSize; x++)
            {
                spaces.put(y,x,new Node<String>("" + count));
                count++;
            }
        }
    }

    private void initTiles()
    {
        for(int row = 0; row < numRows; row++)
        {
            for(int col = 0; col < numCols; col++)
            {
                initTileAt(row, col);
            }
        }
    }

    private void initTileAt(int curRow, int curCol)
    {
        int yOffset = (1 + ySize) *  curRow;
        int rowEnd  = (1 + ySize) *  (curRow + 1);
     
        int xOffset = (1 + xSize) *  curCol;
        int colEnd  = (1 + xSize) *  (curCol + 1);

        Tile t = new Tile();

        System.out.printf("TILE %d %d:\n",curRow,curCol);

        for(int y = 0; y < (2 + ySize); y++)
        {
            for(int x = 0; x < (2 + xSize); x++)
            {
                System.out.printf("    - %d %d = ",y,x);
                System.out.printf("%d %d \n",y+yOffset,x + xOffset);
                
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

    private void putSpace(int y, int x, Node<String> value)
    {
        if(! spaces.containsKeys(y, x))
        {
            spaces.put(y, x, value);
        }
    }

}

    //     int yOffset = (1 + ySize) *  curRow;
    //     int rowEnd  = (1 + ySize) *  (curRow + 1);
     
    //     int xOffset = (1 + xSize) *  curCol;
    //     int colEnd  = (1 + xSize) *  (curCol + 1);

    //     Tile t = new Tile();

    //     System.out.printf("TILE %d %d:\n",curRow,curCol);

    //     for(int y = 0; y < (2 + ySize); y++)
    //     {
    //         for(int x = 0; x < (2 + xSize); x++)
    //         {
    //             System.out.printf("    - %d %d = ",y,x);
    //             System.out.printf("%d %d \n",y+yOffset,x + xOffset);
                
    //             Node<String> value = new Node<>("");

    //             putSpace(y+yOffset,x+xOffset,value);
    //             value = spaces.get(x+xOffset,y+yOffset);
    //             t.put(x, y, value);
                
    //         }
    //     }
    //     tiles.put(curRow,curCol,t);