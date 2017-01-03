package Main;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.SwingConstants;

/**
 * Class that extends the JButton class, creates a borderless button that
 * changes color when the mouse howers over it.
 * 
 * @author Emil Karlsson
 *
 */
public class RolloverButton extends JButton implements MouseListener {

	private Color bgColor;
	private Color fgColor;
	private Color rolloverColor;
	private boolean isPressed;

	/**
	 * Constructor.
	 * 
	 * @param name
	 * @param bgColor
	 * @param fgColor
	 * @param rolloverColor
	 */
	public RolloverButton(String name, Color bgColor, Color fgColor, Color rolloverColor) {
		super(name);
		this.bgColor = bgColor;
		this.fgColor = fgColor;
		this.rolloverColor = rolloverColor;
		this.setHorizontalAlignment(SwingConstants.LEFT);
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

	/**
	 * Method that changes the text color when the mouse enters.
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == this) {
			this.setForeground(rolloverColor);
		}
	}

	/**
	 * Method that resets the text color when the mouse exits.
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource() == this) {
			this.setForeground(fgColor);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	public void setPressed(boolean isPressed) {
		this.isPressed = isPressed;
	}
	public boolean getPressed() {
		return this.isPressed;
	}
}
