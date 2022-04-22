package davis.jack.mazegame;
import java.util.*;

public abstract class DisplayerDecorator implements Displayer
{
    private Displayer next;

    public void setNext(Displayer inNext)
    {
        next = inNext;
    }
    
    public Displayer getNext()
    {
        return next;
    }

    public void print(Grid<String> grid)
    {
        for(int y = 0; y < grid.getYGridSize(); y++)
        {
            for(int x = 0; x < grid.getXGridSize(); x++)
            {
                System.out.printf("%s",grid.getSpace(y, x).getValue());
            }
            System.out.printf("\n");
        }
    }
}