import java.util.*;

public abstract class GameObjParser 
{
    public GameObjParser()
    {}
    
    abstract public boolean validateLine(String line);
    abstract public void parseArgs(String line,Maze maze);
}