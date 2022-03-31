package clientRecordHashTable;

import java.util.Arrays;

//----------------------------------------------------------------------------------------
  	 // Application: 
	 // Assist the staff to extract the client info, update client’s record and delete .
	 // Program (Hash Table): 
	 // Depict the search, insertions and deletion functions.
//----------------------------------------------------------------------------------------

//----------------------------------------------------------------------------------------
// Double Hashing Method for Hash Table
//----------------------------------------------------------------------------------------


public class hashTableDoubleHashing<K,V> implements Dictionary<K,V> {
	
	//----------------------------------------------------------------------------------------
	// Instance Variables
	//----------------------------------------------------------------------------------------
	final int CAPACITY = 4;
	HashNode[] list;
	int sizeOfList;
	int count = 0;
	
	final int X = 33; 			//RECOMMENDED X: 33, 37, 39, 41
	final int M = 3;			// M is a prime number
	
	//----------------------------------------------------------------------------------------
	// Constructors
	//----------------------------------------------------------------------------------------

	public hashTableDoubleHashing () {
		list = new HashNode[CAPACITY];
		sizeOfList = list.length;
	}
	public hashTableDoubleHashing (int setCapacity) {
		list = new HashNode[setCapacity];
		sizeOfList = list.length;
	}
	//----------------------------------------------------------------------------------------
	// METHODS: get, remove, put
	//----------------------------------------------------------------------------------------


	public void put (K key, V data) throws DuplicatedKeyException{	// inserts new record with key & data or ERROR if dictionary already contains a record with the key
		HashNode<K,V> node = new HashNode(key,data); // create a new hash node
		int h;
		// 1. get key -> use PHF to convert to Integer -> locate index using Integer
		h = polynomialHashFunction(node.getKey().toString(), X, M); //use toString on key object

			//-------------------------------------------------------------------------------------------
			/*
			 * DOUBLE HASHING
			 * h(k) + h'(k)
			 * h'(k) = q - (k mod q)
			 * k is the key value
			 * 		h'(k) = q - polynomialHashFunction (k, X, q)
			 * 		update the value of M with q
			 * 	EXAMPLE
			 * 	h'(25) = q - (k mod q) = 7 - (25 mod 7) = 7 - 4 = 3
			 * 
			 *  EQUATION
			 *  h(k), (h(k)+h'(k)) mod M, (h(k)+2h'(k)) mod M, (h(k)+3h'(k)) mod M, ....
			 */
			//-------------------------------------------------------------------------------------------
			
			boolean emptySpace = false;
			final int q = 7;
			int position = 0, doublehash = 0, primeh = 0;
			for (int i=1; i < sizeOfList+1; i++) {
				 // use circular array method to search
				primeh = q - (polynomialHashFunction(node.getKey().toString(), X, q)); // formula for h'(k) = q-(k mod q)
				doublehash = (h + (i*primeh)) % M; // formula for doublehash
				position = doublehash % sizeOfList;
				if (list[position] == null) {
					emptySpace = true;
					list[position] = node; // add node to position front
					count++;
					System.out.println(key+" ("+data+") was put into the Hash Table at point "+position);
					break;
				}
				System.out.println("Could not add at point " + position + ". Using Double Hash method and trying again...");
			}
			
			if (!emptySpace) { // With collisions we do not add
				System.out.println("Collision Error: Could not add "+ key);
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
				position = (position + i) % sizeOfList; // use circular array method to search
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
			else {
				System.out.println("We found no record with the key: " + key);
				return result; // if not found return null
			}
		}
	}
	
