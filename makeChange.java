package clientRecordHashTable;

public class makeChange {

	//----------------------------------------------------------------------------------------
	// Given a value V, if we want to make a change for V cents, and we have an infinite
	// supply of each of coin C = C1, C2, .., Cm valued coins, what is the minimum number
	// of coins to make the change? If it’s not possible to make a change, print -1. Test Case

	// Input: coins[] = {25, 10, 5}, V = 30} 
	// Output: Minimum 2 coins required We can use one coin of 25 cents and one of 5 cents
	//----------------------------------------------------------------------------------------
	
	int[] coins; // coins available (25 cents, 10 cents, 5 cents, 1 cent)
	int amount = 0; // minimum amount of coins required to make change for V cents
	int V; // we have V cents
	// amount of times each coin is used
	int tooniesUsed, looniesUsed, quartersUsed, tensUsed, fivesUsed, onesUsed; 
	
	public makeChange () {
		this.coins = new int[0];
		V = 0;
	}
	
	public makeChange (int[] coins, int V) {
		this.coins = coins;
		this.V = V;
	}
	
	public int findMinimum () {
		amount = -1;
		
		
		//base case - the value of V and array of Coins cannot be empty
		if (this.noCents() || this.noCoins()) {
			if (noCents()) {
				System.out.println("Not able to make change because we have no cents.");
				return amount;
			}
			else {
				System.out.println("Not able to make change.");
				return amount;
			}
		}
		
		// if we have values then we check
		else {
			// Initialize cents used as 0
			tooniesUsed = 0; looniesUsed = 0; quartersUsed = 0; tensUsed = 0; fivesUsed = 0; onesUsed = 0; // check which coins we have
			//PART 1 - check which coins we have
			boolean toonie = false, loonie = false, quarter = false, ten = false, five = false, one = false; // check which coins we have
			for (int i = 0; i < coins.length; i++) {
				if (toonie && loonie && quarter && ten && five && one) // if all exist end search 
					break;
				if (coins[i] == 200) toonie  = true;
				if (coins[i] == 100) loonie  = true;
				if (coins[i] == 25) quarter  = true;
				if (coins[i] == 10) ten  = true;
				if (coins[i] == 5) five  = true;
				if (coins[i] == 1) one  = true;
			}
			//PART 2 - see which variations we can use from greatest to least
			int count = 0;
			// only use the coin as long as it exist in coins and we can add without exceeding V
			if (toonie) {
				while ((V - count) >=200) { // the difference between V and count must equal be less than the coin value
					count += 200;
					tooniesUsed++;
				}
			}
			if (loonie) {
				while ((V - count) >=100) {	// the difference between V and count must equal or exceed the coin value
					count += 100;			// add value to count
					looniesUsed++;			// increment the amount of times the coin was used
				}
			}
			if (quarter) {
				while ((V - count) >=25) { 
					count += 25;
					quartersUsed++;
				}
			}
			if (ten) {
				while ((V - count) >=10) { 
					count += 10;
					tensUsed++;
				}
			}
			if (five) {
				while ((V - count) >=5) { 
					count += 5;
					fivesUsed++;
				}
			}
			if (one) {
				while ((V - count) >=1) { 
					count += 1;
					onesUsed++;
				}
			}
			
			// final checker
			if (count != V) {
				amount = -1;
			}
			else
				amount = (tooniesUsed + looniesUsed + quartersUsed + tensUsed + fivesUsed + onesUsed);
			
			
		}
		

		return amount;
		
	}
	
	//----------------------------------------------------------------------------------------
	// getters
	//----------------------------------------------------------------------------------------
	
	public int[] getCoins() {
		return coins;
	}
	
	public String printCoins() {
		String printC = "";
		for (int i =0; i < coins.length; i++) {
			printC += coins[i] + "";
			if (i < coins.length-1) {
				printC += ", ";
			}
		}
		
		return printC;
	}
	
	public int getMinimumAmount() {
		return amount;
	}

	public int getCents() {
		return V;
	}
	
	public boolean noCents () { //check if we have no cents
		return (V == 0);
	}
	
	public boolean noCoins() {
		return (this.coins.length == 0); // check if we have no coins
	}
	
	//----------------------------------------------------------------------------------------
	// setters
	//----------------------------------------------------------------------------------------
	
	public void setCoins (int[] setCoins) {
		this.coins = setCoins;
	}
	
