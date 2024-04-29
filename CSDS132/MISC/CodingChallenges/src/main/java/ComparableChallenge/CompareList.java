package ComparableChallenge;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class CompareList {
    public static <T extends Comparable<? super T>> T maxListValue(LinkedList<T> list) throws NoSuchElementException {
        T greatest = list.isEmpty() ? null : list.getFirst();

        if (greatest == null) throw new NoSuchElementException();
        
        for (T element : list) {
            greatest = element.compareTo(greatest) > 0 ? element : greatest; 
        }

        return greatest;
    }
}