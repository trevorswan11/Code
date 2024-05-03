package ComparableChallenge;

import java.util.Comparator;

public class Person {
    // The age of the person
    private int age;

    // A constructor to create a person with an age
    public Person(int age) {
        this.setAge(age);
    }

    // Getter for age
    public int getAge() {
        return this.age;
    }

    // Setter for Age
    public void setAge(int age) {
        this.age = age;
    }

    // A static method to compare two people's ages
    public static int ageDifference(Person person1, Person person2) {
        return person1.getAge() - person2.getAge();
    }

    // Returns a comparator for the class
    public static Comparator<Person> getComparator() {
        return Person::ageDifference;
    }
}