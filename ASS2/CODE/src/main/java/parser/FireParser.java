package jwdavis.parser;

import jwdavis.*;
import jwdavis.state.fire.Fire;

import java.util.stream.Collectors;
import java.util.regex.*;
import java.io.*;
import java.util.*;
import java.util.logging.Logger;
import java.util.ArrayList;

public class FireParser extends LineParser
{
    public static final String PATTERN = "fire";

    public FireParser(){}

    @Override
    public String getPattern()
    {
        return PATTERN;
    }

    @Override
    public Fire parseState(String line)
    {
        return new Fire();
    }

}
