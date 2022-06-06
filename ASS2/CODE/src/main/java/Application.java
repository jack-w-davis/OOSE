package jwdavis;

//TODO: Rename utils to util
import jwdavis.utils.IO;
import jwdavis.utils.Map2D;
import jwdavis.parser.*;
import jwdavis.Emergency;
import jwdavis.Simulator;
import jwdavis.responders.*;

import java.util.ArrayList;
import java.util.List;

public class Application 
{
    public static void main(String[] args)
    {
        if(0 < args.length)
        {
            FileParser fp = initFileParser();
            Map2D<String,String,Emergency> map = readEmergenciesFromFile(args[0], fp);
            Simulator sim = new Simulator(map.values(), new ResponderCommImpl());
            sim.start();
        }
        else
        {
            System.out.println("Please enter a valid file");
        }
    }    

    /**
     * creates the fileParser for the simulator to use
     * TODO: make me a better explanation
     */
    public static FileParser initFileParser()
    {
        FileParser fp = new FileParser();
                                            //  PATTERN MATCHED
                                            //--------------------
        fp.addStateParser(new FireParser(), // '\d+ fire .+'
                          new FloodParser(),// '\d+ flood .+'
                          new ChemParser());// '\d+ chemical .+'
        return fp;
    }

    public static Map2D<String,String,Emergency> readEmergenciesFromFile(String filePath, FileParser fp)
    {
        List<String> fileContent = IO.readFileToList(filePath);
        Map2D<String,String,Emergency> em = fp.parseFile(fileContent);
        return em;
    }
}