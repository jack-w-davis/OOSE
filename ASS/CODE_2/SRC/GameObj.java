import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.logging.Level;
import java.util.Collection.*;

abstract public class GameObj
{
    private int row;
    private int col;    
    
    public GameObj()
    {}

    public void setRow(int inRow) {
        row = inRow;
    }

    public void setCol(int inCol) {
        col = inCol;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}

abstract class OrientGameObj extends GameObj
{
    private Orientation ori;

    public void setOri(Orientation inOri){
        ori = inOri;
    }

    public Orientation getOri(){
        return ori;
    }
}

interface Colour
{
    abstract public void setColour(int inColour);
    abstract public int getColour();
}

class Message extends GameObj
{
    private String text;
    
    public Message()
    {
    }

    public void setText(String inText)
    {
        text = inText;
    }

    public String getText()
    {
        return text;
    }
}

class Wall extends OrientGameObj
{
    public static final char CHAR_CODE = 'K';

    public Wall()
    {
    }

    public char getCharCode()
    {
        return CHAR_CODE;
    }
}

class Door extends OrientGameObj implements Colour
{
    public static final char CHAR_CODE = 'D';
    private int colour;

    public Door(){}

    public char getCharCode()
    {
        return CHAR_CODE;
    }

    public void setColour(int inColour)
    {
        colour = inColour;
    }

    public int getColour()
    {
        return colour;
    }
}

class Key extends GameObj implements Colour
{
    public static final char CHAR_CODE = 'K';
    private int colour;

    public Key() 
    {}

    public char getCharCode()
    {
        return CHAR_CODE;
    }

    public void setColour(int inColour)
    {
        colour = inColour;
    }
    
    public int getColour()
    {
        return colour;
    }
}
