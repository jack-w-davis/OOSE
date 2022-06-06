package jwdavis.parser;

import jwdavis.*;
import jwdavis.state.chemical.Spill;

import java.util.stream.Collectors;
import java.util.regex.*;
import java.io.*;
import java.util.*;
import java.util.logging.Logger;
import java.util.ArrayList;

public class ChemParser implements StateParser
{
    public static final String LABEL = "chemical";

    public ChemParser(){}

    @Override
    public String getLabel()
    {
        return LABEL;
    }

    @Override
    public Spill getState()
    {
        return new Spill();
    }

}