	public void remove (K key) throws NoKeyException {	// removes the record with the given key, or ERROR if there is no record with the given key 
//		HashNode<K,V> current, previous;
		int index;
		index = polynomialHashFunction(key.toString(), X, M);
		boolean found = false;
		int position = 0;
		for (int i=0; i < sizeOfList; i++) {
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
		hashTableDoubleHashing<String, String> test1 = new hashTableDoubleHashing<String, String>(3);
		try {test1.put("John Smith", "Client ID: 97378");} catch (DuplicatedKeyException e) {}
		System.out.println("\nTable: T");
		System.out.println(test1);
		try {test1.put("Tory Nelson", "Client ID: 28374");} catch (DuplicatedKeyException e) {}
		System.out.println("\nTable: T");
		System.out.println(test1);
		try {test1.put("Jake Brown", "Client ID: 18273");} catch (DuplicatedKeyException e) {}
		System.out.println("\nTable: T");
		System.out.println(test1);
		test1.get("John Smith");
		test1.get("Taysean Nolan");
		try {test1.remove("Tory Nelson");} catch (NoKeyException e) {System.out.println("Error: No Key Found");}
		System.out.println("\nTable: T");
		System.out.println(test1);
		
		// TEST2
		System.out.println("Test 2");
		hashTableDoubleHashing<String, String> test2 = new hashTableDoubleHashing<String, String>(3);
		try {test2.put("Kelly Ann", "Client ID: 47593");} catch (DuplicatedKeyException e) {}
		System.out.println("\nTable: T");
		System.out.println(test2);
		try {test2.put("Barry Ann", "Client ID: 82734");} catch (DuplicatedKeyException e) {}
		System.out.println("\nTable: T");
		System.out.println(test2);
		try {test2.put("Jo", "Client ID: 38499");} catch (DuplicatedKeyException e) {}
		System.out.println("\nTable: T");
		System.out.println(test2);
		try {test2.put("Hary Smith", "Client ID: 82734");} catch (DuplicatedKeyException e) {}	
		System.out.println("\nTable: T");
		System.out.println(test2);
	}

	// TEST CASES
	
//	Assignment 2, Question 2 - Taysean Wilson-Nolan (twilsonn)
//
//	Test 1
//	John Smith (Client ID: 97378) was put into the Hash Table at point 1
//
//	Table: T
//	0: [ - | - ]
//	1: [ John Smith | Client ID: 97378 ]
//	2: [ - | - ]
//
//	Could not add at point 1. Using Double Hash method and trying again...
//	Tory Nelson (Client ID: 28374) was put into the Hash Table at point 2
//
//	Table: T
//	0: [ - | - ]
//	1: [ John Smith | Client ID: 97378 ]
//	2: [ Tory Nelson | Client ID: 28374 ]
//
//	Could not add at point 1. Using Double Hash method and trying again...
//	Jake Brown (Client ID: 18273) was put into the Hash Table at point 0
//
//	Table: T
//	0: [ Jake Brown | Client ID: 18273 ]
//	1: [ John Smith | Client ID: 97378 ]
//	2: [ Tory Nelson | Client ID: 28374 ]
//
//	John Smith was found and has the record: Client ID: 97378
//	We found no record with the key: Taysean Nolan
//	Tory Nelson was found at index: 2 and removed.
//
//	Table: T
//	0: [ Jake Brown | Client ID: 18273 ]
//	1: [ John Smith | Client ID: 97378 ]
//	2: [ - | - ]
//
//	Test 2
//	Kelly Ann (Client ID: 47593) was put into the Hash Table at point 1
//
//	Table: T
//	0: [ - | - ]
//	1: [ Kelly Ann | Client ID: 47593 ]
//	2: [ - | - ]
//
//	Barry Ann (Client ID: 82734) was put into the Hash Table at point 2
//
//	Table: T
//	0: [ - | - ]
//	1: [ Kelly Ann | Client ID: 47593 ]
//	2: [ Barry Ann | Client ID: 82734 ]
//
//	Could not add at point 1. Using Double Hash method and trying again...
//	Jo (Client ID: 38499) was put into the Hash Table at point 0
//
//	Table: T
//	0: [ Jo | Client ID: 38499 ]
//	1: [ Kelly Ann | Client ID: 47593 ]
//	2: [ Barry Ann | Client ID: 82734 ]
//
//	Could not add at point 1. Using Double Hash method and trying again...
//	Could not add at point 0. Using Double Hash method and trying again...
//	Could not add at point 2. Using Double Hash method and trying again...
//	Collision Error: Could not add Hary Smith
//
//	Table: T
//	0: [ Jo | Client ID: 38499 ]
//	1: [ Kelly Ann | Client ID: 47593 ]
//	2: [ Barry Ann | Client ID: 82734 ]

	
}
