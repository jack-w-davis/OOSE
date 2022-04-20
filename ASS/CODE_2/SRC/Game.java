import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.logging.Level;
import java.util.Collection.*;

public class Game 
{
    public static void main(String[] args)
    {
        //------------------------MODEL----------------------------------
        //MAZE PARSING
        MazeParser<GameObjParser> mParser = new MazeParser<>();

        //ADDING LINEPARSERS
        mParser.addParser(new DoorParser());
        mParser.addParser(new WallParser());
        mParser.addParser(new KeyParser());
    
        //READING IN FILE
        List<String> fileContent = IOUtils.readFile("../RES/map.txt");     
        //PARSING FILE       
        Maze m = mParser.parseFileContent(fileContent);

        //------------------------VIEW-----------------------------------
        List<String> charContent = IOUtils.readFile("../RES/map_values.txt");     
        GameCharManager charMap = new GameCharManager();
        for(String s: charContent)
        {
            String[] parts = s.split("\\s+");
            charMap.addChar(parts[0],parts[1].charAt(0));
        }

        GameDisplayer displayer = new GameDisplayer(1,3,charMap,m);

        displayer.displayMaze();
    }
}
