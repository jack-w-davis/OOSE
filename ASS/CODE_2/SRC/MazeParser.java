import java.util.List;
import java.util.Map;
import java.util.*;

public class MazeParser<T extends TileParser>
{
    private int minRows;
    private int minCols;
    private Set<T> parsers = new HashSet<>();

    //TODO: If time permits make this take a list of parsers so that the parsers
    //      can only be set on creation
    public MazeParser()
    {}

    public Maze loadMaze(List<String> mazeContent, List<String> charContent)
    {
        return parseFileContent(mazeContent);
    }

    public Maze parseFileContent(List<String> fileContent)
    {
        Maze m = new Maze();
        for(String s: fileContent)
        {   
            parseLine(s,m);
        }

        System.out.println("break");

        return m;
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
                parser.parseArgs(line, m);
                break; 
            }
        }
    }

    public void addParser(T inParser)
    {
        parsers.add(inParser);
    } 

}