package numberGuess.components;

import javax.swing.JLabel;

public class Label extends JLabel {

	private static final long serialVersionUID = 1L;

	private Label() {
	}
	
	public static Label create() {
		return new Label();
	}
	
	public JLabel text(String text) {
		this.setText(text);
		return this;
	}
	
}
