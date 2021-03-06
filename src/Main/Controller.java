package Main;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JOptionPane;

/**
 * 
 * @author Emil Karlsson
 *
 */
public class Controller {

	private static DAOManager manager;
	private static StoreDAO store;
	private static BookDAO book;
	private static AuthorDAO author;
	private static HaveDAO have;
	private static AllDataDAO allData;
	private static AdminWindow admWindow;
	private static AddWindow addWindow;
	private static UpdateWindow updateWindow;
	private static DeleteWindow deleteWindow;

	/**
	 * Method that initializes the controller.
	 * 
	 * @throws Exception
	 */
	public static void controllerManager() throws Exception {

		initializeGui();
		initialize();
		refresh();
		actionListenersAdmin();
	}

	private static void refresh() throws SQLException {
		ResultSet rs = allData.getAllData();
		printTable(rs);
		if(rs.next()){
			addRow(rs);	
		}
	}

	private static void initialize() throws Exception {
		try {
			manager = new DAOManager("root", "test");
			manager.open();
			store = new StoreDAO(manager.getConnection());
			book = new BookDAO(manager.getConnection());
			author = new AuthorDAO(manager.getConnection());
			have = new HaveDAO(manager.getConnection());
			allData = new AllDataDAO(manager.getConnection());
		} catch (SQLException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
	}

	private static void initializeGui() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					admWindow = new AdminWindow();
					admWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static void search() throws SQLException {
		ResultSet rs = allData.search(admWindow.getTxfSearch().getText());
		printTable(rs);
		if(rs.next()){
			addRow(rs);			
		}
	}
	private static void update(ResultSet rs, int index) throws SQLException{
		ObjectBox objectBox = updateWindow.getComboBox();
		int selectedIndex = objectBox.getId();
		rs.beforeFirst();
		while (rs.next()) {
			if (index == 0) {
				store.updateRow(updateWindow.getTxf1().getText(), updateWindow.getTxf2().getText(), selectedIndex);
				return;
			} else if (index == 1) {
				book.updateRow(updateWindow.getTxf1().getText(), selectedIndex);
				return;
			} else if (index == 2) {
				int age;
				try{
					age = Integer.parseInt(updateWindow.getTxf2().getText());
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(deleteWindow, "Enter author age in a numeric value..");
					return;
				}
				author.updateRow(updateWindow.getTxf1().getText(), age, selectedIndex);
				return;
			}
		}
		JOptionPane.showMessageDialog(deleteWindow, "Action successful!");
	}


	private static void delete(ResultSet rs, int index) throws SQLException {
		ObjectBox objectBox = deleteWindow.getComboBox();
		int selectedIndex = objectBox.getId();
		rs.beforeFirst();
		while (rs.next()) {
			if (index == 0) {
				store.deleteRow(selectedIndex);
				have.deleteRowStore(selectedIndex);
				return;
			} else if (index == 1) {
				book.deleteRow(selectedIndex);
				have.deleteRowBook(selectedIndex);
				return;
			} else if (index == 2) {
				author.deleteRow(selectedIndex);
				return;
			}
		}
		JOptionPane.showMessageDialog(deleteWindow, "Action successful!");
	}

	@SuppressWarnings("resource")
	private static void submitAdd() throws SQLException {
		ResultSet rsId;
		ResultSet rsHave;
		ObjectBox objectBox;
		int storeId = 0;
		int bookId = 0;
		int authorId = 0;
		int stock = 0;
		int price = 0;

		if (addWindow.getBtnNewStore().getPressed()) {
			store.insertInto(addWindow.getTxfStoreName().getText(), addWindow.getTxfStoreAdress().getText());
		} else {
			objectBox = addWindow.getComboBoxStores();
			storeId = objectBox.getId();
		}
		if (!addWindow.getBtnNewBook().getPressed()) {
			int temp;
			rsId = book.getTableDesc();
			rsId.beforeFirst();
			objectBox = addWindow.getComboBoxBooks();
			temp = objectBox.getId();
			while (rsId.next()) {
				if (temp == rsId.getInt(1)) {
					temp = rsId.getInt(3);
					rsId = author.getTableDesc();
					rsId.beforeFirst();
					while (rsId.next()) {
						if (rsId.getInt(1) == temp) {
							authorId = rsId.getInt(1);
						}
					}
				}
			}
			objectBox = addWindow.getComboBoxAuthor();
			for (int i = 0; i < objectBox.getItemCount(); i++) {
				if (authorId == objectBox.getIdAt(i)) {
					authorId = i;
				}
			}
			addWindow.getComboBoxAuthor().setSelectedIndex(authorId);
		} else if (addWindow.getBtnNewAuthor().getPressed()) {
			try{
				author.insertInto(addWindow.getTxfAuthorName().getText(), addWindow.getTxfAuthorAge().getText());				
			}
			catch(Exception e){
				JOptionPane.showMessageDialog(deleteWindow, "Enter author age in a numeric value..");
				return;
			}
		} else {
			objectBox = addWindow.getComboBoxAuthor();
			authorId = objectBox.getId();
		}
		if (addWindow.getBtnNewBook().getPressed() && addWindow.getBtnNewAuthor().getPressed()) {
			rsId = allData.getStoreAndAuthorId();
			rsId.last();
			book.insertInto(addWindow.getTxfBookName().getText(), rsId.getInt(2));				
		}
		else if(addWindow.getBtnNewBook().getPressed()){
			book.insertInto(addWindow.getTxfBookName().getText(), authorId);
		}
		else {
			objectBox = addWindow.getComboBoxBooks();
			bookId = objectBox.getId();
		}

		if (addWindow.getBtnNewStore().getPressed() || addWindow.getBtnNewBook().getPressed()) {
			rsId = allData.getAllId();
			rsId.last();
			if(addWindow.getBtnNewStore().getPressed() && addWindow.getBtnNewBook().getPressed()){
				storeId = rsId.getInt(1);
				bookId = rsId.getInt(2);		
			}
			else if(addWindow.getBtnNewStore().getPressed()){
				storeId = rsId.getInt(1);
			}
			else{
				bookId = rsId.getInt(2);		

			}

		}

		try {
			stock = Integer.parseInt(addWindow.getTxfStock().getText());
			price = Integer.parseInt(addWindow.getTxfPrice().getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(deleteWindow, "You have to enter a value in every field");
			return;
		}
		rsHave = have.getTableDesc();
		rsHave.beforeFirst();
		while (rsHave.next()) {
			if (rsHave.getInt(1) == storeId && rsHave.getInt(2) == bookId) {
				JOptionPane.showMessageDialog(addWindow, "The store already contain this book");
				return;
			}
		}
		if (storeId == 0 || bookId == 0 || stock == 0 || price == 0) {
			JOptionPane.showMessageDialog(addWindow, "You have to enter a value in every field");
			return;
		}
		have.insertInto(storeId, bookId, stock, price);
		JOptionPane.showMessageDialog(addWindow, "Action successful!");
	}

	private static void add() throws SQLException {

		if (addWindow.getBtnNewStore().getPressed()) {
			addWindow.comboBoxDisable(addWindow.getComboBoxStores());
			addWindow.textFieldEnable(addWindow.getTxfStoreName());
			addWindow.textFieldEnable(addWindow.getTxfStoreAdress());
		} else {
			addWindow.getComboBoxStores();
			addWindow.comboBoxEnable(addWindow.getComboBoxStores());
			addWindow.textFieldDisable(addWindow.getTxfStoreName());
			addWindow.textFieldDisable(addWindow.getTxfStoreAdress());
		}
		if (addWindow.getBtnNewBook().getPressed()) {
			addWindow.comboBoxDisable(addWindow.getComboBoxBooks());
			addWindow.textFieldEnable(addWindow.getTxfBookName());
			addWindow.comboBoxUnlock(addWindow.getComboBoxAuthor());
		} else {
			addWindow.comboBoxEnable(addWindow.getComboBoxBooks());
			addWindow.textFieldDisable(addWindow.getTxfBookName());
			addWindow.comboBoxLock(addWindow.getComboBoxAuthor());
		}
		if (addWindow.getBtnNewAuthor().getPressed() && addWindow.getBtnNewBook().getPressed()) {
			addWindow.comboBoxDisable(addWindow.getComboBoxAuthor());
			addWindow.textFieldEnable(addWindow.getTxfAuthorName());
			addWindow.textFieldEnable(addWindow.getTxfAuthorAge());
		}
		else if(!addWindow.getBtnNewBook().getPressed()){
			addWindow.comboBoxLock(addWindow.getComboBoxAuthor());			
		}
		else{
			addWindow.comboBoxEnable(addWindow.getComboBoxAuthor());
			addWindow.textFieldDisable(addWindow.getTxfAuthorName());
			addWindow.textFieldDisable(addWindow.getTxfAuthorAge());
		}
	}
	
	private static void initializeUpdateWindow(int index) throws SQLException {
		ResultSet rs = null;
		String select = "";
		updateWindow = new UpdateWindow();
		if (index == 0) {
			rs = store.getTableAsce();
			updateWindow.addTextFieldStore();
			select = "Selet store: ";
		} else if (index == 1) {
			rs = book.getTableAsce();
			updateWindow.addTextFieldBook();
			select = "Select book: ";
		} else if (index == 2) {
			rs = author.getTableAsce();
			updateWindow.addTextFieldAuthor();
			select = "Select author: ";
		}
		updateWindow.addDropDownMenu(dropDownMenuObject(rs, select));
		updateWindow.setVisible(true);
		actionListenersUpdateWindow(rs, index);
	}

	private static void initializeDeleteWindow(int index) throws SQLException {
		ResultSet rs = null;
		String select = "";
		if (index == 0) {
			rs = store.getTableAsce();
			select = "Selet store: ";
		} else if (index == 1) {
			rs = book.getTableAsce();
			select = "Select book: ";
		} else if (index == 2) {
			rs = author.getTableAsce();
			select = "Select author: ";
		}
		deleteWindow = new DeleteWindow();
		deleteWindow.addDropDownMenu(dropDownMenuObject(rs, select));
		deleteWindow.setVisible(true);
		actionListenersDeleteWindow(rs, index);

	}

	private static void initializeAddBookWindow() throws SQLException {
		ResultSet rs = store.getTableAsce();
		ResultSet rs2 = book.getTableAsce();
		ResultSet rs3 = author.getTableAsce();
		addWindow = new AddWindow();
		addWindow.addDropDownMenuStores(dropDownMenuObject(rs, "Select store:"));
		addWindow.addDropDownMenuBooks(dropDownMenuObject(rs2, "Select book:"));
		addWindow.addDropDownMenuAuthor(dropDownMenuObject(rs3, "Select author:"));
		addWindow.textFieldDisable(addWindow.getTxfStoreName());
		addWindow.textFieldDisable(addWindow.getTxfStoreAdress());
		addWindow.textFieldDisable(addWindow.getTxfBookName());
		addWindow.textFieldDisable(addWindow.getTxfAuthorName());
		addWindow.textFieldDisable(addWindow.getTxfAuthorAge());
		addWindow.comboBoxLock(addWindow.getComboBoxAuthor());
		addWindow.setVisible(true);
		actionListenersAddWindow();
	}

	private static void printList(ResultSet rs) throws SQLException {
		while (rs.next()) {
			String testStr = rs.getString("name") + " " + rs.getString("adress");
			System.out.println(testStr);
		}
	}

	private static ComboBoxObject[] dropDownMenuObject(ResultSet rs, String defaultStr) throws SQLException {
		rs.beforeFirst();
		int rowCount = 0;
		while (rs.next()) {
			rowCount++;
		}
		rs.beforeFirst();
		ComboBoxObject[] dropDownMenu = new ComboBoxObject[rowCount + 1];
		dropDownMenu[0] = new ComboBoxObject(defaultStr, 0);
		int i = 1;
		while (rs.next()) {
			if (rs.getString(3).length() > 3) {
				dropDownMenu[i] = new ComboBoxObject((rs.getString(2) + " | " + rs.getString(3)), rs.getInt(1));
			} else {
				dropDownMenu[i] = new ComboBoxObject((rs.getString(2) + " | " + rs.getString(3)), rs.getInt(1));
			}
			i++;
		}
		return dropDownMenu;
	}

	private static String[] dropDownMenuString(ResultSet rs, String defaultStr) throws SQLException {
		rs.beforeFirst();
		int rowCount = 0;
		while (rs.next()) {
			rowCount++;
		}
		rs.beforeFirst();
		String[] dropDownMenu = new String[rowCount + 1];
		dropDownMenu[0] = defaultStr;
		int i = 1;
		while (rs.next()) {
			if (rs.getString(3) != null) {
				dropDownMenu[i] = "Id: " + rs.getString(1) + " | " + rs.getString(2) + " | " + rs.getString(3);
			} else {
				dropDownMenu[i] = "Id: " + rs.getString(1) + " | " + rs.getString(2);
			}
			i++;
		}
		return dropDownMenu;
	}

	private String[] resultSetToArray(ResultSet rs) throws SQLException {
		rs.beforeFirst();
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnsCount = rsmd.getColumnCount();
		rs.beforeFirst();
		String[] rowData = new String[columnsCount];
		for (int i = 0; i < columnsCount; i++) {
			rowData[i] = rs.getString(i + 1);
		}
		return rowData;
	}

	private static void printTable(ResultSet rs) throws SQLException {
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnsCount = rsmd.getColumnCount();
		String[] columnNames = new String[columnsCount];
		for (int i = 0; i < columnsCount; i++) {
			columnNames[i] = rsmd.getColumnLabel(i + 1);

		}
		admWindow.addScrollTable(columnNames);
	}

	private static void addRow(ResultSet rs) throws SQLException {
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnsCount = rsmd.getColumnCount();
		rs.beforeFirst();
		String[] rowData = new String[columnsCount];
		while (rs.next()) {
			for (int i = 0; i < columnsCount; i++) {
				rowData[i] = rs.getString(i + 1);
			}
			admWindow.insertRow(rowData);
		}
	}

	private static void actionListenersAdmin() throws SQLException {
		admWindow.getBtnRemoveStore().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					initializeDeleteWindow(0);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		admWindow.getBtnRemoveBook().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					initializeDeleteWindow(1);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		admWindow.getBtnRemoveAuthor().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					initializeDeleteWindow(2);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		admWindow.getBtnAdd().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					initializeAddBookWindow();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		admWindow.getRefresh().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					refresh();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		admWindow.getBtnUpdateStore().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					initializeUpdateWindow(0);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		admWindow.getBtnUpdateBook().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					initializeUpdateWindow(1);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		admWindow.getBtnUpdateAuthor().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					initializeUpdateWindow(2);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		admWindow.getTxfSearch().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Controller.search();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	private static void actionListenersAddWindow() {
		addWindow.getBtnNewStore().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (addWindow.getBtnNewStore().getPressed()) {
					addWindow.getBtnNewStore().setPressed(false);
				} else {
					addWindow.getBtnNewStore().setPressed(true);
				}
				try {
					Controller.add();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		addWindow.getBtnNewBook().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (addWindow.getBtnNewBook().getPressed()) {
					addWindow.getBtnNewBook().setPressed(false);
					addWindow.disableButton(addWindow.getBtnNewAuthor());
					addWindow.comboBoxLock(addWindow.getComboBoxAuthor());
				} else {
					addWindow.getBtnNewBook().setPressed(true);
					addWindow.enableButton(addWindow.getBtnNewAuthor());
					addWindow.comboBoxUnlock(addWindow.getComboBoxAuthor());

				}
				try {
					Controller.add();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		addWindow.getBtnNewAuthor().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (addWindow.getBtnNewAuthor().getPressed()) {
					addWindow.getBtnNewAuthor().setPressed(false);
				} else {
					addWindow.getBtnNewAuthor().setPressed(true);
				}
				try {
					Controller.add();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		addWindow.getBtnSubmit().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addWindow.getBtnSubmit().setPressed(true);
				try {
					Controller.submitAdd();
					addWindow.setVisible(false);
					addWindow.setEnabled(false);
					addWindow = null;
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}				

		});
		addWindow.getComboBoxBooks().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	ResultSet rsId;
            	ObjectBox objectBox;
            	int authorId = 0;
                ObjectBox comboBox = (ObjectBox) event.getSource();
                System.out.println("Selected Item  = " + comboBox.getSelectedItem());
                System.out.println("Action Command = " + event.getActionCommand());
                if ("comboBoxEdited".equals(event.getActionCommand())) {
                    System.out.println("User has typed a string in the combo box.");
                } else if ("comboBoxChanged".equals(event.getActionCommand())) {
                	try{
                        System.out.println("User has selected an item from the combo box.");
            			int temp;
            			rsId = book.getTableDesc();
            			rsId.beforeFirst();
            			objectBox = addWindow.getComboBoxBooks();
            			temp = objectBox.getId();
            			while (rsId.next()) {
            				if (temp == rsId.getInt(1)) {
            					temp = rsId.getInt(3);
            					rsId = author.getTableDesc();
            					rsId.beforeFirst();
            					while (rsId.next()) {
            						if (rsId.getInt(1) == temp) {
            							authorId = rsId.getInt(1);
            						}
            					}
            				}
            			}
            			objectBox = addWindow.getComboBoxAuthor();
            			for (int i = 0; i < objectBox.getItemCount(); i++) {
            				if (authorId == objectBox.getIdAt(i)) {
            					authorId = i;
            				}
            			}
            			addWindow.getComboBoxAuthor().setSelectedIndex(authorId);
                        addWindow.getComboBoxAuthor().revalidate();	
                	}
                	catch(SQLException e){
                		e.printStackTrace();
                	}
                }
            }
        });
	}

	private static void actionListenersDeleteWindow(ResultSet rs, int index) {
		deleteWindow.getBtn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteWindow.getBtn().setPressed(true);
				try {
					delete(rs, index);
					deleteWindow.setVisible(false);
					deleteWindow.setEnabled(false);
					deleteWindow = null;
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
	private static void actionListenersUpdateWindow(ResultSet rs, int index){
		updateWindow.getBtn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateWindow.getBtn().setPressed(true);
				try {
					update(rs, index);
					updateWindow.setVisible(false);
					updateWindow.setEnabled(false);
					updateWindow = null;
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});	
	}
}
