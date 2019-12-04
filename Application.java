import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class Application extends JFrame {

	private static final long serialVersionUID = 1L;

	private static int MIN_GUESS_VALUE = 0;
	private static int MAX_GUESS_VALUE = 100;
	private static int randomValue = new Random().ints(MIN_GUESS_VALUE, MAX_GUESS_VALUE + 1).findFirst().getAsInt();

	public Application() {
		super("Number Guess");

		System.out.println("Debug: " + randomValue);

		JPanel verticalContentPanel = new JPanel(new GridLayout(0, 1));
		JMenuBar menuBar = new JMenuBar();

		/* Setup UI */
		JMenu menu = new JMenu("Menu");
		menuBar.add(menu);
		
		JMenuItem customizeGameMenuItem = new JMenuItem("Customize Game");
		menu.add(customizeGameMenuItem);

		customizeGameMenuItem.addActionListener(e -> {
			JFrame customizeGameFrame = new JFrame();
			customizeGameFrame.setLayout(new GridLayout(0, 1));

			JPanel errorPanel = new JPanel();
			JLabel errorLabel  = new JLabel();
			errorPanel.add(errorLabel);
			
			JPanel minValuePanel = new JPanel();
			JTextField minValueTextField = new JTextField(10);
			minValuePanel.add(new JLabel("Min value: "));
			minValuePanel.add(minValueTextField);

			JPanel maxValuePanel = new JPanel();
			JTextField maxValueTextField = new JTextField(10);
			maxValuePanel.add(new JLabel("Max value: "));
			maxValuePanel.add(maxValueTextField);
			
			JPanel actionButtons = new JPanel();
			JButton applyButton = new JButton("Apply");
			JButton cancelButton = new JButton("Cancel");
			actionButtons.add(applyButton);
			actionButtons.add(cancelButton);
			
			applyButton.addActionListener(ev -> {
				try {
					Integer newMinValue = Integer.valueOf(minValueTextField.getText());
					Integer newMaxValue = Integer.valueOf(maxValueTextField.getText());

					if (newMinValue < newMaxValue) {
						MIN_GUESS_VALUE = newMinValue;
						MAX_GUESS_VALUE = newMaxValue;
						randomValue = new Random().ints(MIN_GUESS_VALUE, MAX_GUESS_VALUE + 1).findFirst().getAsInt();
						
						System.out.println("Debug: " + randomValue);
						customizeGameFrame.dispose();
					} else {
						errorLabel.setText("Error: Min value must be smaller than Max Value");
					}
				} catch (Exception ex) {
					errorLabel.setText("Error: Values must be numeric.");
				}
				
				customizeGameFrame.pack();
			});
			cancelButton.addActionListener(ev -> customizeGameFrame.dispose());
			
			customizeGameFrame.add(errorPanel);
			customizeGameFrame.add(minValuePanel);
			customizeGameFrame.add(maxValuePanel);
			customizeGameFrame.add(actionButtons);

			customizeGameFrame.setLocationRelativeTo(this);
			customizeGameFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			customizeGameFrame.pack();
			customizeGameFrame.setVisible(true);
		});

		JPanel instructionPanel = new JPanel();
		JLabel instructionLabel = new JLabel(
				String.format("Take a guess between %s - %s", MIN_GUESS_VALUE, MAX_GUESS_VALUE));
		instructionPanel.add(instructionLabel);

		JPanel hintPanel = new JPanel(new FlowLayout());
		JLabel hintLabel = new JLabel("Guess a number first");
		hintPanel.add(new JLabel("Hint: "));
		hintPanel.add(hintLabel);

		JPanel inputPanel = new JPanel(new FlowLayout());
		JTextField inputField = new JTextField(10);
		inputPanel.add(new JLabel("Input: "));
		inputPanel.add(inputField);

		JPanel actionButtons = new JPanel();
		JButton tryGuessButton = new JButton("Try Guess");
		JButton playAgainButton = new JButton("Play Again");
		actionButtons.add(tryGuessButton);
		actionButtons.add(playAgainButton);

		tryGuessButton.addActionListener(e -> {
			try {
				Integer guessedNumber = Integer.valueOf(inputField.getText());
				int comparisonResult = guessedNumber.compareTo(randomValue);

				switch (comparisonResult) {
				case -1:
					hintLabel.setText("Try a bigger number.");
					break;

				case 0:
					hintLabel.setText("Congratulations, You won the game!");
					tryGuessButton.setEnabled(false);
					playAgainButton.setEnabled(true);
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
		});

		playAgainButton.setEnabled(false);
		playAgainButton.addActionListener(e -> {
			randomValue = new Random().ints(MIN_GUESS_VALUE, MAX_GUESS_VALUE + 1).findFirst().getAsInt();
			tryGuessButton.setEnabled(true);
			playAgainButton.setEnabled(false);
			System.out.println("Debug: " + randomValue);
		});

		/* Build UI */
		verticalContentPanel.add(instructionPanel);
		verticalContentPanel.add(hintPanel);
		verticalContentPanel.add(inputPanel);
		verticalContentPanel.add(actionButtons);

		this.setJMenuBar(menuBar);
		this.getContentPane().add(verticalContentPanel);

		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new Application());
	}

}
