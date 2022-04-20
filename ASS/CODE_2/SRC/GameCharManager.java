import java.util.*;

public class GameCharManager 
{
    private Map<String,Character> map = new HashMap<>();

    public GameCharManager()
    {
        
    }

    public void addChar(String key, char value)
    {
        map.put(key, value);
    }

    public String get(String key)
    {
        String ret = key;

        if(map.containsKey(key))
            ret = map.get(key).toString();

        return ret;

    }

}
