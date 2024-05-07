package Loops;

import java.util.LinkedList;
import java.util.ListIterator;

public class Loops {
    public static void printEachCharacter(LinkedList<LinkedList<String>> list) {
        for (LinkedList<String> s : list) {
            ListIterator<String> it = s.listIterator();
            while (it.hasNext()) {
                String str = it.next();
                for (int i = 0; i < str.length(); i++) {
                    System.out.println(str.charAt(i));
                }
            }
        } 
    }

    // For testing
    public static void main(String[] args) {
        LinkedList<LinkedList<String>> list = new LinkedList<>();

        LinkedList<String> list1 = new LinkedList<>();
        LinkedList<String> list2 = new LinkedList<>();
        LinkedList<String> list3 = new LinkedList<>();

        list1.add("Connamacher");
        list1.add("Is");
        list1.add("The");
        list1.add("Best");

        list2.add("Nobel");
        list2.add("Should");
        list2.add("Go");
        list2.add("To");
        list2.add("Sleep");

        list3.add("Bye");
        list3.add("Bye");

        list.add(list1);
        list.add(list2);
        list.add(list3);

        printEachCharacter(list);
    }
}
