package code;

/** A person in a game */
public class Person {

	/** the strength of the person */
	private int strength;

	/** the energy level of the person, it starts at a default value */
	private int energy = 10;

	/** the intelligence of the person */
	private int intelligence;

	/**
	 * Create a person with the given strength and intelligence
	 * 
	 * @param strength     the strength of the person
	 * @param intelligence the intelligence of the person
	 */
	public Person(int strength, int intelligence) {
		this.strength = strength;
		this.intelligence = intelligence;
	}

	/**
	 * Returns the strength of the person
	 * 
	 * @return the strength of the person
	 */
	public int getStrength() {
		return strength;
	}

	/**
	 * Changes the strength of the person
	 * 
	 * @param strength the new strength of the person
	 */
	public void setStrength(int strength) {
		this.strength = strength;
	}

	/**
	 * Returns the energy of the person
	 * 
	 * @return the current energy of the person
	 */
	public int getEnergy() {
		return energy;
	}

	/**
	 * Increases or decreases the energy of the person by amount
	 * 
	 * @param amount the amount that the person's energy will increase
	 */
	public void adjustEnergy(int amount) {
		this.energy = this.energy + amount;
	}

	/**
	 * Returns the intelligence of the person
	 * 
	 * @return the intelligence if the person
	 */
	public int getIntelligence() {
		return intelligence;
	}

	/**
	 * Changes the intelligence of the person
	 * 
	 * @param intelligence the new intelligence of the person
	 */
	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}
}
