package jwdavis.parser;

import jwdavis.*;
import jwdavis.state.fire.Fire;
import jwdavis.state.State;


import java.util.stream.Collectors;
import java.util.regex.*;
import java.io.*;
import java.util.*;
import java.util.logging.Logger;
import java.util.ArrayList;

public interface StateParser
{
    String getLabel();
    State getState();
}
