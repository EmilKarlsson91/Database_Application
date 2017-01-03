package Main;

import javax.swing.JComboBox;
/**
 * Class that extends the JComboBox, creates a list that have names and also saves index.
 * @author emkar
 *
 */
public class ObjectBox extends JComboBox{

	private int[] id;
	/**
	 * Constructor.
	 * 
	 * @param menuOptionsString
	 * @param id
	 */
	public ObjectBox(String[] menuOptionsString, int[] id){
		super(menuOptionsString);
		this.id = id;
	}
	/**
	 * Method that returns the selected combobox index.
	 * 
	 * @return id
	 */
	public int getId(){
		return id[this.getSelectedIndex()];
	}
	/**
	 * Method that returns the id at a specific index.
	 * 
	 * @param i
	 * @return id
	 */
	public int getIdAt(int i){
		return id[i];
	}
}
