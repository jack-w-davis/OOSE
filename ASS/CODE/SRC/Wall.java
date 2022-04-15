public class Wall extends GameOBJ implements Collidable
{
    private static final String FILE_PATTERN = "(W[VH])(\\s+\\d){2}";

    public Wall(String... inParams)
    {

    }

    public static String getFilePattern()
    {
        return FILE_PATTERN;
    }
}
