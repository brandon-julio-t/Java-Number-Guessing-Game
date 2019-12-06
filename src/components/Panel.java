package numberGuess.components;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Arrays;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class Panel extends JPanel {

	private static final long serialVersionUID = 1L;

	private Panel() {
	}

	public static Panel create() {
		return new Panel();
	}

	public Panel vertical() {
		this.setLayout(Layout.vertical());
		return this;
	}

	public Panel horizontal() {
		this.setLayout(Layout.horizontal());
		return this;
	}
	
	public Panel childrens(JComponent... childrens) {
		Arrays.asList(childrens).forEach(this::add);
		return this;
	}

	public static class Layout {

		public static GridLayout vertical() {
			return new GridLayout(0, 1);
		}

		public static FlowLayout horizontal() {
			return new FlowLayout();
		}

	}

}
