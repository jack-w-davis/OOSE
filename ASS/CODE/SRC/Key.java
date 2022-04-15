public class Key extends GameOBJ implements Interactable
{
    public static final String FILE_PATTERN = "(K)(\\s+\\d){3}";

    private int colour;
    
    public Key(String... inParams)
    {

    }

    public static String getFilePattern()
    {
        return FILE_PATTERN;
    }

    public void interact()
    {

    }
}
