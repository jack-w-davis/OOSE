package davis.jack.mazegame;
import java.util.*;

public class DoorDisplayer extends DisplayerDecorator
{
    public static final String CHAR_CODE = "D";

    public DoorDisplayer(Displayer inDisplayer)
    {
        setNext(inDisplayer);
    }
    
    @Override
    public Grid<String> performOperation(Maze maze)
    {
        Grid<String> grid = getNext().performOperation(maze);
        placeDoors(grid, maze);
        return grid;
    }

    private void placeDoors(Grid<String> grid, Maze maze)
    {
        Map2DList<Integer,Door> map = maze.filterByType(Door.class);
        
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
                t.get(y,0).setValue(CHAR_CODE);
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
                t.get(0,x).setValue(CHAR_CODE);
            }
        }
    }
}