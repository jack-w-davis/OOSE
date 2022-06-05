package jwdavis.parser;

import jwdavis.*;
import jwdavis.state.flood.Flood;

import java.util.stream.Collectors;
import java.util.regex.*;
import java.io.*;
import java.util.*;
import java.util.logging.Logger;
import java.util.ArrayList;

public interface TokenParser<T>
{
    T parseToken(String token);
}
