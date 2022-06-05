package jwdavis.parser;


import jwdavis.*;
import jwdavis.parser.LineParser;
import jwdavis.state.State;
import jwdavis.utils.Map2D;

import java.io.*;
import java.util.stream.Collectors;
import java.util.regex.*;
import java.util.*;
import java.util.logging.Logger;
import java.util.ArrayList;

public class FileParser 
{
    private List<LineParser> parsers;

    public FileParser()
    {
        parsers = new ArrayList<>();
    }

    /**
     * Adds LineParsers for the fileParser to use. Each LineParser
     * corresponds to a possible emergency, Fire, Chemical, Flood.
     */
    public void addLineParser(LineParser... lineParsers)
    {
        for(LineParser parser: lineParsers)
        {
            this.parsers.add(parser);
        }
    }

    public Map2D<String,String,Integer> parseFile(List<String> lines)
    {
        Map2D<String,String,Integer> emergencies = new Map2D<>();

        for(String line: lines)
        {
            parseLine(line);            
        }

        return emergencies;
    }

    public void parseLine(String line)
    {
        System.out.println(line);
        for(LineParser ep: parsers)
        {
            if(line.matches(ep.getRegexString()))
            {
                System.out.printf(" -- I MATCH %s\n",ep.getPattern());
                ep.parseLine(line);
            }
        }
    }
}