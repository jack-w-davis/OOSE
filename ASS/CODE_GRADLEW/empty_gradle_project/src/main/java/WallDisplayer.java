
class WallDisplayer implements Displayer
{
    private int rowTileSize;
    private int colTileSize;

    public WallDisplayer(int inRowTileSize, int inColTileSize)
    {
        colTileSize = inColTileSize;
        rowTileSize = inRowTileSize;
    }

    @Override
    public Grid<String> performOperation(Maze maze)
    {
        Grid<Integer> grid = new Grid<>(maze,rowTileSize,colTileSize,0);
        createInnerWalls(grid, maze);
        createPerimWalls(grid);
        Grid<String> output = convertGrid(grid);

        return output;
    }

    private void createInnerWalls(Grid<Integer> grid, Maze maze)
    {
        Map2DList<Integer,OrientGameObj> map = maze.filterByType(OrientGameObj.class);

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

    private void createPerimWalls(Grid<Integer> grid)
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
                left  = applyBitOr(left, Direction.UP);
                right = applyBitOr(right, Direction.UP);

            }
            if(y != (maxY-1)){
                left  = applyBitOr(left, Direction.DOWN);
                right = applyBitOr(right, Direction.DOWN);
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
                up    = applyBitOr(up,   Direction.LEFT);
                down  = applyBitOr(down, Direction.LEFT);

            }
            if(x != (maxX-1)){
                up    = applyBitOr(up,   Direction.RIGHT);
                down  = applyBitOr(down, Direction.RIGHT);
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
                wallType = applyBitOr(wallType, Direction.UP);
            }
            if(y != (grid.getYTileSize() - 1)){
                wallType = applyBitOr(wallType, Direction.DOWN);
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
                wallType = applyBitOr(wallType, Direction.LEFT);
            }
            if(x != (grid.getXTileSize() - 1)){
                wallType = applyBitOr(wallType, Direction.RIGHT);
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

    private int applyBitOr(int val, Direction dir)
    {
        return (val | getDirMask(dir));
    }

    private int getDirMask(Direction d)
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