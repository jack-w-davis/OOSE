import java.util.*;

//TODO: If time permits add interface here so that TileParser (via generics)
//      can be injected into maze parser where needed
public abstract class TileParser 
{
    //TODO: Strategey/Template pattern is here
    //Default Constructor
    public TileParser()
    {}

    //TODO: Throw exception here for non matching str

    // abstract public String parseLine(String line);

    public GameObj parseLine(String line)
    {
        parseArgs(line.trim().split("\\s+"));
        
        return null;
    }

    abstract public void parseArgs(String[] inArgs);
    abstract public boolean validateLine(String line);

}

class DoorParser extends TileParser
{
    // public String parseLine(String line);

    public DoorParser()
    {}

    public void parseArgs(String[] inArgs)
    {

    }

    public boolean validateLine(String line)
    {
        return  line.matches("(D[HV])(\\s+\\d){3}");

    }

}

class WallParser extends TileParser
{
    public void parseArgs(String[] inArgs)
    {

    }

    public boolean validateLine(String line)
    {
        return line.matches("(W[VH])(\\s+\\d){2}");
    }

}

class KeyParser extends TileParser
{

    public void parseArgs(String[] inArgs)
    {

    }

    public boolean validateLine(String line)
    {
        return line.matches("(K)(\\s+\\d){3}");
        
    }

}
