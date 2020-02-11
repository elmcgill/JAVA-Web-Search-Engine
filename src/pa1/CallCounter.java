package pa1;

/**
 * This class simply keeps track of how many times jsoup.connect() is called
 * 
 * @author Jeremy Galang and Ethan McGill
 *
 */
public class CallCounter {

	private static CallCounter instance = new CallCounter();
	private int counter = 0;

	private CallCounter() {

	}

	/**
	 * @return a CallCounter object instance
	 */
	public static CallCounter getInstance() {
		return instance;
	}

	/**
	 * @return a count of how many times a link has been called
	 */
	public int getCount() {
		return counter;
	}

	/**
	 * Updates a counter when a call has been made
	 */
	public void madeCall() {

		counter++;
	}

}
