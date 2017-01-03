package Main;
/**
 * Class that creates an object containing information for the objectlist.
 * 
 * @author Emil Karlsson.
 *
 */
public class ComboBoxObject {

	private String name;
	private int id;
	/**
	 * Constructor.
	 * 
	 * @param name
	 * @param id
	 */
	public ComboBoxObject(String name, int id){
		this.name = name;
		this.id = id;
	}
	public String getName(){
		return this.name;
	}
	public int getId(){
		return this.id;
	}
}
