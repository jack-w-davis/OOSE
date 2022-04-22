package davis.jack.mazegame;

public class GameObj
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