package clientRecordHashTable;

import java.util.Arrays;

//----------------------------------------------------------------------------------------
  	 // Application: 
	 // Assist the staff to extract the client info, update client’s record and delete .
	 // Program (Hash Table): 
	 // Depict the search, insertions and deletion functions.
//----------------------------------------------------------------------------------------

//----------------------------------------------------------------------------------------
// Hash Table with Linear Probing
//----------------------------------------------------------------------------------------

public class hashTableLinearProbing<K,V> implements Dictionary<K,V> {
	
	//----------------------------------------------------------------------------------------
	// Instance Variables
	//----------------------------------------------------------------------------------------
	final int CAPACITY = 4;
	HashNode[] list;
	int sizeOfList;
	int count = 0;
	
	final int X = 33; 			//RECOMMENDED X: 33, 37, 39, 41
	final int M = 7;			// M is a prime number
	
	//----------------------------------------------------------------------------------------
	// Constructors
	//----------------------------------------------------------------------------------------

	public hashTableLinearProbing () {
		list = new HashNode[CAPACITY];
		sizeOfList = list.length;
	}
	public hashTableLinearProbing (int setCapacity) {
		list = new HashNode[setCapacity];
		sizeOfList = list.length;
	}
	//----------------------------------------------------------------------------------------
	// METHODS: get, remove, put
	//----------------------------------------------------------------------------------------


	public void put (K key, V data) throws DuplicatedKeyException{	// inserts new record with key & data or ERROR if dictionary already contains a record with the key
		HashNode<K,V> node = new HashNode(key,data); // create a new hash node
		int index;
		// 1. get key -> use PHF to convert to Integer -> locate index using Integer
		index = polynomialHashFunction(node.getKey().toString(), X, M); //use toString on key object
		
		 // a key exist in this index
			/*
			 * METHOD 2: Linear Probing
			 * Look for next available location
			 */
			boolean emptySpace = false;
			int position = 0;
			for (int i=0; i < sizeOfList; i++) {
				position = (index + i) % sizeOfList; // use circular array method to search
				if (list[position] == null) {
					emptySpace = true;
					list[position] = node; // add node to position front
					count++;
					System.out.println(key+" ("+data+") was put into the Hash Table at point "+position);
					break;
				}
				System.out.println("Could not add at point " + position + ". Using Linear Probing method and trying again...");
			}
			
			if (!emptySpace) { // With collisions we do not add
//				HashNode<K,V> current, previous;
//				current = list[index];
//				previous = null;
//				while (current.getNext()!= null) { // traverse to last node in chain
//					previous = current;
//					current = current.getNext();
//				}
//				current.setNext(node); // set the next of last node to our new node
				System.out.println("Collision when adding "+ key);
				throw new DuplicatedKeyException ("Error");
			}		
	}
	
	public V get (K key) { // returns the data associated with the given key or null if no record has the given key
		V result = null;
//		HashNode<K,V> current, previous;
		if (isEmpty()) { // if empty then return null
			System.out.println("Hash Table empty.");
			return null;
		}
		else  { 
			int index;
			index = polynomialHashFunction(key.toString(), X, M);

			boolean found = false;
			int position = 0;
			for (int i=0; i < sizeOfList-1; i++) {
				position = (index + i) % sizeOfList; // use circular array method to search
				if (list[position] != null) {
					if (list[position].getKey().equals(key)) {
						System.out.println(key +" was found and has the record: " + list[position].getData());
						result = (V)list[position].getData();
						found = true; 
						break;
					}
				}

			}
			if (found) {
				return result; // if found return data at position
			}
			else
				return result; // if not found return null
			
//			
//			if (list[index] == null) { // if no record data exist return null
//				System.out.println("We found no record with the key: " + key);
//				return null;
//			}
//			else { // if there is a match 
//				current = list[index]; // set current to the chain of keys we found at index
//				previous = null;
//				while (current != null && current.getKey() != key) { // traverse until we find key
//					previous = current; 
//					current = current.getNext();
//				} 
//				if (current == null) {
//					System.out.println("While searching index "+index+", we found no record with the key: " + key);
//					return null;
//				} 
//				else {
//					System.out.println(key +" was found and has the record: " + current.getData());
//					return current.getData();
//				}
//		
//			}
		}
	}
	
