package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public class FocusedTextField extends JTextField implements FocusListener{

	private String hint;
	
	public FocusedTextField(){
		
	}
	public FocusedTextField(String hint){
		this.hint = hint;
		this.setText(hint);
		this.setFont(new Font("Tahoma", Font.ITALIC, 13));
		this.setForeground(Color.gray);
		addFocusListener(this);
	}
	
	@Override
	public void focusGained(FocusEvent e) {
		this.setText("");
		this.setFont(new Font("Tahoma", Font.BOLD, 13));
		this.setForeground(Color.black);

	}

	@Override
	public void focusLost(FocusEvent e) {
		if(this.getText() == ""){
			this.setText(this.hint);
			this.setText(hint);
		}
		this.setFont(new Font("Tahoma", Font.ITALIC, 13));
		this.setForeground(Color.gray);
	}

}
