import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

public class IOUtils
{
    public static final Scanner sc = new Scanner(System.in);

    //TODO: Add try catch here
    public static List<String> readFile(String filePath)
    {
        List<String> list = new ArrayList<>();
        try
        {
            File file = new File(filePath);

            System.out.println(file.exists());//TODO: Replace me with a logging statement
            
            BufferedReader br = new BufferedReader(new FileReader(file));
            list = br.lines().collect(Collectors.toList());

            br.close();

        }
        catch(IOException e)
        {
            //TODO: Throw  me here, really the method calling should catch this
        }

        return list;
    }

    
}

