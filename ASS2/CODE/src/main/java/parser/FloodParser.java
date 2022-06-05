package jwdavis.parser;

import jwdavis.*;
import jwdavis.state.flood.Flood;

import java.util.stream.Collectors;
import java.util.regex.*;
import java.io.*;
import java.util.*;
import java.util.logging.Logger;
import java.util.ArrayList;

public class FloodParser implements StateParser
{
    public static final String LABEL = "flood";

    public FloodParser(){}

    @Override
    public String getLabel()
    {
        return LABEL;
    }

    @Override
    public Flood getState()
    {
        return new Flood();
    }
    
}
