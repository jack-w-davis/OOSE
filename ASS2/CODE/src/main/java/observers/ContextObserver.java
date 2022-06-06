package jwdavis.observers;


import java.util.regex.*;

public interface ContextObserver 
{
    public static final Pattern ATTENDED_PATTERN 
    = Pattern.compile( "(?<type>(fire|flood|chemical)) (?<attended>[+-]) (?<location>.+)");

    public void update(String message);
    public void updateCurTime(int curTime);
}
