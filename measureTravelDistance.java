package clientRecordHashTable;
import java.util.Scanner;  // Import the Scanner class


/*
 * -------------------------------------------------------------------------------------------
 * Task
 * You are traveling to another city that is located d miles away from your home
 * Your car can travel:
 * 		1. At most m miles on a full tank and you start with a full tank. 
 * 		2. Along your way, there are gas stations 
 * What is the minimum number of refills needed?
 * -------------------------------------------------------------------------------------------
 */


public class measureTravelDistance {
	
	/*
	 * -------------------------------------------------------------------------------------------
	 * instance variables
	 * -------------------------------------------------------------------------------------------
	 */
	
	int d; 					// distance between cities
	int m; 					// maximum miles on full tank
	int n; 					// max number of stops
	int[] stopPoints;		// array of all points
	int[] stopsMade;		// array of points where stops are made
	int nCounter;			// counter for every time a stop is made
	boolean limitReached, noMoreStops;	// checker for max stops 
	int output;				// final output
	/*
	 * Constructors
	 */
	public measureTravelDistance (int d, int m, int n, int[]stopPoints) { // create your scenario
		this.d = d; 
		this.m = m; 
		this.n = n; 
		this.stopPoints = stopPoints;
		this.stopsMade = new int[n];
		this.nCounter = 0;
		limitReached = noMoreStops = false;
	}
	
	/*
	 * -------------------------------------------------------------------------------------------
	 * Getters
	 * -------------------------------------------------------------------------------------------
	 */
	
	public int getDistance() { // return distance
		return this.d;
	}
	public int getMiles() { // return miles
		return this.m;
	}
	public int outputStops() {
		return this.output;
	}
	public int getStopLimit() { // return stops
		return this.n;
	}
	public String getPoints () { // return all points
		String results = "";
		int length = stopPoints.length;			// amount of all stops	
		for (int i=0;i<stopPoints.length;i++) {	// loop to create a string list of all stops
			results += stopPoints[i];
			if (length!=1) {
				if (i<(length-2)) {
					results += ", ";
				}
				if (i==(length-2)) {
					results += " and ";
				}
			}

		}
		return results;
	}
	public String getStopsMade () {
		String results = "";
		for (int i=0;i<nCounter;i++) {	// loop to create a string list of stops made
			results += stopsMade[i];
			if (nCounter!=1) {
				if (i<(nCounter-2)) {
					results += ", ";
				}
				if (i==(nCounter-2)) {
					results += " and ";
				}
			}

		}
		return results;
	}
	
	/*
	 * -------------------------------------------------------------------------------------------
	 * Setters
	 * -------------------------------------------------------------------------------------------
	 */
	
	public void addOneStop() {
		if (nCounter < d ) { // n cannot exceed d
			nCounter = nCounter + 1;
			output = nCounter; // update output
		}
		else {
			limitReached = true; // if limitReached output changes in refills method
		}
	}
	
	public void refills() { // calculate the minimum refills	
		int distanceTraveled = 0;	// 1. track distance traveled
		int remainingDistance = d;	// 2. track remaining distance
		int closest = 0;			// 3. track index of closest (nearest) stop
		boolean pastedFirstStop = false;	// 4. check if we pasted first stop
		/*
		 * Loop Conditions
		 * 1. destination point (d) > (m) max distance we travel on full tank
		 * 2. we still have a distance remaining
		 */
		while ((d > m) && remainingDistance > 0) {
			// 5. if remaining distance > (m) max we travel on full tank
			if (remainingDistance > m) {
				int furthestWeCanTravel = distanceTraveled+m; // track furtherest point we can reach on gas left
				int distanceUntilNextStop = 0; 				  // track distance until the next stop
				if (pastedFirstStop) { 						  // check if we've passed the first stop
					if ((closest+1) == stopPoints.length) {	// if closest (last stop) is the last available refill stop
						limitReached = true;
						noMoreStops = true;
						break; // we cannot reach our destination
					}
					if (furthestWeCanTravel < stopPoints[closest+1]) {	// if next refill stop is further than the furthest point we can travel to
						limitReached = true;
						break; // we cannot reach our destination
					}
				} 
				else if (!pastedFirstStop && furthestWeCanTravel < stopPoints[closest]) { // we haven't passed the first stop & closest refill stop is further than we can travel 
					limitReached = true;
					break; // we cannot reach our destination
				}
				
				for (int i= (closest+1); i<stopPoints.length; i++) { // only search for stopPoints after the closest
					if (stopPoints[i] <= furthestWeCanTravel && stopPoints[i] > stopPoints[closest]) {
						closest = i; // if the next point is within the range of our travel and its greater than the previous point, update closest
					}
					distanceUntilNextStop = stopPoints[closest] - distanceTraveled; // track difference between out current point and the next stop
				}
				
				distanceTraveled += distanceUntilNextStop;	// travel from current distance until the next stop 
				remainingDistance -= distanceUntilNextStop;	// track the remaining distance by subtracting it by the distance we travelled
				stopsMade[nCounter] = stopPoints[closest]; // add the refill stop we made to our stopsMade array
				this.addOneStop(); // increment our nCounter (amount of stops made)
				pastedFirstStop =true; // we've passed our first stop
				if (limitReached) {	// stop at the last point if we've reached out limit
					break;
				}
			}
			// 6.	otherwise we can travel the remaining distance 
			else if (remainingDistance <= m) {
				int lastTrip = m - remainingDistance; // calculate the difference between the most we can travel and the remaining distance 
				remainingDistance -= m - lastTrip; // make remaining distance = 0, by subtracting the difference from the remaining distance
			}
		}

	}
	
