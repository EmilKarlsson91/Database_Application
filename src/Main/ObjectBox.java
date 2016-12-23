package Main;

import javax.swing.JComboBox;

public class ObjectBox extends JComboBox{

	private int[] id;
	
	public ObjectBox(String[] menuOptionsString, int[] id){
		super(menuOptionsString);
		this.id = id;
	}
	public int getId(){
		return id[this.getSelectedIndex()];
	}
	public int getIdAt(int i){
		return id[i];
	}
}
