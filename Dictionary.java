package clientRecordHashTable;

public interface Dictionary <K,V> {
	
	public V get(K key); // get key method
	
	public void put (K key, V data) throws DuplicatedKeyException; // put key with data method
	
	public void remove (K key) throws NoKeyException; // remove key method

}
