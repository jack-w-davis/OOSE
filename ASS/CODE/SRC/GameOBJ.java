public class GameOBJ implements Tile
{
    private int rm;

    public GameOBJ(int inRm)
    {
        rm = inRm;
    }

    public String getTest()
    {
        return "GameObj";
    }

    public int getRm()
    {
        return rm;
    }
}
