import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.logging.Level;
import java.util.Collection.*;

public class Game 
{
    public static void main(String[] args)
    {
        //----------------------------------------------------------------------
        //------------------------MODEL-----------------------------------------
        //----------------------------------------------------------------------
            MazeParser<GameObjParser> mParser = new MazeParser<>();

            //ADDING LINEPARSERS
            mParser.addParser(new DoorParser());
            mParser.addParser(new WallParser());
            mParser.addParser(new KeyParser());
            //READING IN FILE
            List<String> fileContent = IOUtils.readFile("../RES/map_2.txt");     
            //PARSING FILE       
            Maze m = mParser.parseFileContent(fileContent);

        //----------------------------------------------------------------------
        //------------------------VIEW------------------------------------------
        //----------------------------------------------------------------------
            //Reading character map file
            // List<String> charContent = IOUtils.readFile("../RES/map_values.txt");

            //Adding the content of charMap to my map
            // for(String s: charContent)
            // {
                // String[] parts = s.split("\\s+");
                // charMap.addChar(parts[0],parts[1].charAt(0));
                // }
                
                // GameDisplayer displayer = new GameDisplayer(1,3,charMap,m);
                // displayer.displayMaze();
                
                
        //-----------------DISPLAYER SHIT---------------------------------------
                
        // WallDisplayer      w       = new WallDisplayer(1, 3);
        // DoorDisplayer      d       = new DoorDisplayer(w);
        // IntToCharConverter charMap = new IntToCharConverte r("../RES/map_values.txt",d);
        // charMap.performOperation(m);

        IntToCharConverter charMap = 
            new IntToCharConverter("../RES/map_values.txt",
                 new DoorDisplayer(
                 new WallDisplayer(1, 3)));
        
        charMap.performOperation(m);

    }

    public static void print(Grid<String> grid)
    {
        for(int y = 0; y < grid.getYGridSize(); y++)
        {
            for(int x = 0; x < grid.getXGridSize(); x++)
            {
                System.out.printf("%3s",grid.getSpace(y, x).getValue());
            }
            System.out.printf("\n");
        }
    }
}
