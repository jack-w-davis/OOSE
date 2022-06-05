package jwdavis.parser;

import jwdavis.*;
import jwdavis.state.flood.Flood;

import java.util.stream.Collectors;
import java.util.regex.*;
import java.io.*;
import java.util.*;
import java.util.logging.Logger;
import java.util.ArrayList;

public class FloodParser extends LineParser
{
    public static final String PATTERN = "flood";

    public FloodParser(){}

    @Override
    public String getPattern()
    {
        return PATTERN;
    }
    
}
