package davis.jack.mazegame;
public class Player 
{
    private int row;
    private int col;

    public Player()
    {

    }   

    public void setCoords(int inRow, int inCol)
    {
        row = inRow;
        col = inCol;
    }

    public int getRow()
    {
        return row;
    }

    public int getCol()
    {
        return col;
    }
    
}
