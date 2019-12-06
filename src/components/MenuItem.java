package numberGuess.components;

import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

public class MenuItem extends JMenuItem {

	private static final long serialVersionUID = 1L;
	
	private MenuItem() {
	}
	
	public static MenuItem create() {
		return new MenuItem();
	}
	
	public MenuItem label(String text) {
		this.setText(text);
		return this;
	}
	
	public MenuItem onClick(ActionListener action) {
		this.addActionListener(action);
		return this;
	}

}
