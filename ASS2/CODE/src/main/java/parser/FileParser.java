package jwdavis.parser;


import jwdavis.*;
import jwdavis.state.State;
import jwdavis.utils.Map2D;

import java.io.*;
import java.util.stream.Collectors;
import java.util.regex.*;
import java.util.*;
import java.util.logging.Logger;

public class FileParser 
{
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
        }
    }

    public Map2D<String,String,Emergency> parseFile(Collection<String> lines)
    {
        Map2D<String,String,Emergency> emergencies = new Map2D<>();
        for(String line: lines)
        {
            if(line.matches("\\d+ (fire|flood|chemical) .+"))
            parseLine(line,emergencies);            
        }

        return emergencies;
    }

    /**
     * parses a line into an Emergency Object
     */
    public void parseLine(String line, Map2D<String,String,Emergency> emergencies)
    {
        String[] tokens = line.split(" ",3);
        
        try
        {
            int time = Integer.parseInt(tokens[0]);
            String type = tokens[1].trim().toLowerCase();
            State state = parsers.get(type).getState();
            String location = tokens[2].trim();

            emergencies.put(                       
                location.toLowerCase(),              // Key1: Location   
                type.toLowerCase(),                  // Key2: Type (Fire,Spill,Flood)
                new Emergency(time, state,location));// Value: The emergency itself
        }
        catch(NullPointerException e)
        {
            //TODO: Log me here & throw my own excepion relating to 
            //the line not matching or something
        }
    }
}