package Main;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
/**
 * Class that creates the add window.
 * 
 * @author Emil Karlsson
 *
 */
public class AddWindow extends JFrame {

	private JPanel contentPane;
	private ObjectBox comboBoxStores;
	private ObjectBox comboBoxBooks;
	private ObjectBox comboBoxAuthor;
	private FocusedTextField textFieldStoreName;
	private FocusedTextField textFieldStoreAdress;
	private FocusedTextField textFieldBookName;
	private FocusedTextField textFieldAuthorName;
	private FocusedTextField textFieldAuthorAge;
	private FocusedTextField stockField;
	private FocusedTextField priceField;
	private RolloverButton newStore;
	private RolloverButton newBook;
	private RolloverButton newAuthor;
	private RolloverButton submit;

	
	/**
	 * Create the frame.
	 */
	public AddWindow() {
		super("Add Books");
		setBackground(Color.WHITE);
		setBounds(100, 100, 1177, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		addButtons();
		addTextFields();
	}
	private void addTextFields(){
		stockField = new FocusedTextField("Stock");
		stockField.setBounds(948, 13, 93, 22);
		contentPane.add(stockField);
		stockField.setColumns(10);
		
		priceField = new FocusedTextField("Price");
		priceField.setBounds(1053, 13, 93, 22);
		contentPane.add(priceField);
		priceField.setColumns(10);
		
		textFieldStoreName = new FocusedTextField("Store name");
		textFieldStoreName.setBounds(12, 13, 146, 22);
		contentPane.add(textFieldStoreName);
		textFieldStoreName.setColumns(10);
		
		textFieldStoreAdress = new FocusedTextField("Adress");
		textFieldStoreAdress.setBounds(168, 13, 146, 22);
		contentPane.add(textFieldStoreAdress);
		textFieldStoreAdress.setColumns(10);
		
		textFieldBookName = new FocusedTextField("Book name");
		textFieldBookName.setBounds(324, 13, 146, 22);
		contentPane.add(textFieldBookName);
		textFieldBookName.setColumns(10);
		
		textFieldAuthorName = new FocusedTextField("Author name");
		textFieldAuthorName.setBounds(635, 13, 146, 22);
		contentPane.add(textFieldAuthorName);
		textFieldAuthorName.setColumns(10);
		
		textFieldAuthorAge = new FocusedTextField("Author age");
		textFieldAuthorAge.setBounds(790, 13, 146, 22);
		contentPane.add(textFieldAuthorAge);
		textFieldAuthorAge.setColumns(10);
	}
	private void addButtons(){
		newStore = new RolloverButton("New Store", Color.darkGray, Color.lightGray, Color.white);
		newStore.setBounds(12, 48, 120, 22);
		newStore.setFont(new Font("Candara", Font.BOLD, 13));
		contentPane.add(newStore);

		newBook = new RolloverButton("New Book", Color.darkGray, Color.lightGray, Color.white);
		newBook.setBounds(144, 48, 120, 22);
		newBook.setFont(new Font("Candara", Font.BOLD, 13));
		contentPane.add(newBook);

		newAuthor = new RolloverButton("New Author", Color.darkGray, Color.lightGray, Color.white);
		newAuthor.setBounds(276, 48, 120, 22);
		newAuthor.setFont(new Font("Candara", Font.BOLD, 13));
		contentPane.add(newAuthor);
		newAuthor.setEnabled(false);
		
		submit = new RolloverButton("Submit", Color.darkGray, Color.lightGray, Color.white);
		submit.setBounds(408, 48, 120, 22);
		submit.setFont(new Font("Candara", Font.BOLD, 13));
		contentPane.add(submit);
	}
	/**
	 * Method that creates the drop down menu for store.
	 * @param menuOptions
	 */
	public void addDropDownMenuStores(ComboBoxObject[] menuOptions) {
		int arrayLength = menuOptions.length;
		String[] menuOptionsString = new String[arrayLength];
		int[] id = new int[arrayLength];
		for(int i = 0; i < menuOptions.length; i++){
			menuOptionsString[i] = menuOptions[i].getName();
			id[i] = menuOptions[i].getId();
		}
		comboBoxStores = new ObjectBox(menuOptionsString, id);
		comboBoxStores.setBounds(12, 13, 300, 22);
		contentPane.add(comboBoxStores);
	}
	/**
	 * Method that creates the drop down menu for books.
	 * @param menuOptions
	 */
	public void addDropDownMenuBooks(ComboBoxObject[] menuOptions) {
		int arrayLength = menuOptions.length;
		String[] menuOptionsString = new String[arrayLength];
		int[] id = new int[arrayLength];
		for(int i = 0; i < menuOptions.length; i++){
			menuOptionsString[i] = menuOptions[i].getName();
			id[i] = menuOptions[i].getId();
		}
		comboBoxBooks = new ObjectBox(menuOptionsString, id);
		comboBoxBooks.setBounds(324, 13, 300, 22);
		contentPane.add(comboBoxBooks);
	}
	/**
	 * Method that creates the drop down menu for author.
	 * @param menuOptions
	 */
	public void addDropDownMenuAuthor(ComboBoxObject[] menuOptions) {
		int arrayLength = menuOptions.length;
		String[] menuOptionsString = new String[arrayLength];
		int[] id = new int[arrayLength];
		for(int i = 0; i < menuOptions.length; i++){
			menuOptionsString[i] = menuOptions[i].getName();
			id[i] = menuOptions[i].getId();
		}
		comboBoxAuthor = new ObjectBox(menuOptionsString, id);
		comboBoxAuthor.setBounds(636, 13, 300, 22);
		contentPane.add(comboBoxAuthor);
	}
	/**
	 * Method that locks a combobox.
	 * @param comboBox
	 */
	public void comboBoxLock(ObjectBox comboBox){
		comboBox.setEnabled(false);
	}
	/**
	 * Method that Unlocks a combobox.
	 * @param comboBox
	 */
	public void comboBoxUnlock(ObjectBox comboBox){
		comboBox.setEnabled(true);
	}
	/**
	 * Method that disables and hides a combobox.
	 * @param comboBox
	 */
	public void comboBoxDisable(ObjectBox comboBox){
		comboBox.setVisible(false);
		comboBox.setEnabled(false);
	}
	/**
	 * Method that enables and shows a cmbobox.
	 * @param comboBox
	 */
	public void comboBoxEnable(ObjectBox comboBox){
		comboBox.setVisible(true);
		comboBox.setEnabled(true);
	}
	/**
	 * Method that disables a textfield.
	 * 
	 * @param text
	 */
	public void textFieldDisable(FocusedTextField text){
		text.setVisible(false);
		text.setEnabled(false);
		text.setEditable(false);
	}
	/**
	 * Method that enables and shows a textfield.
	 * @param text
	 */
	public void textFieldEnable(FocusedTextField text){
		text.setVisible(true);
		text.setEnabled(true);
		text.setEditable(true);
	}
	/**
	 * Method that disables a button.
	 * 
	 * @param rb
	 */
	public void disableButton(RolloverButton rb){
		rb.setEnabled(false);
	}
	/**
	 * Method that enables a button.
	 * 
	 * @param rb
	 */
	public void enableButton(RolloverButton rb){
		rb.setEnabled(true);
	}
	
	public ObjectBox getComboBoxStores(){
		return this.comboBoxStores;
	}
	public ObjectBox getComboBoxBooks(){
		return this.comboBoxBooks;
	}
	public ObjectBox getComboBoxAuthor(){
		return this.comboBoxAuthor;
	}
	public RolloverButton getBtnNewStore(){
		return this.newStore;
	}
	public RolloverButton getBtnNewBook(){
		return this.newBook;
	}
	public RolloverButton getBtnNewAuthor(){
		return this.newAuthor;
	}
	public RolloverButton getBtnSubmit(){
		return this.submit;
	}
	public FocusedTextField getTxfStoreName(){
		return this.textFieldStoreName;
	}
	public FocusedTextField getTxfStoreAdress(){
		return this.textFieldStoreAdress;
	}
	public FocusedTextField getTxfBookName(){
		return this.textFieldBookName;
	}
	public FocusedTextField getTxfAuthorName(){
		return this.textFieldAuthorName;
	}
	public FocusedTextField getTxfAuthorAge(){
		return this.textFieldAuthorAge;
	}
	public FocusedTextField getTxfStock(){
		return this.stockField;
	}
	public FocusedTextField getTxfPrice(){
		return this.priceField;
	}
}
