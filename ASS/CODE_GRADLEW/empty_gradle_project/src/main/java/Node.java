package davis.jack.mazegame;
//TODO: Rename me
public class Node<T>
{
    private T item;

    public Node()
    {}

    public Node(T inItem)
    {
        item = inItem;
    }

    public void setValue(T inItem)
    {
        item = inItem;
    }

    public T getValue()
    {
        return item;
    }
}