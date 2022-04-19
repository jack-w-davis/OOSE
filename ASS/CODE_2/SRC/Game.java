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

        //------------------------MODEL----------------------------------
        //MAZE PARSING
        MazeParser<TileParser> mParser = new MazeParser<>();

        //ADDING LINEPARSERS
        mParser.addParser(new DoorParser());
        mParser.addParser(new WallParser());
        mParser.addParser(new KeyParser());
    
        //READING IN FILE
        List<String> fileContent = IOUtils.readFile("../RES/map.txt");     
        //PARSING FILE       
        Maze m = mParser.parseFileContent(fileContent);
        Map2D doors = m.getDrawable(Door.class);
        Map2D keys = m.getDrawable(Key.class);


        //------------------------VIEW-----------------------------------
        // GameDisplayer displayer = new GameDisplayer("../RES/map_values.txt");

    }
}
