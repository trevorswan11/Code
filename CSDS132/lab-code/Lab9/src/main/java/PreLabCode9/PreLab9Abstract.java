package PreLabCode9;

public abstract class PreLab9Abstract {
	private double calories;

	/* This constructor creates an Instance of this class with an inputted name */
	public PreLab9Abstract(double calories) {
		this.calories = calories;
	}

	/*
	 * This abstract method allows overriding to calculate calories available to
	 * consume in a day
	 */
	public abstract double caloriesLeft();

	/* First of two overloaded public methods, no argument returns field value */
	public double getCalories() {
		return this.calories;
	}

	/*
	 * Second overloaded public method, single argument calculates amount of food
	 * times its calorie count
	 */
	public double getCalories(double amount) {
		return this.getCalories() * amount;
	}
}
