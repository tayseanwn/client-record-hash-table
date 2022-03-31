package clientRecordHashTable;

public class HashNode<K, V>
{
    private HashNode<K, V> next;
    private K key;
    private V data;
    
    /**
     * Creates an empty node.
     */
    public HashNode()
    {
        next = null;
        key = null;
        data = null;
    }
    
    /**
     * Creates a node storing the specified element.
     *
     * @param elem  the element to be stored within the new node
     */
    public HashNode (K key, V data)
    {
        next = null;
        this.key = key;
        this.data = data;
    }
    
    /**
     * Returns the node that follows this one.
     *
     * @return  the node that follows the current one
     */
    public HashNode<K, V> getNext()
    {
        return next;
    }
    
    /**
     * Sets the node that follows this one.
     *
     * @param node  the node to be set to follow the current one
     */
    public void setNext (HashNode<K, V> node)
    {
        next = node;
    }
    
    /**
     * Returns the element stored in this node.
     *
     * @return  the element stored in this node
     */
    public K getKey()
    {
        return key;
    }
    
    public V getData() {
    	return data;
    }
    
    /**
     * Sets the element stored in this node.
     *
     * @param elem  the element to be stored in this node
     */
    public void setData (V data)
    {
        this.data = data;
    }
}
