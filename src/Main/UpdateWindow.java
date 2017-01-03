package Main;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Class that creates the update window.
 * 
 * @author Emil Karlsson
 *
 */
public class UpdateWindow extends JFrame {

	private JPanel contentPane;
	private ObjectBox comboBox;
	private RolloverButton update;
	private FocusedTextField textField1;
	private FocusedTextField textField2;

	/**
	 * Create the frame.
	 */
	public UpdateWindow() {
		super("Update");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();	
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		addButton();
	}
	/**
	 * Method that adds the button to the frame.
	 */
	private void addButton(){
		update = new RolloverButton("Update", Color.darkGray, Color.lightGray, Color.white);
		update.setBounds(324, 13, 120, 22);
		update.setFont(new Font("Candara", Font.BOLD, 13));
		contentPane.add(update);
	}
	/**
	 * Method that adds a drop down menu.
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
	/**
	 * Method that adds the textfields for the store.
	 */
	public void addTextFieldStore(){
		
		textField1 = new FocusedTextField("Store name");
		textField1.setBounds(12, 51, 146, 22);
		contentPane.add(textField1);
		textField1.setColumns(10);
		
		textField2 = new FocusedTextField("Adress");
		textField2.setBounds(168, 51, 146, 22);
		contentPane.add(textField2);
		textField2.setColumns(10);
	}
	/**
	 * Method that adds the textfield for the book.
	 */
	public void addTextFieldBook(){
		
		textField1 = new FocusedTextField("Book name");
		textField1.setBounds(12, 51, 146, 22);
		contentPane.add(textField1);
		textField1.setColumns(10);
	}
	/**
	 * Method that adds the textfields for the author.
	 */
	public void addTextFieldAuthor(){
		
		textField1 = new FocusedTextField("Author name");
		textField1.setBounds(12, 51, 146, 22);
		contentPane.add(textField1);
		textField1.setColumns(10);
		
		textField2 = new FocusedTextField("Author age");
		textField2.setBounds(168, 51, 146, 22);
		contentPane.add(textField2);
		textField2.setColumns(10);
	}
	public ObjectBox getComboBox(){
		return this.comboBox;
	}
	public RolloverButton getBtn(){
		return this.update;
	}
	public FocusedTextField getTxf1(){
		return this.textField1;
	}
	public FocusedTextField getTxf2(){
		return this.textField2;
	}
}
