
import java.util.*;

public class ColourDisplayer extends DisplayerDecorator
{
    //The esc-char used to turn reset a teminals colou
    public static final String DEF_TERM_COLOUR = "\033[m";

    //A map of all the colours (that this colourDisplay can draw)
    //The logic of coloured keys unlocking doors is unrelated
    //To the logic of drawing the coloured objects themself
    private Map<Integer,String> colourMap = new HashMap<>();

    public ColourDisplayer(Displayer inDisplayer)
    {
        setNext(inDisplayer);
    }

    @Override
    public Grid<String> performOperation(Maze maze)
    {
        Grid<String> grid = getNext().performOperation(maze);
        setColour(grid, maze);

        return grid;
    }

    private void setColour(Grid<String> grid, Maze maze)
    {
        List<Door> doorList = maze.filterByType(Door.class).valueList();
        colourInDoors(doorList, grid);
        iterateOverKeys(grid, maze);
    }

    public void put(int key, String value)
    {
        colourMap.put(key, value);
    }

    private String get(int key)
    {
        String ret = "";

        if(colourMap.containsKey(key))
        {
            ret = colourMap.get(key).toString();
        }

        return ret;
    }

    private void colourInDoors(List<Door> doorList,Grid<String> grid)
    {
        for(Door d: doorList)
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

    private void iterateOverKeys(Grid<String> grid, Maze maze)
    {
        Map2DList<Integer,Key> keyMap = maze.filterByType(Key.class);
        for(int row: keyMap.key1Set()){
            for(int col: keyMap.key2Set(row)){
                List<Key> keyList = keyMap.getValue(row, col);
                colourKeys(keyList, row, col, grid);
            }
        }
    }

    private void colourKeys(List<Key> keyList,int row, int col, Grid<String> grid)
    {
        List<Node<String>> innerSpaces = grid.getTile(row, col).getInnerCellNodes(); 
        //Takes which ever number is smaller, the number of keys or the 
        //number of spots in which to draw a key 
        int numkeys = Math.min(innerSpaces.size(), keyList.size());

        for(int index = 0; index < numkeys; index++){
            String origVal = innerSpaces.get(index).getValue();
            int colour = keyList.get(index).getColour();
            innerSpaces.get(index).setValue(get(colour) + origVal + DEF_TERM_COLOUR);
        }
    }


    private void verticalDoor(Door obj, Grid<String> grid)
    {
        int row        = obj.getRow();
        int col        = obj.getCol();
        String colour  = get(obj.getColour());
        Tile<String> t = grid.getTile(row, col);

        int yMax = Collections.max(t.key1Set());

        for(int y : t.key1Set())
        {
            if( !(y==0 || y==yMax) ){
                String val = t.get(y, 0).getValue();
                val = colour + val + DEF_TERM_COLOUR;
                t.get(y,0).setValue(val);
            } 
        }
    }

    private void horizontalDoor(Door obj, Grid<String> grid)
    {
        int row        = obj.getRow();
        int col        = obj.getCol();
        String colour  = get(obj.getColour());
        Tile<String> t = grid.getTile(row, col);

        int xMax = Collections.max(t.key2Set(0));

        for(int x : t.key2Set(1))
        {
            if( !(x==0 || x==xMax) )
            {
                String val = t.get(0, x).getValue();
                val = colour + val + DEF_TERM_COLOUR;
                t.get(0,x).setValue(val);
            } 
        }
    }
}