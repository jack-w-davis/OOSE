public class Wall extends GameOBJ implements Collidable
{
    public static final String FILE_PATTERN = "(W[VH])(\\s+\\d){2}";

    public Wall()
    {

    }

    public static String getFilePattern()
    {
        return FILE_PATTERN;
    }
}
