public class Door extends GameOBJ implements Collidable
{
    public static final String FILE_PATTERN = "(D[HV])(\\s+\\d){3}";

    public Door()
    {

    }

    public static String getFilePattern()
    {
        return FILE_PATTERN;
    }
}
