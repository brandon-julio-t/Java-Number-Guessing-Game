package numberGuess.views;

import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import numberGuess.RandomNumber;
import numberGuess.components.Button;
import numberGuess.components.Label;
import numberGuess.components.Panel;

public class CustomizeGameView extends JFrame {

	private static final long serialVersionUID = 1L;

	JLabel errorLabel = Label.create();
	JTextField minValueTextField = new JTextField(10);
	JTextField maxValueTextField = new JTextField(10);
	Button applyButton = Button.create().label("Apply").onClick(makeApplyButtonAction());
	Button cancelButton = Button.create().label("Cancel").onClick(e -> this.dispose());

	private CustomizeGameView() {
		super("Customize Game");

		/* Start UI */

		JPanel errorPanel = Panel.create().horizontal().childrens(errorLabel);
		JPanel minValuePanel = Panel.create().horizontal().childrens(Label.create().text("Min value: "),
				minValueTextField);
		JPanel maxValuePanel = Panel.create().horizontal().childrens(Label.create().text("Max value: "),
				maxValueTextField);
		JPanel actionButtonsPanel = Panel.create().horizontal().childrens(applyButton, cancelButton);

		JPanel contentPanel = Panel.create().vertical().childrens(errorPanel, minValuePanel, maxValuePanel,
				actionButtonsPanel);

		/* End UI */

		this.getContentPane().add(contentPanel);

		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}

	private ActionListener makeApplyButtonAction() {
		return e -> {
			try {
				Integer newMinValue = Integer.valueOf(minValueTextField.getText().trim());
				Integer newMaxValue = Integer.valueOf(maxValueTextField.getText().trim());

				if (newMinValue < newMaxValue) {
					RandomNumber.setRandomRange(newMinValue, newMaxValue);

					this.dispose();
				} else {
					errorLabel.setText("Error: Min value must be smaller than Max Value");
				}
			} catch (Exception ex) {
				errorLabel.setText("Error: Values must be numeric.");
			}

			minValueTextField.setText("");
			maxValueTextField.setText("");

			ApplicationView.getInstance().updateInstructionLabel();
			ApplicationView.getInstance().pack();

			this.pack();
		};
	}

	public static void init() {
		new CustomizeGameView();
	}

}
