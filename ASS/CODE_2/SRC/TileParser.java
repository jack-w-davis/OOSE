import java.util.*;

public abstract class TileParser 
{
    //TODO: Strategey/Template pattern is here
    private static final Map<String,TileParser> PARSERS = Map
        .ofEntries( 
            Map.entry("D", new DoorParser()),
            Map.entry("W", new WallParser()),
            Map.entry("K", new KeyParser())
        );

        
    //TODO: Throw exception here for non matching str
    public static TileParser getParser(String objCode)
    {
        objCode = objCode.trim().toUpperCase();
        TileParser objParser = null;        

        if(PARSERS.containsKey(objCode)){
            objParser = PARSERS.get(objCode);    
        }else{
            //TODO: Throw exception here for non matching str
        }

        return objParser;
    }
}

class DoorParser extends TileParser
{

}

class WallParser extends TileParser
{

}

class KeyParser extends TileParser
{

}
