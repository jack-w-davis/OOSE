package jwdavis.utils;

import java.util.stream.Collectors;
import java.io.*;
import java.util.*;

public class IO 
{
    public static final String PATTERN = "\\d+ (fire|flood|chemical) .+";

    /**
     * reads a files content to a list of Strings. If the file isn't found
     * it throws a new FileNotFoundException
     * 
     * @param  filePath the filepath of the file to be read
     * @return a list of Strings, being the content of the file
     */
    public static List<String> readFileToList(String filePath)
    {
        List<String> list = new ArrayList<>();

        try(BufferedReader bufRdr = new BufferedReader(new FileReader(filePath)))
        {
            list = bufRdr.lines().collect(Collectors.toList());
        }
        catch(FileNotFoundException e)
        {
            System.out.printf("FILE '%'s NOT FOUND\n",filePath);
        }
        catch(IOException e)
        {
            System.out.println("IO EXCEPTION");
        }
        
        return list;
    }
}
