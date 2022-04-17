import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.logging.Level;
import java.util.Collection.*;

public class Game 
{
    private static final Logger logger = Logger.getLogger(Game.class.getName());
    public static void main(String[] args)
    {
        MazeParser<TileParser> mParser = new MazeParser<>();
        mParser.addParser(new DoorParser());
        mParser.addParser(new WallParser());
        mParser.addParser(new KeyParser());
    
        List<String> fileContent = IOUtils.readFile("../RES/map.txt");            
        mParser.parseFileContent(fileContent);
        
        System.out.println("break");
    }
}
