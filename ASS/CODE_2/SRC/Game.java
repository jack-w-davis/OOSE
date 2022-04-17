import java.util.*;

public class Game 
{
    public static void main(String[] args)
    {
        MazeParser<TileParser> mParser = loadParsers();

        mParser.preParseLine("DH");
    }

    public static MazeParser<TileParser> loadParsers()
    {
        MazeParser<TileParser> m = new MazeParser<>();
        m.addParser("D", new DoorParser());
        m.addParser("W", new WallParser());
        m.addParser("K", new KeyParser());

        return m;
    }
}
