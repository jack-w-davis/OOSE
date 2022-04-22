package davis.jack.mazegame;
class Door extends OrientGameObj implements Colour
{
    public static final char CHAR_CODE = 'D';
    private int colour;

    public Door(){}

    public char getCharCode()
    {
        return CHAR_CODE;
    }

    @Override
    public void setColour(int inColour)
    {
        colour = inColour;
    }

    @Override
    public int getColour()
    {
        return colour;
    }
}