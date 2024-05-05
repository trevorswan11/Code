package ReviewSession;

public class State {
    private int population;
    private String twoLetterCode;

    public State() {
        population = 0;
        twoLetterCode = "NA";
    }

    public State(int population, String twoLetterCode) {
        this.population = population;
        this.twoLetterCode = twoLetterCode;
    }
    
    public int getPopulation() {
        return population;
    }

    public String getTwoLetterCode() {
        return twoLetterCode;
    }
    
    public void setPopulation(int population) {
        this.population = population;
    }
    
    public void setTwoLetterCode(String twoLetterCode) {
        this.twoLetterCode = twoLetterCode;
    }
    
    public boolean equals(Object o) {
        if (o instanceof State) {
            State s = (State) o;
            return s.getPopulation() == getPopulation() && getTwoLetterCode().equals(s.getTwoLetterCode());
        }
        return false;
    }
}
