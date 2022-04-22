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
