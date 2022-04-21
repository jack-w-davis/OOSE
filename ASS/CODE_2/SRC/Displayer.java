import java.util.*;

//The abstract interface
interface Displayer
{
    abstract public Grid<String> performOperation(Maze maze);
    
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

class WallDisplayer implements Displayer
{
    private int rowTileSize;
    private int colTileSize;

    public WallDisplayer(int inRowTileSize, int inColTileSize)
    {
        colTileSize = inColTileSize;
        rowTileSize = inRowTileSize;
    }

    public Grid<String> performOperation(Maze maze)
    {
        Grid<Integer> grid = new Grid<>(maze,rowTileSize,colTileSize,0);
        getInnerWalls(grid, maze);
        perimWalls(grid);
        Grid<String> output = convertGrid(grid);
        print(output);

        return output;
    }

    private void getInnerWalls(Grid<Integer> grid, Maze maze)
    {
        Map2DList<Integer,OrientGameObj> map = maze.getDrawable(OrientGameObj.class);

        for(OrientGameObj o: map.valueList())
        {
            switch(o.getOri())
            {
                case VERTICAL:
                    verticalWall(o,grid);
                    break;
                case HORIZONTAL:
                    horizontalWall(o,grid);
                    break;
            }
        }
    }

    private void perimWalls(Grid<Integer> grid)
    {
        perimLeftRight(grid);
        perimUpDown(grid);
    }
    
    private void perimLeftRight(Grid<Integer> grid)
    {
        int maxY = grid.getYGridSize();
        int maxX = grid.getXGridSize();
        for(int y = 0; y < maxY; y++)
        {
            int left  = grid.getSpace(y,     0).getValue();
            int right = grid.getSpace(y, (maxX - 1)).getValue();

            if(y != 0){
                left  = applyBitOr(left, Dir.UP);
                right = applyBitOr(right, Dir.UP);

            }
            if(y != (maxY-1)){
                left  = applyBitOr(left, Dir.DOWN);
                right = applyBitOr(right, Dir.DOWN);
            }

            grid.getSpace(y,   0).setValue(left);
            grid.getSpace(y, (maxX-1)).setValue(right);
        }
    }

    private void perimUpDown(Grid<Integer> grid)
    {
        int maxY = grid.getYGridSize();
        int maxX = grid.getXGridSize();
        for(int x = 0; x < maxX; x++)
        {
            int up   = grid.getSpace(         0,x).getValue();
            int down = grid.getSpace((maxY - 1),x).getValue();

            if(x != 0){
                up    = applyBitOr(up,   Dir.LEFT);
                down  = applyBitOr(down, Dir.LEFT);

            }
            if(x != (maxX-1)){
                up    = applyBitOr(up,   Dir.RIGHT);
                down  = applyBitOr(down, Dir.RIGHT);
            }

            grid.getSpace(0,x).setValue(up);
            grid.getSpace((maxY-1),x).setValue(down);
        }
    }

    //TODO: IF time permits refactor me
    private void verticalWall(OrientGameObj obj,Grid<Integer> grid)
    {
        int row = obj.getRow();
        int col = obj.getCol();

        Tile<Integer> t = grid.getTile(row, col);
        
        for(int y : t.key1Set())
        {
            int wallType = t.get(y,0).getValue();
            if(y != 0){
                wallType = applyBitOr(wallType, Dir.UP);
            }
            if(y != (grid.getYTileSize() - 1)){
                wallType = applyBitOr(wallType, Dir.DOWN);
            }
            t.get(y,0).setValue(wallType);
        }
    }

    private void horizontalWall(OrientGameObj obj,Grid<Integer> grid)
    {
        int row = obj.getRow();
        int col = obj.getCol();

        Tile<Integer> t = grid.getTile(row, col);
        
        for(int x : t.key2Set(0))
        {
            int wallType = t.get(0,x).getValue();
            if(x != 0){
                wallType = applyBitOr(wallType, Dir.LEFT);
            }
            if(x != (grid.getXTileSize() - 1)){
                wallType = applyBitOr(wallType, Dir.RIGHT);
            }
            t.get(0,x).setValue(wallType);
        }
    }

    //TODO: Maybe make me a helper function of some util class
    private Grid<String> convertGrid(Grid<Integer> grid)
    {
        Grid<String> output = new Grid<>(grid," ");

        for(int y: grid.key1Set())
        {
            for(int x: grid.key2Set(y))
            {
                String val = " ";
                if(0 < grid.getSpace(y, x).getValue())
                {
                    val = "W" + String.valueOf(grid.getSpace(y, x).getValue());
                } 
                output.getSpace(y,x).setValue(val);
            }
        }
        return output;
    }

    private int applyBitOr(int val, Dir dir)
    {
        return (val | getDirMask(dir));
    }

    private void print(Grid<String> grid)
    {
        for(int y = 0; y < grid.getYGridSize(); y++)
        {
            for(int x = 0; x < grid.getXGridSize(); x++)
            {
                System.out.printf("%3s",grid.getSpace(y, x).getValue());
            }
            System.out.printf("\n");
        }
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

enum Dir
{
    UP,
    RIGHT,
    DOWN,
    LEFT
}

class DoorDisplayer extends DisplayerDecorator
{
    public DoorDisplayer(Displayer inDisplayer)
    {
        setNext(inDisplayer);
    }

    public Grid<String> performOperation(Maze maze)
    {
        Grid<String> grid = getNext().performOperation(maze);

        placeDoors(grid, maze);

        return grid;
    }

    private void placeDoors(Grid<String> grid, Maze maze)
    {
        Map2DList<Integer,Door> map = maze.getDrawable(Door.class);
        
        for(Door d: map.valueList())
        {
            switch(d.getOri())
            {
                case VERTICAL:
                    verticalDoor(d,grid);
                    break;
                case HORIZONTAL:
                    horizontalDoor(d,grid);
                    break;
            }
        }
    }

    private void verticalDoor(Door obj, Grid<String> grid)
    {
        int row = obj.getRow();
        int col = obj.getCol();
        Tile<String> t = grid.getTile(row, col);

        int yMax = Collections.max(t.key1Set());

        for(int y : t.key1Set())
        {
            if( !(y==0 || y==yMax) ){
                t.get(y,0).setValue("D");
            }
        }
    }

    private void horizontalDoor(Door obj, Grid<String> grid)
    {
        int row = obj.getRow();
        int col = obj.getCol();
        Tile<String> t = grid.getTile(row, col);

        Set<Integer> keySet = t.key2Set(0);

        int xMax = Collections.max(keySet);

        for(int x : keySet)
        {
            if( !(x==0 || x==xMax) ){
                t.get(0,x).setValue("D");
            }
        }
    }
}


class ColourDisplayer extends DisplayerDecorator
{
    public ColourDisplayer(Displayer inDisplayer)
    {
        setNext(inDisplayer);
    }

    public Grid<String> performOperation(Maze maze)
    {
        Grid<String> grid = getNext().performOperation(maze);

        return grid;
    }

    private void setColour(Grid<String> grid, Maze maze)
    {
        Map2DList<Integer,Colour> map = maze.getDrawable(Colour.class);

        for(Colour c: map.valueList())
        {
            // switch(d.getOri())
            // {
                // case VERTICAL:
                //     verticalDoor(d,grid);
                //     break;
                // case HORIZONTAL:
                //     horizontalDoor(d,grid);
                //     break;
            // }
        }
    }
}