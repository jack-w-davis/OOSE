package jwdavis.parser;

import jwdavis.*;
import jwdavis.Emergency;
import jwdavis.state.State;

import java.util.stream.Collectors;
import java.util.regex.*;
import java.io.*;
import java.util.*;
import java.util.logging.Logger;
import java.util.ArrayList;


public abstract class LineParser
{
    // //TODO: Talk about how c#/python have closures/callbacks which is beter
    // //for making something like a token parser
    // private static final String START_PATTERN = "\\d+";
    // private static final String END_PATTERN   = ".+"; 

    // public String getStartPattern()
    // {
    //     return START_PATTERN;
    // }

    // public String getEndPattern()
    // {
    //     return END_PATTERN;
    // }
    
    // public String getRegexString()
    // {
    //     return String.format("%s %s %s", getStartPattern(),
    //                                      getPattern(),
    //                                      getEndPattern());
    // }
    
    // /**
    //  * Parses a line read in from file to an emergency. This method does not
    //  * check whether the number of 'tokens' matches the expected number, in this
    //  * case 3. This is because doing so would be redudant as the string returned
    //  * by {@link #getRegexString()} 
    //  */
    // public Emergency parseLine(String line)
    // {
    //     //Splits it into 3 tokens/parts
    //     String[] tokens = line.split(" ",3);

    //     int time = Integer.parseInt(tokens[0]);
    //     State state = parseState(); 
        
    //     return new Emergency(time,state,tokens[2]);
    // }
    
}