	public void remove (K key) throws NoKeyException {	// removes the record with the given key, or ERROR if there is no record with the given key 
//		HashNode<K,V> current, previous;
		int index;
		index = polynomialHashFunction(key.toString(), X, M);
		boolean found = false;
		int position = 0;
		for (int i=0; i < sizeOfList-1; i++) {
			position = (index + i) % sizeOfList; // use circular array method to search
			if (list[position] != null) {
				if (list[position].getKey().equals(key)) {
					found = true; 
					list[position] = null; //delete node
					System.out.println(key +" was found at index: " +position+ " and removed.");
					break;
				}
			}

		}
		if (!found) {
			System.out.println("No key found. Could not remove: " + key);
		}
		
			
		
//		if (list[index] == null) {
//			throw new NoKeyException ("Error");
//		}
//		else { // if there is a match 
//			current = list[index]; // set current to the chain of keys we found at index
//			previous = null;
//			while (current != null && current.getKey() != key) { // traverse until we find key
//				previous = current; 
//				current = current.getNext();
//			} 
//			if (current == null) { // no key found
//				System.out.println("No key found. Could not remove: " + key);
//			} 
//			else { // key was found
//				//CASE 0: if the only element
//				if (current.getNext() == null && previous == null) {
//					list[index] = null;
//				}
//				
//				//CASE 1: if last, then previous.setNext(null)
//				else if (current.getNext() == null) {
//					previous.setNext(null);
//				}
//				//CASE 2: if first, then list[index].next(current.next), current.next(null), 
//				else if (previous == null) {
//					previous = current; 
//					current = current.getNext(); // shift everything up one
//					previous.setNext(null); // remove previous which was the head node
//					list[index] = current; // set current as new head node
//				}
//				//CASE 3: if middle, then previous.next(current.next), 
//				else {
//					previous.setNext(current.getNext());
//				}
//				
//				System.out.println(key +" was removed");
//			}
//	
//		}
	}
	
	//----------------------------------------------------------------------------------------
	// METHODS: additional methods
	//----------------------------------------------------------------------------------------
	
	public int polynomialHashFunction (String str, int x, int M) { // HASHCODE
		
		int u, k; // for length of s, convert each letter into an int and add to hash table
		u = (int)str.charAt(0); // store first (int)char of string
		k=str.length();
		// Horner's Rule: (int)ck*x^k +...+ (int)c0*x^0) mod M, where M = k
		// 			    = ((((int)ck*x + (int)ck-1)x + (int)ck-2)x ... + (int)c0)x
		for (int i=1; i<(k-1); i++) { // start at second (int)char in string 
//			
			u = (u*x + (int)str.charAt(i)) % M;
		}
		return u;
	}
	
	public int counter () { // return size
		return this.count;
	}
	public boolean isEmpty() { // return if table if empty
		return (count==0);
	}
	public String toString () {
		String result = "";
		String key, data;
		HashNode <K,V> current, previous;
		for (int i=0; i < sizeOfList; i++) {
			System.out.print(i + ": [ ");
			if (list[i] == null) {
				data = "-";
				key = "-";
				System.out.print(key +" | " + data);
			}
			else {
				current = list[i]; // traverse through chain and show all keys
				System.out.print(current.getKey() +" | " + current.getData()); // print first key
				while (current.getNext() != null) { // traverse and show the rset
					previous = current;
					current = current.getNext();
					data = current.getData()+"";
					key = current.getKey()+"";
					System.out.print(", " +key +" | " + data);
				}
				
			} System.out.print(" ]\n");
		}
		
		return result;
	}
	
	
	//----------------------------------------------------------------------------------------
	// MAIN METHOD
	//----------------------------------------------------------------------------------------
		

	public static void main (String[] args) {
		
		// TEST1
		System.out.println("Assignment 2, Question 2 - Taysean Wilson-Nolan (twilsonn)\n");
		System.out.println("Test 1");
		hashTableLinearProbing<String, String> test1 = new hashTableLinearProbing<String, String>(3);
		try {test1.put("John Smith", "Client ID: 97378");} catch (DuplicatedKeyException e) {}
		System.out.println(test1);
		try {test1.put("Tory Nelson", "Client ID: 28374");} catch (DuplicatedKeyException e) {}
		System.out.println(test1);
		try {test1.put("Jake Brown", "Client ID: 18273");} catch (DuplicatedKeyException e) {}
		System.out.println(test1);
		test1.get("John Smith");
		test1.get("Tory Nelson");
		test1.get("Jake Brown");
		test1.get("Taysean Nolan");
		try {test1.remove("Tory Nelson");} catch (NoKeyException e) {System.out.println("Error: No Key Found");}
		System.out.println(test1);
		
		// TEST2
		System.out.println("Test 2");
		hashTableLinearProbing<String, String> test2 = new hashTableLinearProbing<String, String>(3);
		try {test2.put("Kelly Ann", "Client ID: 47593");} catch (DuplicatedKeyException e) {}
		System.out.println(test2);
		try {test2.put("Hary Smith", "Client ID: 82734");} catch (DuplicatedKeyException e) {}
		System.out.println(test2);
		try {test2.put("Jones Blares", "Client ID: 38499");} catch (DuplicatedKeyException e) {}
		System.out.println(test2);
		try {test2.remove("Hary Smith");} catch (NoKeyException e) {System.out.println("Error: No Key Found");}
		System.out.println(test2);
		try {test2.put("Hary Smith", "Client ID: 82734");} catch (DuplicatedKeyException e) {}
		System.out.println(test2);
		try {test2.remove("Hary Smith");} catch (NoKeyException e) {System.out.println("Error: No Key Found");}
		System.out.println(test2);
		test1.get("Hary Smith");
		
	}

	
}
