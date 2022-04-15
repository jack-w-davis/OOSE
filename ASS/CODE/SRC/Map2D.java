import java.util.*;

public class Map2D<K,V> 
{
    private Map<K,Map<K,List<V>>> m = new HashMap<>();

    public Map2D(){}

    public void put(K key1, K key2, V value)
    {
        //If key one isn't in the map already,
        //It creates a new map for that key then adds it
        if(! containsOutterKey(key1))
        {
            Map<K,List<V>> inner = new HashMap<>();
            m.put(key1,inner);
        }
        putInnerKey(key1, key2, value);
    }

    private void putInnerKey(K key1, K key2, V value)
    {
        //If Key 2 doesn't exists
        if(m.get(key1).containsKey(key2))
        {
            m.get(key1).get(key2).add(value);
        }
        //If key1 exists but not key 2
        else
        {
            List<V> arr = new ArrayList<>();
            arr.add(value);
            m.get(key1).put(key2, arr);
        }
    }
    public boolean containsKeys(K key1, K key2)
    {
        boolean contains = false;
        //Contains = true if both keys exist
        if(containsOutterKey(key1))
            if(m.get(key1).containsKey(key2))
                contains = true;

        return contains;
    }

    public boolean containsOutterKey(K key1)
    {
        return m.containsKey(key1);
    }


}
