import java.util.*;

public class Door extends GameOBJ implements Collidable, Drawable
{
    private static final String FILE_PATTERN = "(D[HV])(\\s+\\d){3}";
    private Orientation orientation = null;

    public Door(String... inParams)
    {
        orientation = Orientation.get(inParams[0]);

        int[] values = new int[3];

        for(int i = 0; i < 3; i++)
        {
            values[i] = Integer.parseInt(inParams[i+1]);
        }

    }

    public static String getFilePattern()
    {
        return FILE_PATTERN;
    }
}
