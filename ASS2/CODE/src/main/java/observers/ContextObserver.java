package jwdavis.observers;


import java.util.regex.*;

public interface ContextObserver 
{
    public static final Pattern ATTENDED_PATTERN 
    = Pattern.compile( "(?<type>(fire|flood|chemical)) (?<attended>[+-]) (?<location>.+)");

    public static final Pattern TIME_PATTERN = Pattern.compile("\\d+");
    void update(String message);
}
