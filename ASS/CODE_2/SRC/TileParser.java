import java.util.*;

//TODO: MOVE ALL PARSERS TO OWN SEPERATE CLASSES
public abstract class TileParser 
{
    //TODO: Strategey/Template pattern is here
    //Default Constructor
    public TileParser()
    {}
    
    abstract public boolean validateLine(String line);
    abstract public void parseArgs(String line,Maze maze);
}