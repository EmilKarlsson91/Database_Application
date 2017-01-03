package Main;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
/**
 * Class that creates the admin window.
 * 
 * @author Emil Karlsson
 *
 */
public class AdminWindow extends JFrame {

	private RolloverButton btnRemoveStore;
	private RolloverButton btnUpdateBook;
	private RolloverButton btnAdd;
	private RolloverButton btnRefresh;
	private RolloverButton btnUpdateStore;
	private RolloverButton btnUpdateAuthor;
	private	RolloverButton btnRemoveBook;
	private RolloverButton btnRemoveAuthor;
	private JScrollPane tableScroll;
	private JPanel contentPane;
	private FocusedTextField txtSearch;
	private JTable table;
	private DefaultTableModel tableModel;

	/**
	 * Create the frame.
	 */
	public AdminWindow() {
		super("Admin control window");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 650);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		addSearchBox();
		addButtons();

	}
	/**
	 * Method that creates the table inside a scrollpane on window.
	 * 
	 * @param columnNames
	 */
	public void addScrollTable(String columnNames[]){
		tableModel = new DefaultTableModel(columnNames, 0);
		table = new JTable(tableModel);
		table.setFillsViewportHeight(true);
		tableScroll = new JScrollPane(table);
		tableScroll.setBounds(8, 180, 1177, 427);
		contentPane.add(tableScroll);
	}
	private void addSearchBox(){
		txtSearch = new FocusedTextField("Search");
		txtSearch.setBounds(540, 13, 120, 25);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);
	}
	private void addButtons(){
		
		btnRemoveStore = new RolloverButton("Remove store", Color.darkGray, Color.lightGray, Color.white);
		btnRemoveStore.setFont(new Font("Consolas", Font.BOLD, 13));
		btnRemoveStore.setBounds(144, 13, 120, 25);
		contentPane.add(btnRemoveStore);
		
		btnAdd = new RolloverButton("Add", Color.darkGray, Color.lightGray, Color.white);
		btnAdd.setFont(new Font("Consolas", Font.BOLD, 13));
		btnAdd.setBounds(12, 13, 120, 25);
		contentPane.add(btnAdd);
		
		btnUpdateStore = new RolloverButton("Update Store", Color.darkGray, Color.lightGray, Color.white);
		btnUpdateStore.setFont(new Font("Consolas", Font.BOLD, 13));
		btnUpdateStore.setBounds(12, 51, 120, 25);
		contentPane.add(btnUpdateStore);
		
		btnUpdateBook = new RolloverButton("Update book", Color.darkGray, Color.lightGray, Color.white);
		btnUpdateBook.setFont(new Font("Consolas", Font.BOLD, 13));
		btnUpdateBook.setBounds(144, 51, 120, 25);
		contentPane.add(btnUpdateBook);
		
		btnUpdateAuthor = new RolloverButton("Update author", Color.darkGray, Color.lightGray, Color.white);
		btnUpdateAuthor.setFont(new Font("Consolas", Font.BOLD, 13));
		btnUpdateAuthor.setBounds(276, 51, 125, 25);
		contentPane.add(btnUpdateAuthor);
		
		btnRefresh = new RolloverButton("Refresh", Color.darkGray, Color.lightGray, Color.white);
		btnRefresh.setFont(new Font("Candara", Font.BOLD, 13));
		btnRefresh.setBounds(408, 51, 120, 25);
		contentPane.add(btnRefresh);
		
		btnRemoveBook = new RolloverButton("Remove book", Color.darkGray, Color.lightGray, Color.white);
		btnRemoveBook.setBackground(Color.GRAY);
		btnRemoveBook.setFont(new Font("Candara", Font.BOLD, 13));
		btnRemoveBook.setBounds(276, 13, 120, 25);
		contentPane.add(btnRemoveBook);
		
		btnRemoveAuthor = new RolloverButton("Remove author", Color.darkGray, Color.lightGray, Color.white);
		btnRemoveAuthor.setBackground(Color.GRAY);
		btnRemoveAuthor.setFont(new Font("Candara", Font.BOLD, 13));
		btnRemoveAuthor.setBounds(408, 13, 125, 25);
		contentPane.add(btnRemoveAuthor);
	}
	/**
	 * Method that prints data from the database on the table.
	 * @param rowEntities
	 */
	public void insertRow(String[] rowEntities){
		tableModel.insertRow(0, rowEntities);
	}
	public JTable getTable(){
		return this.table;
	}
	public RolloverButton getRefresh(){
		return this.btnRefresh;
	}
	public RolloverButton getBtnAdd(){
		return this.btnAdd;
	}
	public RolloverButton getBtnRemoveStore(){
		return this.btnRemoveStore;
	}
	public RolloverButton getBtnUpdateStore(){
		return this.btnUpdateStore;
	}
	public RolloverButton getBtnUpdateBook(){
		return this.btnUpdateBook;
	}
	public RolloverButton getBtnUpdateAuthor(){
		return this.btnUpdateAuthor;
	}
	public RolloverButton getBtnRemoveBook(){
		return this.btnRemoveBook;
	}
	public RolloverButton getBtnRemoveAuthor(){
		return this.btnRemoveAuthor;
	}
	public FocusedTextField getTxfSearch(){
		return this.txtSearch;
	}
}
