package numberGuess.views;

import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import numberGuess.RandomNumber;
import numberGuess.components.Button;
import numberGuess.components.Label;
import numberGuess.components.MenuBar;
import numberGuess.components.MenuItem;
import numberGuess.components.Panel;

public class ApplicationView extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final String instructionLabelFormatTemplate = "Take a guess between %s - %s";
	private static final String defaultHintText = "Guess a number first.";

	private static ApplicationView instance = null;

	private JLabel instructionLabel = Label.create();
	private JLabel hintLabel = Label.create().text(defaultHintText);
	private JTextField inputField = new JTextField(10);
	private Button tryGuessButton = Button.create().label("Try Guess").onClick(makeTryGuessButtonAction());
	private Button playAgainButton = Button.create().label("Play Again").onClick(makePlayAgainButtonAction());

	private ApplicationView() {
		super("Number Guess");

		/* Start UI */

		JMenuBar menuBar = MenuBar.create()
				.children(MenuItem.create().label("Customize Game").onClick(e -> CustomizeGameView.init()));

		JPanel instructionPanel = Panel.create().horizontal().childrens(instructionLabel);
		JPanel hintPanel = Panel.create().horizontal().childrens(Label.create().text("Hint: "), hintLabel);
		JPanel inputPanel = Panel.create().horizontal().childrens(Label.create().text("Input: "), inputField);
		JPanel actionButtonsPanel = Panel.create().horizontal().childrens(tryGuessButton, playAgainButton);

		JPanel contentPanel = Panel.create().vertical().childrens(instructionPanel, hintPanel, inputPanel,
				actionButtonsPanel);

		/* End UI */

		RandomNumber.generateRandomValue();

		this.instance = this;

		this.updateInstructionLabel();
		this.playAgainButton.disableButton();

		this.setJMenuBar(menuBar);
		this.getContentPane().add(contentPanel);

		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}

	private ActionListener makeTryGuessButtonAction() {
		return e -> {
			try {
				Integer guessedNumber = Integer.valueOf(inputField.getText().trim());
				int comparisonResult = guessedNumber.compareTo(RandomNumber.getRandomValue());

				switch (comparisonResult) {
				case -1:
					hintLabel.setText("Try a bigger number.");
					break;

				case 0:
					hintLabel.setText("Congratulations, You won the game!");
					tryGuessButton.disableButton();
					playAgainButton.enableButton();
					break;

				case 1:
					hintLabel.setText("Try a smaller number.");
					break;
				}

				this.pack();
			} catch (Exception ex) {
				hintLabel.setText("Input must be a number.");
				inputField.setText("");
			}
		};
	}

	private ActionListener makePlayAgainButtonAction() {
		return e -> {
			RandomNumber.generateRandomValue();

			tryGuessButton.enableButton();
			playAgainButton.disableButton();

			hintLabel.setText(defaultHintText);
			inputField.setText("");

			this.pack();
		};
	}

	public void updateInstructionLabel() {
		instructionLabel
				.setText(String.format(instructionLabelFormatTemplate, RandomNumber.getMin(), RandomNumber.getMax()));
	}

	public static ApplicationView getInstance() {
		return instance;
	}

	public static void init() {
		new ApplicationView();
	}

}
