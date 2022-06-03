package jwdavis.parser;

import java.util.stream.Collectors;

import jwdavis.*;

import java.util.regex.*;
import java.io.*;
import java.util.*;
import java.util.logging.Logger;
import java.util.ArrayList;


public abstract class EmergencyParser
{
    private static final String START_PATTERN = "\\d+";
    private static final String END_PATTERN   = ".+"; 

    abstract public String buildPattern();
    abstract public String getPattern();
    abstract public Emergency parseLine(String line);
    
    public String getStartPattern()
    {
        return START_PATTERN;
    }

    public int parseDigit(String token)
    {
        if(token.matches(START_PATTERN))
        {
            return Integer.parseInt(token);       
        }
        return 0;//TODO CHANGE ME
        //THROW EXCEPTON
    }

    public String getEndPattern()
    {
        return END_PATTERN;
    }

}




