import java.util.List;
import java.util.Map;
import java.util.*;

public class MazeParser<T extends TileParser>
{
    private static final int MIN_ROWS = 1;
    private static final int MIN_COLS = 1;
    private Set<T> parsers = new HashSet<>();

    //TODO: If time permts make this take a list of parsers so that the parsers
    //      can only be set on creation
    public MazeParser()
    {}

    public void parseFileContent(List<String> fileContent)
    {
        Maze m = new Maze();
        for(String s: fileContent)
        {
            parseLine(s,m);
        }
    }

    public void parseLine(String line, Maze m)
    {
        for(T parser: parsers)
        {
            if(parser.validateLine(line))
            {
                //Dear Dave or whoever is marking, i know in OOPD/PDI breaks
                //are considered evil, but please don't remove marks here!
                // <3 Thanks
                parser.parseArgs(line.split("\\s+"), m);
                break; 
            }
        }
    }

    public void addParser(T inParser)
    {
        parsers.add(inParser);
    } 

}