	public void setMinimumAmount(int minimum) {
		this.amount = minimum;
	}
	
	public void setCents (int cents) {
		V = cents;
	}
	
	public String toString () {
		String result = "";
		int minimum = this.findMinimum();
		boolean lastCoin = false;
		int Vchecker = 0;
		// Minimum 2 coins required We can use one coin of 25 cents and one of 5 cents
		
		// if it is not possible to make change
		if (amount == -1) {
			return "It is not possible to make change. Output: " + amount;
		}
		
		result+="Minimum of " + minimum + " coins required. " + "We can use ";
		if (tooniesUsed != 0) { // if coin was used then add amount of times to string result
			Vchecker += (tooniesUsed*200);
			
			result += tooniesUsed + " toonie";
			if (tooniesUsed != 1) // if we used more than one than make it plural
				result += "s";
		}
		
		if (looniesUsed != 0 && !lastCoin) {
			if (Vchecker != 0) { 	// if there were coins used before
				Vchecker += (looniesUsed*100); // add these coins to Vchecker
				if (Vchecker == V) 	// if this is the last coin put an "and"
					result += " and ";
				else				// if there are more coins to add then put a comma
					result += ", ";
			}
			else
				Vchecker += (looniesUsed*100);
			
			result += looniesUsed + " loonie";
			if (looniesUsed != 1) {
				result += "s";
			}
			
		}
		if (quartersUsed != 0 && !lastCoin) {
			if (Vchecker != 0) { 	// if there were coins used before
				Vchecker += (quartersUsed*25); // add these coins to Vchecker
				if (Vchecker == V) 	// if this is the last coin put an "and"
					result += " and ";
				else				// if there are more coins to add then put a comma
					result += ", ";
			}
			else
				Vchecker += (quartersUsed*25);
			
			result += quartersUsed + " coin";
			if (quartersUsed != 1) 
				result += "s";
			result += " of 25 cents";
		}
		if (tensUsed != 0 && !lastCoin) {
			if (Vchecker != 0) { 	// if there were coins used before
				Vchecker += (tensUsed*10); // add these coins to Vchecker
				if (Vchecker == V) 	// if this is the last coin put an "and"
					result += " and ";
				else				// if there are more coins to add then put a comma
					result += ", ";
			}
			else
				Vchecker += (tensUsed*10);
			
			result += tensUsed + " coin";
			if (tensUsed != 1) 
				result += "s";
			result += " of 10 cents";
		}
		if (fivesUsed != 0 && !lastCoin) {
			if (Vchecker != 0) { 	// if there were coins used before
				Vchecker += (fivesUsed*5); // add these coins to Vchecker
				if (Vchecker == V) 	// if this is the last coin put an "and"
					result += " and ";
				else				// if there are more coins to add then put a comma
					result += ", ";
			}
			Vchecker += (fivesUsed*5);
			
			result += fivesUsed + " coin";
			if (fivesUsed != 1) 
				result += "s";
			result += " of 5 cents";
		}
		if (onesUsed != 0 && !lastCoin) {
			if (Vchecker != 0) { 	// if there were coins used before
				Vchecker += (onesUsed*1); // add these coins to Vchecker
				if (Vchecker == V) 	// if this is the last coin put an "and"
					result += " and ";
				else				// if there are more coins to add then put a comma
					result += ", ";
			}
			else
				Vchecker += (onesUsed*1);
			
			result += onesUsed + " coin";
			if (onesUsed != 1) 
				result += "s";
			result += " of 1 cent";
		}
		
		return result + ".";
	}
	
	public static void main(String[] args) {
		System.out.println("Assignment 2, Question 3 - Taysean Wilson-Nolan (twilsonn)");
		makeChange test1 = new makeChange(new int[]{25, 10, 5, 1}, 65);
		test1.findMinimum();
		System.out.println("\nTest 1\nAmount of cents (V): " + test1.getCents());
		System.out.println("Coins: " + test1.printCoins());
		System.out.println(test1);
		
		makeChange test2 = new makeChange(new int[]{25, 10, 5}, 44);
		test2.findMinimum();
		System.out.println("\nTest 2\nAmount of cents (V): " + test2.getCents());
		System.out.println("Coins: " + test2.printCoins());
		System.out.println(test2);
		
	}
	
	// TEST CASES
	
//	Assignment 2, Question 3 - Taysean Wilson-Nolan (twilsonn)
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


}
