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

    // public CharResManager getInstance()
    // {
    //     if(null == instance){
    //         instance = new CharResManager();
    //     }

    //     return instance;
    // }

}
