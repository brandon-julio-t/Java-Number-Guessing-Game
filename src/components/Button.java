package numberGuess.components;

import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Button extends JButton {

	private static final long serialVersionUID = 1L;
	
	private Button(String label) {
		super(label);
	}
	
	private Button() {
	}

	public static Button create() {
		return new Button();
	}
	
	public Button label(String label) {
		this.setText(label);
		return this;
	}
	
	public Button onClick(ActionListener action) {
		this.addActionListener(action);
		return this;
	}
	
	public void enableButton() {
		this.setEnabled(true);
	}
	
	public void disableButton() {
		this.setEnabled(false);
	}

}
