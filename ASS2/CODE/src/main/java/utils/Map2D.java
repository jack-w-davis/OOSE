package jwdavis.utils;
import java.util.*;

public class Map2D<K,L,V> 
{
    private Map<K,Map<L,V>> m = new HashMap<>();

    public Map2D(){}

    //If key one isn't in the map already,
    //It creates a new map for that key then adds it
    public void put(K key1, L key2, V value)
    {
        if(! m.containsKey(key1))
        {
            m.put(key1,new HashMap<L,V>());
        }
        putInnerKey(key1, key2, value);
    }

    private void putInnerKey(K key1, L key2, V value)
    {
        m.get(key1).put(key2,value);
    }
    
    public boolean containsKeys(K key1, L key2)
    {
        boolean contains = false;
        //Contains = true if both keys exist
        if(m.containsKey(key1)){
            if(m.get(key1).containsKey(key2)){
                contains = true;
            }   
        }
        return contains;
    }

    public V get(K key1, L key2)
    {
        V retVal = null;
        if(containsKeys(key1, key2))
        {
            retVal = m.get(key1).get(key2);
        }
        return retVal;
    }
}
