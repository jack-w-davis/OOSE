package jwdavis.parser;

import java.util.stream.Collectors;

import jwdavis.emergency.Emergency;
import jwdavis.parser.EmergencyParser;
import jwdavis.state.State;

import java.util.regex.*;
import java.io.*;
import java.util.*;
import java.util.logging.Logger;
import java.util.ArrayList;

public class FileParser 
{
    private List<EmergencyParser> parsers;

    public FileParser()
    {
        parsers = new ArrayList<>();
    }

    /**
     * @param ep an array of parsers to add to the fileparser
     * 
     */
    public void addEmergencyParser(EmergencyParser... ep)
    {
        for(EmergencyParser parser: ep)
        {
            this.parsers.add(parser);
        }
    }

    public List<Emergency<? extends State>> parseCollection(Collection<String> lines)
    {
        for(String line: lines)
        {
            parseLine(line);            
        }

        return null;
    }

    public void parseLine(String line)
    {
        System.out.println(line);
        for(EmergencyParser ep: parsers)
        {
            if(line.matches(ep.buildPattern()))
            {
                System.out.printf(" -- I MATCH %s\n",ep.getPattern());
                ep.parseLine(line);
            }
        }
    }
}