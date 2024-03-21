package LabCode9;

public abstract class Food extends GameItem {
    private double calories;

    public Food(String name, double weight, double calories) {
        super(name, weight);
        this.setCalories(calories);
    }

    public double getCalories() {
        return this.calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public void eat(Person p) {}
}
