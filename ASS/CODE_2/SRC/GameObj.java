import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.logging.Level;
import java.util.Collection.*;

abstract public class GameObj
{
    private int row;
    private int col;
    
    public GameObj()
    {}

    public void setRow(int inRow)
    {
        row = inRow;
    }

    public void setCol(int inCol)
    {
        col = inCol;
    }

    public int getRow()
    { 
        return row; 
    }

    public int getCol()
    {
        return col;
    }
}

class Wall extends GameObj
{
    private static final Logger logger = 
        Logger.getLogger(Wall.class.getName());
    public Wall()
    {
        logger.info(this.getClass().getName());
    }
}

class Door extends GameObj
{
    private static final Logger logger = 
        Logger.getLogger(Door.class.getName());
    public Door()
    {
        logger.info(this.getClass().getName());
    }
}

class Key extends GameObj
{
    private static final Logger logger = 
        Logger.getLogger(Key.class.getName());
    public Key()
    {
        logger.info(this.getClass().getName());
    }
}