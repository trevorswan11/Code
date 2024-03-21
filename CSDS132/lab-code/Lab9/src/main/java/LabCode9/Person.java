package LabCode9;

/** A person in a game */
public class Person {
	/** the strength of the person */
	private int strength;

	/** the energy level of the person, it starts at a default value */
	private int energy = 10;
	
	/** the intelligence of the person */
	private int intelligence;

	/** What the person is carrying */
	private String carrying;

	/** The item the person is carrying */
	private GameItem item;

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
	 * Returns the item in the inventory
	 * 
	 * @return the current item in the inventory
	 */
	public String getCarrying() {
		return this.carrying;
	}

	/**
	 * Returns the game item
	 * 
	 * @return the current item as a GameItem
	 */
	public GameItem getItem() {
		return this.item;
	}

	 /**
	  * Sets the item and carrying being held by the person
	  * @param o A GameItem
	  * @return void
	  */
	public void setItem(GameItem o) {
		this.carrying = o.getName();
		this.item = o;
	}

	/**
	 * Changes the intelligence of the person
	 * 
	 * @param intelligence the new intelligence of the person
	 */
	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	public boolean pickup(GameItem o) {
		if (o.getWeight() < this.getStrength()) {
			this.setItem(o);
			return true;
		}
		return false;
	}

	public GameItem drop() {
		if (carrying == null) {
			return null;
		}
		GameItem temp = this.getItem();
		this.setItem(null);
		return temp;
	}

	public void eat(Food f) {
		this.adjustEnergy((int)(f.getCalories()/100.0));
		f.eat(this);
	}
}
