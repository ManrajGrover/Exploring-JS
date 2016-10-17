import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ThreadCommunication {
	
	public static void main(String args[]) {
		/**
		 * Initialize Blocking Queue of Array type and size 5
		 */
		BlockingQueue<Data> queue = new ArrayBlockingQueue<Data>(5);
		/**
		 * Initialize instances of thread with queue to be used
		 */
		SendData sd = new SendData(queue);
		CubeData cd = new CubeData(queue);
		
		/**
		 * Start the threads
		 */
		new Thread(sd).start();
		new Thread(cd).start();
		System.out.println("Communication started!");
	}
	
}
