import java.util.*;
import java.util.stream.Collectors;

public class Map2D<K,V> 
{
    private Map<K,Map<K,ArrayList<V>>> m = new HashMap<>();

    public Map2D(){}

    public void put(K key1, K key2, V value)
    {
        //If key one isn't in the map already,
        //It creates a new map for that key then adds it
        if(! m.containsKey(key1))
        {
            m.put(key1,new HashMap<K,ArrayList<V>>());
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
            m.get(key1).put(key2, new ArrayList<V>());
            m.get(key1).get(key2).add(value);
        }
    }
    public boolean containsKeys(K key1, K key2)
    {
        boolean contains = false;
        //Contains = true if both keys exist
        if(m.containsKey(key1))
            if(m.get(key1).containsKey(key2))
                contains = true;

        return contains;
    }

    public <T extends V> Map2D<K,T> filterByType(Class<T> type)
    {
        //The object being returned
        Map2D<K,T> cl = new Map2D<>();

        //Iterates over the rows
        for(Map.Entry<K,Map<K,ArrayList<V>>> colEntry: m.entrySet())
        {
            //The value of the entry
            Map<K,ArrayList<V>> colVal = colEntry.getValue();
            K colKey = colEntry.getKey();
            //Inner Entry
            for(Map.Entry<K,ArrayList<V>> rowEntry: colVal.entrySet())
            {
                ArrayList<V> rowVal = rowEntry.getValue();
                K rowKey = rowEntry.getKey();

                for(V value: rowEntry.getValue())
                {
                    if(type.isInstance(value))
                    {
                        cl.put(colKey, rowKey, type.cast(value));
                    }
                }
            }
        }
        System.out.println("cloned");

        return cl;
    }
}
