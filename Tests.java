package clientRecordHashTable;

import java.util.Scanner;

public class Tests {

	public static void main(String[] args) {
		
		//-------------------------------------------------------------------------------------
		// Q1
		//-------------------------------------------------------------------------------------

		measureTravelDistance test1 = new measureTravelDistance(1050,400,4, new int[]{100, 400, 600, 1000});
		measureTravelDistance test2 = new measureTravelDistance(700,200,3, new int[]{100, 300, 350});
		
		test1.refills();
		test2.refills();
		
		System.out.println("Integer d: "+test1.getDistance()+"\nInteger m: "+test1.getMiles()+"\nInteger n: "+test1.getStopLimit()+"\nStops: "+test1.getPoints()+"\n\n"+test1);		
		System.out.println("Output: " + test1.outputStops());
		
		System.out.println("\nTest 2");
		System.out.println("Integer d: "+test2.getDistance()+"\nInteger m: "+test2.getMiles()+"\nInteger n: "+test2.getStopLimit()+"\nStops: "+test2.getPoints()+"\n\n"+test2);		
		System.out.println("Output: " + test2.outputStops());
		
		// USER INPUT
		/*
		 * Uncomment to put input your own values for Question 1
		 */
		
//		Scanner scan = new Scanner(System.in);
//		System.out.println("\nCreate your own scenario:");
//		System.out.println("Constraints\n1 ≤ d ≤ 1100, 1 ≤ m ≤ 415, 1 ≤ n ≤ 300, 0 < stop1 < stop2 < ... < stop n < d");
//		System.out.println("Enter Integer d: ");
//		int inputD = scan.nextInt();
//		System.out.println("Enter Integer m: ");
//		int inputM = scan.nextInt();
//		System.out.println("Enter Integer n: ");
//		int inputN = scan.nextInt();
//		System.out.println("Enter Stops: ");
//		int inputS[] = new int[inputN];
//		for (int i=0; i<inputN; i++) {
//			inputS[i] = scan.nextInt();
//		}
//		
//		Q1 user1 = new Q1(inputD, inputM, inputN, inputS);
//		user1.refills();
//		System.out.println("Integer d: "+user1.getDistance()+"\nInteger m: "+user1.getMiles()+"\nInteger n: "+user1.getStopLimit()+"\nStops: "+user1.getPoints()+"\n\n"+user1);		
//		System.out.println("Output: " + user1.outputStops());
		
		
		//-------------------------------------------------------------------------------------
		// Q2
		//-------------------------------------------------------------------------------------

		// TEST1
		System.out.println("\nQuestion 2\n");
		System.out.println("Test 1");
		hashTableDoubleHashing<String, String> test1B = new hashTableDoubleHashing<String, String>(3);
		try {test1B.put("John Smith", "Client ID: 97378");} catch (DuplicatedKeyException e) {}
		System.out.println("\nTable: T");
		System.out.println(test1B);
		try {test1B.put("Tory Nelson", "Client ID: 28374");} catch (DuplicatedKeyException e) {}
		System.out.println("\nTable: T");
		System.out.println(test1B);
		try {test1B.put("Jake Brown", "Client ID: 18273");} catch (DuplicatedKeyException e) {}
		System.out.println("\nTable: T");
		System.out.println(test1B);
		test1B.get("John Smith");
		test1B.get("Taysean Nolan");
		try {test1B.remove("Tory Nelson");} catch (NoKeyException e) {System.out.println("Error: No Key Found");}
		System.out.println("\nTable: T");
		System.out.println(test1B);
		
		// TEST2
		System.out.println("Test 2");
		hashTableDoubleHashing<String, String> test2B = new hashTableDoubleHashing<String, String>(3);
		try {test2B.put("Kelly Ann", "Client ID: 47593");} catch (DuplicatedKeyException e) {}
		System.out.println("\nTable: T");
		System.out.println(test2B);
		try {test2B.put("Barry Ann", "Client ID: 82734");} catch (DuplicatedKeyException e) {}
		System.out.println("\nTable: T");
		System.out.println(test2B);
		try {test2B.put("Jo", "Client ID: 38499");} catch (DuplicatedKeyException e) {}
		System.out.println("\nTable: T");
		System.out.println(test2B);
		try {test2B.put("Hary Smith", "Client ID: 82734");} catch (DuplicatedKeyException e) {}	
		System.out.println("\nTable: T");
		System.out.println(test2B);		
		
		
		
		//-------------------------------------------------------------------------------------
		// Q3
		//-------------------------------------------------------------------------------------

		System.out.println("\nQuestion 3");
		makeChange test1C = new makeChange(new int[]{25, 10, 5, 1}, 65);
		test1C.findMinimum();
		System.out.println("\nTest 1\nAmount of cents (V): " + test1C.getCents());
		System.out.println("Coins: " + test1C.printCoins());
		System.out.println(test1C);
		
		makeChange test2C = new makeChange(new int[]{25, 10, 5}, 44);
		test2C.findMinimum();
		System.out.println("\nTest 2\nAmount of cents (V): " + test2C.getCents());
		System.out.println("Coins: " + test2C.printCoins());
		System.out.println(test2C);
		
		
		//-------------------------------------------------------------------------------------
		// Q4
		//-------------------------------------------------------------------------------------
		int[] S1 = new int[] {1, 2, 4};
		int m1 = S1.length;
		int N1 = 7;
		
		System.out.println("\nQuestion 4\n");
		makeChangeAllSolutions test1D = new makeChangeAllSolutions(S1, m1, N1) ;
		int countOne = test1D.count(S1, m1, N1);
		System.out.println("We have the following coins: " + test1D.printCoins()
				+ ".\nWe will make change for: " + test1D.getCents() + " cents."
				+ "\nWe've found the amount of ways we can make change for this amount.\nOutput: " + countOne);
		
		
		int[] S2 = new int[] {1,3,7,6};
		int m2 = 4;
		int N2 = 12;
		
		System.out.println("\nTest 2");
		makeChangeAllSolutions test2D = new makeChangeAllSolutions(S2, m2, N2) ;
		int countTwo = test2D.count(S2,m2,N2);
		System.out.println("We have the following coins: " + test2D.printCoins()
				+ ".\nWe will make change for: " + test2D.getCents() + " cents."
				+ "\nWe've found the amount of ways we can make change for this amount.\nOutput: " + countTwo);
		
	}
	
//
//	Test 1
//	Question 1
//	Integer d: 1050
//	Integer m: 400
//	Integer n: 4
//	Stops: 100, 400, 600 and 1000
//
//	The distance between the cities is 1050 mile(s), the car can travel at most 400 mile(s) on a full tank.
//	It suffices to make 3 refill(s): at point(s) 400, 600 and 1000.
//	This is the minimum number of refills as with a single refill one would only be able to travel at most 800 miles.
//
//	Output: 3
//
//	Test 2
//	Integer d: 700
//	Integer m: 200
//	Integer n: 3
//	Stops: 100, 300 and 350
//
//	Failed to reach destination after making 3 stop(s) for refills.
//	We can only travel a maximum distance of 200 on a full tank.
//	We would not be able to reach our distination at point 700 even if we did a refill at point: 350.
//	We would get stuck at point 550.
//
//	Output: -1
//
//	Question 2
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
//
//
//	Question 3
//
//	Test 1
//	Amount of cents (V): 65
//	Coins: 25, 10, 5, 1
//	Minimum of 4 coins required. We can use 2 coins of 25 cents, 1 coin of 10 cents and 1 coin of 5 cents.
//
//	Test 2
//	Amount of cents (V): 44
//	Coins: 25, 10, 5
//	It is not possible to make change. Output: -1
//
//	Question 4
//
//	We have the following coins: 1, 2, 4.
//	We will make change for: 7 cents.
//	We've found the amount of ways we can make change for this amount.
//	Output: 6
//
//	Test 2
//	We have the following coins: 1, 3, 7, 6.
//	We will make change for: 12 cents.
//	We've found the amount of ways we can make change for this amount.
//	Output: 11

}
