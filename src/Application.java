package numberGuess;

import javax.swing.SwingUtilities;

import numberGuess.views.ApplicationView;

public class Application {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> ApplicationView.init());
	}

}