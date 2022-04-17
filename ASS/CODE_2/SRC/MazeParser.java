import java.util.List;
import java.util.Map;
import java.util.*;


/**
 * @
 */

public class MazeParser<T extends TileParser>
{
    private Map<String,T> parsers = new HashMap<>();

    //TODO: Add here
    //DEP-INJ: ObjParser
    public MazeParser(){}

    public void parseFileContent(List<String> fileContent)
    {
        for(String s: fileContent)
        {
            preParseLine(s);   
        }
    }

    public void preParseLine(String line)
    {
        line = line.trim();

        TileParser p = getParser(line.substring(0,1));
        
        p.parseLine(line);
    }

    public void addParser(String code, T inParser)
    {
        code = code.trim().toUpperCase();
        parsers.put(code,inParser);
    } 

    public TileParser getParser(String objCode)
    {
        objCode = objCode.trim().toUpperCase();
        TileParser objParser = null;        

        if(parsers.containsKey(objCode))
        {
            objParser = parsers.get(objCode);    
        }

        return objParser;
    }
}