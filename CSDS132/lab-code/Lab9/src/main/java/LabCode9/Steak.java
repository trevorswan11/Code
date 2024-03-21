package LabCode9;

public class Steak extends Food {
    public Steak() {
      super("Steak", 2, 800);
    }

    @Override
    public void eat(Person p) {
      p.setStrength(p.getStrength() + (int)(this.getCalories()/100));
    }
  }