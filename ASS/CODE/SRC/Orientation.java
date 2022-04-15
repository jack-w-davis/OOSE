public abstract class Orientation 
{
    private static final String vert_pattern = "DV";
    private static final String hori_pattern = "DH";

    public static Orientation get(String key)
    {
        Orientation o = null;
        if(key.matches(vert_pattern))
        {
            o = new Vertical();
        }
        else if(key.matches(hori_pattern))
        {
            o = new Horizontal();
        }
        
        return o;
    }

    abstract public void getOrienation();
}

class Vertical extends Orientation
{
    public void getOrienation()
    {

    }
}

class Horizontal extends Orientation
{
    public void getOrienation()
    {
        
    }
}

