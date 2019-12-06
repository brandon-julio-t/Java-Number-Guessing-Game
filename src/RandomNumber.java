package numberGuess;

import java.util.Random;

public class RandomNumber {

	private static Integer min = null;
	private static Integer max = null;
	private static Integer randomValue = null;

	public static int getMin() {
		return ((min == null) ? (min = 0) : min).intValue();
	}

	public static int getMax() {
		return ((max == null) ? (max = 100) : max).intValue();
	}

	public static void setRandomRange(int min, int max) {
		RandomNumber.min = min;
		RandomNumber.max = max;
		generateRandomValue();
	}

	public static int getRandomValue() {
		if (randomValue == null) {
			generateRandomValue();
		}
		return randomValue.intValue();
	}

	private static void setRandomValue(int randomValue) {
		RandomNumber.randomValue = randomValue;
	}

	public static void generateRandomValue() {
		setRandomValue(new Random().ints(getMin(), getMax() + 1).findAny().getAsInt());
		System.out.println("Debug: " + RandomNumber.getRandomValue());
	}

}
