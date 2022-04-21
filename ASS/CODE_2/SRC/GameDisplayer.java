import java.util.*;

public class GameDisplayer
{
    private GameCharManager charMap = new GameCharManager();
    private int colSize;
    private int rowSize;
    private Maze maze;

    //TODO: Make int rowSize and colSize only take odd numbers > 1
    public GameDisplayer(int inRowSize, int inColSize,GameCharManager inCharMap, Maze inMaze)
    {
        charMap = inCharMap;
        rowSize = inRowSize;
        colSize = inColSize;
        maze = inMaze;
    }

    public void displayMaze()
    {
        int[][] wallCodes = new int[5][5];
        wallCodes = getWallIntCode();
        wallCodes = getPerimWallCode(wallCodes);
        String[][] wallArr = convertWallIntToChar(wallCodes);
        
        TEST(wallArr);
    }

    private int[][] getWallIntCode()
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

    private int[][] getPerimWallCode(int[][] wallArr)
    {
        wallArr = getHorizontalPerimWall(wallArr);
        wallArr = getVerticalPerimWall(wallArr);
        return wallArr;
    }

    private int[][] getVerticalPerimWall(int[][] wallArr)
    {
        int numRows = maze.getNumRows();
        int numCols = maze.getNumCols();

        for(int row = 0; row < numRows + 1; row++)
        {
            if(row != 0){
                wallArr[row][0]       = (wallArr[row][0]       | getDirMask(Dir.UP)); 
                wallArr[row][numCols] = (wallArr[row][numCols] | getDirMask(Dir.UP)); 
            }
            if(row != numRows){
                wallArr[row][0]       = (wallArr[row][0]       | getDirMask(Dir.DOWN)); 
                wallArr[row][numCols] = (wallArr[row][numCols] | getDirMask(Dir.DOWN)); 
            }
        }

        return wallArr;
    }

    private int[][] getHorizontalPerimWall(int[][] wallArr)
    {
        int numRows = maze.getNumRows();
        int numCols = maze.getNumCols();

        for(int col = 0; col < numCols + 1; col++){

            if(col != 0){
                wallArr[0]      [col] = (wallArr[0]      [col] | getDirMask(Dir.LEFT)); 
                wallArr[numRows][col] = (wallArr[numRows][col] | getDirMask(Dir.LEFT)); 
            }
            if(col != numCols){
                wallArr[0]      [col] = (wallArr[0]      [col] | getDirMask(Dir.RIGHT)); 
                wallArr[numRows][col] = (wallArr[numRows][col] | getDirMask(Dir.RIGHT)); 
            }
        }

        return wallArr;
    }

    private String[][] convertWallIntToChar(int[][] wallCode)
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
                    ret[row][col] = charMap.get("W" + wallCode[row][col]);
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
    

    private int calcColumnTerminalSize(int numCol)
    {
        return ((colSize + 1) * numCol) + 1;
    }

    private int calcRowTerminalSize(int numRow)
    {
        return ((rowSize + 1) * numRow) + 1;
    }

    public void TEST(String[][] wallArr)
    {
        for(int row = 0; row < (maze.getNumRows() + 1); row++)
        {
            for(int col = 0; col < (maze.getNumCols() + 1); col++)
            {
                System.out.printf("%s   ",wallArr[row][col]);
            }
            System.out.print("\n\n");
        }
    };


}

enum Dir
{
    UP,
    RIGHT,
    DOWN,
    LEFT
}
