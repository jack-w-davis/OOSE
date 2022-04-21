//The abstract interface
interface Displayer
{
    // abstract public int getRowTileSize();
    // abstract public int getColTileSize();
    // abstract public int getTotalRowTileSize(int numRows);
    // abstract public int getTotalColTileSize(int numCols);

    abstract public Grid performOperation(Maze maze);
    
}

abstract class DisplayerDecorator implements Displayer
{
    private Displayer next;

    public void setNext(Displayer inNext)
    {
        next = inNext;
    }
    
    public Displayer getNext()
    {
        return next;
    }
}

class WallDisplayer extends DisplayerDecorator
{
    public WallDisplayer(Displayer inDisplayer)
    {
        setNext(inDisplayer);
    }

    public Grid performOperation(Maze maze)
    {
        Grid grid = getNext().performOperation(maze);
        
        // int[][] wallCodes = getWallIntCode(maze);
        // wallCodes = getPerimWallCode(maze, wallCodes);
        
        return null;
    }

    private int[][] getWallIntCode(Maze maze)
    {
        Map2DList<Integer,OrientGameObj> map = maze.getDrawable(OrientGameObj.class);
        
        int numRows = maze.getNumRows() + 1;
        int numCols = maze.getNumCols() + 1;

        int[][] wallArr = new int[numRows][numCols];

        for(OrientGameObj o: map.valueList())
        {
            switch(o.getOri())
            {
                case VERTICAL:
                    verticalWall(o,wallArr);
                    break;
                case HORIZONTAL:
                    horizontalWall(o,wallArr);
                    break;
            }
        }
        return wallArr;
    }

    // private int[][] getPerimWallCode(Maze maze, int[][] wallArr)
    // {
    //     wallArr = getHorizontalPerimWall(wallArr);
    //     wallArr = getVerticalPerimWall(wallArr);
    //     return wallArr;
    // }

    // private int[][] getVerticalPerimWall(Maze maze, int[][] wallArr)
    // {
    //     int numRows = maze.getNumRows();
    //     int numCols = maze.getNumCols();

    //     for(int row = 0; row < numRows + 1; row++)
    //     {
    //         if(row != 0){
    //             wallArr[row][0]       = (wallArr[row][0]       | getDirMask(Dir.UP)); 
    //             wallArr[row][numCols] = (wallArr[row][numCols] | getDirMask(Dir.UP)); 
    //         }
    //         if(row != numRows){
    //             wallArr[row][0]       = (wallArr[row][0]       | getDirMask(Dir.DOWN)); 
    //             wallArr[row][numCols] = (wallArr[row][numCols] | getDirMask(Dir.DOWN)); 
    //         }
    //     }

    //     return wallArr;
    // }

    // private int[][] getHorizontalPerimWall(Maze maze, int[][] wallArr)
    // {
    //     int numRows = maze.getNumRows();
    //     int numCols = maze.getNumCols();

    //     for(int col = 0; col < numCols + 1; col++){

    //         if(col != 0){
    //             wallArr[0]      [col] = (wallArr[0]      [col] | getDirMask(Dir.LEFT)); 
    //             wallArr[numRows][col] = (wallArr[numRows][col] | getDirMask(Dir.LEFT)); 
    //         }
    //         if(col != numCols){
    //             wallArr[0]      [col] = (wallArr[0]      [col] | getDirMask(Dir.RIGHT)); 
    //             wallArr[numRows][col] = (wallArr[numRows][col] | getDirMask(Dir.RIGHT)); 
    //         }
    //     }

    //     return wallArr;
    // }

    private String[][] convertWallIntToChar(Maze maze, int[][] wallCode)
    {
        int cornerColSize = (maze.getNumCols() + 1);
        int cornerRowSize = (maze.getNumRows() + 1);

        String[][] ret = new String[cornerRowSize][cornerColSize];
        
        for(int row = 0; row < (maze.getNumRows() + 1); row++)
        {
            for(int col = 0; col < (maze.getNumCols() + 1); col++)
            {
                if(wallCode[row][col] != 0)
                {
                    ret[row][col] = "W" + wallCode[row][col];
                }
                else
                {
                    ret[row][col] = " "; 
                }
            }
        }

        return ret;
    }

    private void verticalWall(OrientGameObj obj,int[][] wallArr)
    {
        int row = obj.getRow();
        int col = obj.getCol();

        wallArr[row  ][col] = (wallArr[row  ][col] | getDirMask(Dir.DOWN));
        wallArr[row+1][col] = (wallArr[row+1][col] | getDirMask(Dir.UP));
    }

    private void horizontalWall(OrientGameObj obj,int[][] wallArr)
    {
        int row = obj.getRow();
        int col = obj.getCol();

        wallArr[row][col  ] = (wallArr[row][col  ] | getDirMask(Dir.RIGHT));
        wallArr[row][col+1] = (wallArr[row][col+1] | getDirMask(Dir.LEFT));
    }

    private int getDirMask(Dir d)
    {
        int mask = 0;
        switch(d)
        {
            case UP:
                mask = 1;
                break;
            case RIGHT:
                mask = 2;
                break;
            case DOWN:
                mask = 4;
                break;
            case LEFT:
                mask = 8;
                break;
        }
        return mask;
    }
}