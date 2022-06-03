package jwdavis.parser;

import jwdavis.*;
import java.util.stream.Collectors;
import java.util.regex.*;
import java.io.*;
import java.util.*;
import java.util.logging.Logger;
import java.util.ArrayList;

public class FireParser extends EmergencyParser
{
    public static final String PATTERN = "fire";

    public FireParser(){}

    @Override
    public String buildPattern()
    {
        return String.format("%s %s %s", getStartPattern(),
                                         getPattern(),
                                         getEndPattern());
    }

    @Override
    public String getPattern()
    {
        return PATTERN;
    }

    @Override
    public Emergency parseLine(String line)
    {
        return null;
    }
}
