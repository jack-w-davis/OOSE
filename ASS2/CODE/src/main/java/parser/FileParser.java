package jwdavis.parser;

import jwdavis.*;
import java.util.*;
import jwdavis.state.State;
import jwdavis.utils.Map2D;

import java.util.logging.Logger;

public class FileParser 
{
    @SuppressWarnings("PMD.FieldNamingConventions")
    private static final Logger logger = Logger.getLogger(FileParser.class.getName());

    private Map<String,StateParser> parsers;

    public FileParser()
    {
        parsers = new HashMap<>();
    }

    /**
     * Adds StateParsers for the fileParser to use. Each StateParser
     * corresponds to a possible emergency, Fire, Chemical, Flood.
     */
    public void addStateParser(StateParser... stateParsers)
    {
        for(StateParser p: stateParsers)
        {
            this.parsers.put(p.getLabel().toLowerCase(),p);
            String l = "Added stateParser: " + p.getLabel();
            logger.info(l);
        }
    }

    /**
     * parses a collection of strings (i.e. a files content) into a map of
     * emergencies.
     */
    public Map2D<String,String,Emergency> parseFile(Collection<String> lines)
    {
        Map2D<String,String,Emergency> emergencies = new Map2D<>();
        for(String line: lines)
        {
            if(line.matches("\\d+ (fire|flood|chemical) .+"))
            {
                parseLine(line,emergencies);            
            }
            else
            { 
                String l = "Invalid Emergency '" + line + "' was not parsed";
                logger.warning(l); 
            }
        }

        return emergencies;
    }

    /**
     * parses a line into an Emergency Object
     */
    public void parseLine(String line, Map2D<String,String,Emergency> emergencies)
    {
        String[] tokens = line.split(" ",3);
        
        int time = Integer.parseInt(tokens[0]);
        String type = tokens[1].trim().toLowerCase();
        State state = parsers.get(type).getState();
        String location = tokens[2].trim();
        
        emergencies.put(                       
            location.toLowerCase(),              // Key1: Location   
            type.toLowerCase(),                  // Key2: Type (Fire,Spill,Flood)
            new Emergency(time, state,location));// Value: The emergency itself
        
        String l = "Parsed emergency: " + line;
        logger.info(l);
    }
}