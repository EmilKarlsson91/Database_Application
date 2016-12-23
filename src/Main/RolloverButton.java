package Main;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class RolloverButton extends JButton implements MouseListener {

	private Color bgColor;
	private Color fgColor;
	private Color rolloverColor;
	private boolean isPressed;
	
	public RolloverButton(String name, Color bgColor, Color fgColor, Color rolloverColor ){
		super(name);
		this.bgColor = bgColor;
		this.fgColor = fgColor;
		this.rolloverColor = rolloverColor;
		this.setBorderPainted(false);
		this.setRolloverEnabled(false);
		this.setContentAreaFilled(false);
		this.setFocusPainted(false);
		this.setBackground(this.bgColor);
		this.setForeground(this.fgColor);
		addMouseListener(this);
	}
	
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == this) {
			this.setForeground(rolloverColor);
		}

	}

	

	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource()==this) {
			this.setForeground(fgColor); 
			}
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

	public void setPressed(boolean isPressed){
		this.isPressed = isPressed;
	}
	public boolean getPressed(){
		return this.isPressed;
	}
}
