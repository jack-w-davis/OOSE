package davis.jack.mazegame;

import java.util.*;

public class KeyDisplayer extends DisplayerDecorator
{
    public static final String CHAR_CODE = "K";

    public KeyDisplayer(Displayer inDisplayer)
    {
        setNext(inDisplayer);
    }

    @Override
    public Grid<String> performOperation(Maze maze)
    {
        Grid<String> grid = getNext().performOperation(maze);

        iterateOverKeys(grid, maze);

        return grid;
    }

    private void iterateOverKeys(Grid<String> grid, Maze maze)
    {
        Map2DList<Integer,Key> keyMap = maze.filterByType(Key.class);

        for(int row: keyMap.key1Set())
        {
            for(int col: keyMap.key2Set(row))
            {
                List<Key> keyList = keyMap.getValue(row, col);
                placeKeys(keyList, row, col, grid);
            }
        }
    }

    private void placeKeys(List<Key> keyList,int row, int col, Grid<String> grid)
    {
        List<Node<String>> innerSpaces = grid.getTile(row, col).getInnerCellNodes(); 
        //Takes which ever number is smaller, the number of keys or the 
        //number of spots in which to draw a key 
        int numkeys = Math.min(innerSpaces.size(), keyList.size());

        for(int index = 0; index < numkeys; index++){
            innerSpaces.get(index).setValue(CHAR_CODE);
        }
    }
}