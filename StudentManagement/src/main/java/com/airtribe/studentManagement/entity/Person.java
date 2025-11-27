package main.java.com.airtribe.studentManagement.entity;

/**
 * Concrete entity showing single inheritance (Person extends AbstractPerson).
 */
public class Person extends AbstractPerson {
    // Overriding constructor (calls super)
    public Person(String fullName, int age, char gender) {
        super(fullName, age, gender); // constructor chaining via super()
    }

    // Default constructor (overriding constructor style)
    public Person() {
        super();
    }

    @Override
    public String whoAmI() {
        return "I am a person.";
    }
}

