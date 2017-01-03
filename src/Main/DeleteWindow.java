package Main;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
/**
 * Class that creates the delete window.
 * 
 * @author Emil Karlsson
 *
 */
public class DeleteWindow extends JFrame {

	private JPanel contentPane;
	private ObjectBox comboBox;
	private RolloverButton delete;

	/**
	 * Create the frame.
	 */
	public DeleteWindow() {
		super("Delete");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();	
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		addButton();

	}
	private void addButton(){
		delete = new RolloverButton("Delete", Color.darkGray, Color.lightGray, Color.white);
		delete.setBounds(324, 13, 120, 22);
		delete.setFont(new Font("Candara", Font.BOLD, 13));
		contentPane.add(delete);
	}
	/**
	 * Method that creates the drop down menu.
	 * 
	 * @param menuOptions
	 */
	public void addDropDownMenu(ComboBoxObject[] menuOptions) {
		int arrayLength = menuOptions.length;
		String[] menuOptionsString = new String[arrayLength];
		int[] id = new int[arrayLength];
		for(int i = 0; i < menuOptions.length; i++){
			menuOptionsString[i] = menuOptions[i].getName();
			id[i] = menuOptions[i].getId();
		}
		comboBox = new ObjectBox(menuOptionsString, id);
		comboBox.setBounds(12, 13, 300, 22);
		contentPane.add(comboBox);
		for (int i = 0; i < menuOptions.length; i++) {
			System.out.println(menuOptions[i]);

		}
	}
	public ObjectBox getComboBox(){
		return this.comboBox;
	}
	public RolloverButton getBtn(){
		return this.delete;
	}
}
