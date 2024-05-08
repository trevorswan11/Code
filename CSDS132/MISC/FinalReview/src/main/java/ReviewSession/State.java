package ReviewSession;

import java.util.Comparator;

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

    public static Comparator<State> compareByPop() {
        return (state1, state2) -> Integer.compare(state1.getPopulation(), state2.getPopulation());
    }

    public static Comparator<?> comparingPopulation() {
        return Comparator.comparing(State::getPopulation);
    }

    public static Comparator<?> comparingCode() {
        return Comparator.comparing(State::getTwoLetterCode);
    }
}
