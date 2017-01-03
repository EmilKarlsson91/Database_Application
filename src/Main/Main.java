package Main;

/**
 * Main class containing the main method.
 * 
 * @author Emil Karlsson.
 *
 */
public class Main {

	/**
	 * Main method, the program starts here.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			Controller.controllerManager();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
