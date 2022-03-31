package clientRecordHashTable;

public class makeChangeAllSolutions {
	
	//--------------------------------------------------------------------------------------
	// Given a value N, if we want to make change for N cents, and we have infinite supply
	// of each of S = {S1, S2, .., Sm} valued coins, how many ways can we make the change?
	//
	// Optimal Substructure To count the total number of solutions, we can divide all set 
	// solutions into two sets.
	//
	// 1. Solutions that do not contain mth coin (or Sm).
	// 2. Solutions that contain at least one Sm.
	// 3. Let count(S[ ], m, n) be the function to count the number of solutions, then it  
	//	  can be written as sum of count(S[ ], m-1, n) and count(S[ ], m, n-Sm). 
	//--------------------------------------------------------------------------------------
	
	//--------------------------------------------------------------------------------------
	// Instance variables
	//--------------------------------------------------------------------------------------

	int N; // we will make change for N cents
	int[] S; // supply of coins available
	int m; // amount of coins we have (length of S)
	int[][] solutions;
	
	//--------------------------------------------------------------------------------------
	// Constructors
	//--------------------------------------------------------------------------------------

	public makeChangeAllSolutions () {
		this.N = 0;
		this.m = 0;
		this.S = new int[0];
	}
	
	public makeChangeAllSolutions (int[] S, int m, int N) {
		this.S = S;
		this.m = m;
		this.N = N;
	}
	

	//--------------------------------------------------------------------------------------
	// setters
	//--------------------------------------------------------------------------------------
	
	public void setCoins (int[] S) {
		this.S = S;
	}
	
	public void setCents (int N) {
		this.N = N;
	}
	
	public void updateAmountOfCoins(int m) { // length of coins (m) is determined by length of S
		this.m = m;
	}
	
	//--------------------------------------------------------------------------------------
	// getters
	//--------------------------------------------------------------------------------------

	public int count (int[] S, int m, int N) { // user can input the variables for S, m, N
		int sum = 0;
		
		//BASE CASES
		if (N==0) // if N equals 0 then we've satisfied the amount we need
			return 1;
		if (N < 0) // if N less than 0 then our result did not equal the exact amount of cents we needed
			return 0;
		if (m==0) //if m equals then we have no more coins left to use
			return 0;
		
		// There are two types of results
		
		// Category 1: Results that do not contain the last (Sm) coin
		//		count(S, m-1, N);
		// Category 2: Results that contain at least one of the last (Sm) coin (in our case m-1 is last)
		//		count(S, m, N-S[m-1]);
		
		/*
		 * 1. Recursively comparing how many diff. combinations we can use and how many times each value occurs
		 * 2. let our left node be count(S, m-1, N)  and our right node be count(S, m, N-S[m-1])
		 * 3. if our left node equals zero and our right node returns 1 then the sub-root is a solution
		 * Example c(S={1}, m=1, N=6} -> left return 0 + right -> c({1}, 1, 5)...c({1}, 1, 0) return 0
		 */
		
		int left = count(S, m-1, N);
		int right = count(S, m, N - S[m-1]);
		
		sum = left + right;
		
		return sum;
	}

	/*
	 * --------------------------------------------------------------------------------------
	 * Algorithm
	 * 
	 * Here each coin of a given denomination can come an infinite number of times.
	 * (Repetition allowed), this is what we call UNBOUNDED KNAPSACK. 
	 * We have 2 choices for a coin of a particular denomination, either:
	 * i) to include, or ii) to exclude.
	 * 
	 * But here, the inclusion process is not for just once; we can include any 
	 * denomination any number of times until N < 0.
	 *  
	 * Basically, If we are at s[m-1], we can take as many instances of that coin 
	 * ( unbounded inclusion ) i.e count(S, m, n – S[m-1] ) ; then we move to s[m-2]. 
	 * After moving to s[m-2], we can’t move back and can’t make choices for s[m-1] 
	 * i.e count(S, m-1, n ). 
	 * 
	 * Finally, as we have to find the total number of ways, so we will add these 2 
	 * possible choices, i.e count(S, m, n – S[m-1] ) + count(S, m-1, n ) ; which will 
	 * be our required answer.
	 * --------------------------------------------------------------------------------------
	 */
	
	public int getCents() {
		return this.N;
	}
	
	public int getAmountOfCoins() {
		return this.m;
	}
	
	public int[] getCoins() {
		return this.S;
	}
	
	public String printCoins() {
		String result = "";
		for (int i = 0; i<m; i++) {
			result += ""+S[i];
			if (i < (m-1))
				result += ", ";
		}
		return result;
	}
	

	//--------------------------------------------------------------------------------------
	// Main method
	//--------------------------------------------------------------------------------------

	public static void main(String[] args) {
		int[] S1 = new int[] {1, 2, 4};
		int m1 = S1.length;
		int N1 = 7;
		
		System.out.println("Assignmet 2, Question 4 - Taysean Wilson-Nolan (twilson)\nTest 1");
		makeChangeAllSolutions test1 = new makeChangeAllSolutions(S1, m1, N1) ;
		int countOne = test1.count(S1, m1, N1);
		System.out.println("We have the following coins: " + test1.printCoins()
				+ ".\nWe will make change for: " + test1.getCents() + " cents."
				+ "\nWe've found the amount of ways we can make change for this amount.\nOutput: " + countOne);
		
		
		int[] S2 = new int[] {1,3,7,6};
		int m2 = 4;
		int N2 = 12;
		
		System.out.println("\nTest 2");
		makeChangeAllSolutions test2 = new makeChangeAllSolutions(S2, m2, N2) ;
		int countTwo = test2.count(S2,m2,N2);
		System.out.println("We have the following coins: " + test2.printCoins()
				+ ".\nWe will make change for: " + test2.getCents() + " cents."
				+ "\nWe've found the amount of ways we can make change for this amount.\nOutput: " + countTwo);
		
	}
	
	// TEST CASES
	
//	Assignmet 2, Question 4 - Taysean Wilson-Nolan (twilson)
//	Test 1
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
