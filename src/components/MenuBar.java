package numberGuess.components;

import javax.swing.JMenuBar;

public class MenuBar extends JMenuBar {

	private static final long serialVersionUID = 1L;

	private MenuBar() {
	}

	public static MenuBar create() {
		return new MenuBar();
	}

	public MenuBar children(MenuItem children) {
		this.add(children);
		return this;
	}

}