	@Override
	public String toString() {
		String result = "";
		if (!limitReached) { // if we haven't reached the limit output the destination (d) point and max we travel (m)
			result += "The distance between the cities is "+d+" mile(s), the car can travel at most "+m+" mile(s) on a full tank.";
			if (nCounter==0) {	// if we haven't made any stops
				result += "\nNo refills were needed.";
			} 
			else { // if we made a stops let them know at which points we made stops
				result += "\nIt suffices to make "+nCounter+" refill(s): at point(s) "+this.getStopsMade()+".";
				if (n>1) {	// if amount of stops greater than 1, let them know why we made more than one stop
					result += "\nThis is the minimum number of refills as with a single refill one would only be able to travel at most "+m*2+" miles.\n";
				}
			}
		}
		else if (noMoreStops && limitReached) { // if we reached limit because there are no more stops
			int lastStop = nCounter-1;
			output = -1;
			result = "Failed to reach destination after making "+nCounter+" stop(s) for refills.\n"
					+ "We can only travel a maximum distance of "+m+" on a full tank.\nWe would not be able to "
					+ "reach our distination at point "+d+" even if we did a refill at point: "+this.stopsMade[lastStop]+"."
					+ "\nWe would get stuck at point " + (m + stopsMade[lastStop])+".\n";
		
		}
		else { // we reached out limit because we couldn't reach out next stop
			int lastStop;
			output = -1;
			if (nCounter == 0) // if we couldnt reach the first stop
				lastStop = 0; 
			else
				lastStop = nCounter-1; // we pasted first stop than we look at the previous stop
			
			result = "Car stopped at point "+this.stopsMade[lastStop]+ ". Failed to reach destination after only making "+nCounter+" stop(s) for refills.\n";
		}
		
		return result;
	}
	
	/*
	 * -------------------------------------------------------------------------------------------
	 * Main Method
	 * -------------------------------------------------------------------------------------------
	 */
	
	public static void main (String[] args) {
		
		// TEST CASES
		
		measureTravelDistance test1 = new measureTravelDistance(1050,400,4, new int[]{100, 400, 600, 1000});
		measureTravelDistance test2 = new measureTravelDistance(700,200,3, new int[]{100, 300, 350});
		
		test1.refills();
		test2.refills();
		
		
		System.out.println("Integer d: "+test1.getDistance()+"\nInteger m: "+test1.getMiles()+"\nInteger n: "+test1.getStopLimit()+"\nStops: "+test1.getPoints()+"\n\n"+test1);		
		System.out.println("Output: " + test1.outputStops());
		
		System.out.println("\nTest 2 for Question 1");
		System.out.println("Integer d: "+test2.getDistance()+"\nInteger m: "+test2.getMiles()+"\nInteger n: "+test2.getStopLimit()+"\nStops: "+test2.getPoints()+"\n\n"+test2);		
		System.out.println("Output: " + test2.outputStops());
		
		// USER INPUT
		
		Scanner scan = new Scanner(System.in);
		System.out.println("\nCreate your own scenario:");
		System.out.println("Constraints\n1 ≤ d ≤ 1100, 1 ≤ m ≤ 415, 1 ≤ n ≤ 300, 0 < stop1 < stop2 < ... < stop n < d");
		System.out.println("Enter Integer d: ");
		int inputD = scan.nextInt();
		System.out.println("Enter Integer m: ");
		int inputM = scan.nextInt();
		System.out.println("Enter Integer n: ");
		int inputN = scan.nextInt();
		System.out.println("Enter Stops: ");
		int inputS[] = new int[inputN];
		for (int i=0; i<inputN; i++) {
			inputS[i] = scan.nextInt();
		}
		
		measureTravelDistance user1 = new measureTravelDistance(inputD, inputM, inputN, inputS);
		user1.refills();
		System.out.println("Integer d: "+user1.getDistance()+"\nInteger m: "+user1.getMiles()+"\nInteger n: "+user1.getStopLimit()+"\nStops: "+user1.getPoints()+"\n\n"+user1);		
		System.out.println("Output: " + user1.outputStops());
		
	}
	
	// TEST CASES
//
//	Test 1 for Question 1
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
//	Test 2 for Question 1
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
	

}
