//TODO: Rename me
public class Node<T>
{
    private T item;

    public Node()
    {}

    public Node(T inItem)
    {
        setValue(inItem);
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