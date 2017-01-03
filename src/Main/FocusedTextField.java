package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;
/**
 * Class that extends the JTextField class, creates textfields that have a hint.
 * @author Emil Karlsson
 *
 */
public class FocusedTextField extends JTextField implements FocusListener{

	private String hint;

	/**
	 * Constructor.
	 * @param hint
	 */
	public FocusedTextField(String hint){
		this.hint = hint;
		this.setText(hint);
		this.setFont(new Font("Tahoma", Font.ITALIC, 13));
		this.setForeground(Color.gray);
		addFocusListener(this);
	}
	
	/**
	 * Method that modifies the text if it gets focus.
	 */
	@Override
	public void focusGained(FocusEvent e) {
		this.setText("");
		this.setFont(new Font("Tahoma", Font.BOLD, 13));
		this.setForeground(Color.black);

	}

	/**
	 * Method that modifies the text if it looses focus.
	 */
	@Override
	public void focusLost(FocusEvent e) {
		if(this.getText() == "" || this.getText() == null){
			this.setText(this.hint);
		}
		this.setFont(new Font("Tahoma", Font.ITALIC, 13));
		this.setForeground(Color.gray);
	}

}
