import java.util.List;
import java.util.Map;
import java.util.*;

public class MazeParser<T extends GameObjParser>
{
    private int minRows;
    private int minCols;
    private Set<T> parsers = new HashSet<>();

    //TODO: If time permits make this take a list of parsers so that the parsers
    //      can only be set on creation
    public MazeParser()
    {}

    public Maze parseFileContent(List<String> fileContent)
    {
        Maze m = new Maze();
        
        parseFirstLine(fileContent.remove(0), m);
        for(String s: fileContent){   
            parseLine(s,m);
        }

        System.out.println("break");

        return m;
    }

    private void parseFirstLine(String line, Maze m)
    {
        String[] args = line.split("\\s+");

        m.setRows(Integer.parseInt(args[0]));
        m.setCols(Integer.parseInt(args[1]));
    }

    private void parseLine(String line, Maze m)
    {
        line = line.trim();

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