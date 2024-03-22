package PreLabCode9;

public class PreLab9Main extends PreLab9Abstract {
	private String food;

	/* Constructor that inherits the abstract Constructor */
	public PreLab9Main(double calories, String food) {
		super(calories);
		this.setFood(food);
	}

	/* Getter method for food name */
	public String getFood() {
		return this.food;
	}

	/* Setter method for food name */
	public void setFood(String food) {
		this.food = food;
	}

	/*
	 * Overrides the method inherited from the abstract class to return the 2000
	 * calorie limit minus the calories consumed
	 */
	@Override
	public double caloriesLeft() {
		return 2000 - getCalories(20);
	}
}
