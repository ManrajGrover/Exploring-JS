import java.util.concurrent.Semaphore;

public class Threads extends Thread{
	private int start, end;
	private long sum;
	
	private static long[] array;
	
	private Semaphore done;
	
	/**
	 * Constructor for Thread
	 * @param s Start index of subarray
	 * @param e End index of subarray
	 * @param d Semaphore reference
	 * @param arr Subarray whose sum needs to be calculated
	 */
	public Threads(int s, int e, Semaphore d, long[] arr) {
		start = s;
		end = e;
		sum = 0;
		done = d;
		array = arr;
	}
	
	/**
	 * Calculate sum of subarray and release the permit
	 */
	public void run() {
		for(int i = start;i < end;i++) {
			sum = sum + array[i];
		}
		done.release();
	}
	
	/**
	 * Get sum of individual threads
	 * @return Sum of Subarray
	 */
	public long getSumOfSubArray() {
		return sum;
	}
}
