package davis.jack.mazegame;
import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

public class IOUtils
{
    public static final Scanner SC = new Scanner(System.in);

    //TODO: Add try catch here
    public static List<String> readFile(String filePath)
    {
        List<String> list = new ArrayList<>();

        try(BufferedReader br = 
            new BufferedReader(
            new InputStreamReader(
            new FileInputStream(
            new File(filePath)))))
        {
            // System.out.println(file.exists());//TODO: Replace me with a logging statement
            list = br.lines().collect(Collectors.toList());
        }
        catch(IOException e)
        {
            System.out.println("WHOOPSY FUCKING DO GRADLEW, NO EMPTY BLOCKS HERE");
        }


        return list;
    }   
}

