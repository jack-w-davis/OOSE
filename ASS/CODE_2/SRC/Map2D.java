import java.util.*;
import java.util.stream.*;

public class Map2D<K,V> implements Iterable<V>
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
        if(! m.get(key1).containsKey(key2))
        {
            m.get(key1).put(key2, new ArrayList<V>());
        }
        m.get(key1).get(key2).add(value);
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

    public List<V> getValue(K key1, K key2)
    {
        List<V> retVal = null;
        if(containsKeys(key1, key2))
        {
            retVal = m.get(key1).get(key2);
        }
        return retVal;
    }

    public <T> Map2D<K,T> filterByType(Class<T> type)
    {
        Map2D<K,T> copy = new Map2D<>();
        for(K key1: key1Set()){

            for(K key2: key2Set(key1)){

                List<V> arr = m.get(key1).get(key2);
                for(V value: arr){

                    if(type.isInstance(value)){
                        copy.put(key1, key2, type.cast(value));
                    }
                }
            }
        }
        return copy;
    }

    public Iterator<V> iterator()
    {
        return valueList().iterator();
    }

    public List<V> valueList()
    {
        //Gets all the items from the map of ( map of) arraylist<V>
        //and then puts it in one list
        List<V> values = m.values()
                          .stream()
                          .flatMap(v -> v.values()
                                         .stream()
                                         .flatMap(v2 -> v2.stream()))
                           .collect(Collectors.toList());

        return values;
    }

    public Set<K> key1Set()
    {
        return m.keySet();
    }

    public Set<K> key2Set(K key1)
    {
        Set<K> s = new HashSet<>();

        if(m.containsKey(key1))
        {
            s = m.get(key1).keySet();
        }
        
        return s;
    }
}
