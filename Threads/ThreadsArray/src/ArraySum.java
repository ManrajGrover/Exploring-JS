import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class ArraySum {
	/**
	 * Array of integers of long type
	 */
	private static long[] arr;
	
	/**
	 * Semaphore for managing permits to the data shared between threads
	 */
	private Semaphore done;
	
	/**
	 * sumParallel sums the array parallelly using threads
	 * It prints the sum as well as time taken to calculate it
	 * 
	 * @param lenForOne Length of one part of array to be summed per thread
	 * @param numberOfThreads Number of threads to be used
	 */
	public void sumParallel(int lenForOne,int numberOfThreads) {
		/**
		 * Initialize the Semaphore with 0 meaning no threads are waiting
		 */
		done = new Semaphore(0);
		/**
		 * Initialize Start time of the code
		 */
		long startTime = 0;
		/**
		 * Initialize array sum
		 */
		long arraySum = 0;
		
		/**
		 * Create list of threads to be used for summing the array
		 */
		List<Threads> threads = new ArrayList<Threads>();
		
		/**
		 * Allocate each thread part of array which it will sum
		 * Last threads takes up everything left in the array
		 */
		for(int i = 0; i < numberOfThreads; i++) {
			int s = i*lenForOne, e = (i+1)*lenForOne;
			
			if (i == numberOfThreads - 1) {
				e = arr.length;
			}
			
			startTime = System.currentTimeMillis();
			Threads thread = new Threads(s, e, done, arr);
			threads.add(thread);
			thread.start();
			
		}
		
		/**
		 * Acquire all permits from the Semaphore
		 */
		try {
			done.acquire(numberOfThreads);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		
		/**
		 * Find array sum from individual sums of sub-arrays calculated by threads
		 */
		for (Threads thread: threads) {
			arraySum = arraySum +  thread.getSumOfSubArray();
		}
		/**
		 * Initialize end time and calculate total time taken
		 */
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		
		System.out.println("Time taken by threads is " + Long.toString(totalTime) + " milliseconds.");
		System.out.println("Sum using threads is "+Long.toString(arraySum));
	}
	
	/**
	 * sumIterative sums the array iteratively and prints the total as well as time
	 * taken to complete the sum
	 * 
	 * @param lenOfArray Length of array to be summed
	 */
	public void sumIterative(int lenOfArray) {
		long iterSum = 0;
		long startTime = System.currentTimeMillis();
		
		/**
		 * Calculate sum of array iteratively
		 */
		for (int i = 0;i < lenOfArray; i++) {
			iterSum = iterSum + arr[i];
		}
		
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		
		System.out.println("Time taken by direct for loop is " + Long.toString(totalTime) + " milliseconds.");
		System.out.println("Sum using iterative method is "+Long.toString(iterSum));
	}
	
	public static void main(String args[]) {
		/**
		 * Initialize Length of Array
		 */
		int lenOfArray = 100000000;
		/**
		 * Number of threads to be used
		 */
		int threads = 15;
		arr = new long[lenOfArray];
		
		/**
		 * Fill array with integers
		 */
		for (int i = 0;i < lenOfArray; i++) {
			arr[i] = (long) i;
		}
		/**
		 * Calculate length of subarray to be used
		 */
		int lenForOneThread = lenOfArray/threads;
		
		ArraySum as = new ArraySum();
		/**
		 * Call parallel array sum
		 */
		as.sumParallel(lenForOneThread, threads);
		/**
		 * Call iterative array sum
		 */
		as.sumIterative(lenOfArray);
	}
}
