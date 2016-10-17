import java.util.concurrent.BlockingQueue;

public class CubeData implements Runnable{
	
	private BlockingQueue<Data> queue;
	/**
	 * Constructor for CubeData
	 * @param queue BlockingQueue being shared between two threads
	 */
	public CubeData(BlockingQueue<Data> queue) {
		this.queue = queue;
	}
	
	/**
	 * Runnable interface method implemented
	 * It checks the queue for data, pops it and prints its cube
	 */
	@Override
	public void run() {
		try {
			Data data;
			/**
			 * Check if data is not -1 (indicating last element)
			 */
			while((data = queue.take()).getData() != -1) {
				int d = data.getData();
				Thread.sleep(10);
				System.out.println("Cube of "+Integer.toString(d)+" is "+ d*d*d);
				
			}
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

}
