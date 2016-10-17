
public class Data {
	private int data;
	/**
	 * Constructor for Data
	 * @param data Integer to be shared between threads
	 */
	public Data(int data) {
		this.data = data;
	}
	
	public int getData() {
		return data;
	}
	
	public void setData(int data) {
		this.data = data;
	}
}
