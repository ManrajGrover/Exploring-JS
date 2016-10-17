import java.util.concurrent.BlockingQueue;

public class SendData implements Runnable{
	
	private BlockingQueue<Data> queue;
	/**
	 * Constructor for SendData
	 * @param queue BlockingQueue to be used between two threads
	 */
	public SendData(BlockingQueue<Data> queue) {
		this.queue = queue;
	}
	
	/**
	 * Runnable interface method implemented
	 * It loops through integers and adds them to the queue
	 */
	@Override
	public void run() {
		for (int i = 0;i < 10; i++) {
			Data data = new Data(i);
			
			try {
				Thread.sleep(5);
				queue.put(data);
				System.out.println("Queued " + Integer.toString(i));
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * Add last element indicating end of integers
		 */
		Data data = new Data(-1);

		try {
			queue.put(data);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
