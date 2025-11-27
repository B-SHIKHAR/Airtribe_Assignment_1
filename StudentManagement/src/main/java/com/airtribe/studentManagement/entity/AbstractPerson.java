package main.java.com.airtribe.studentManagement.entity;

/**
 * Abstract base class to demonstrate abstract methods and runtime polymorphism.
 */
public abstract class AbstractPerson {
    // Instance variables (data hiding)
    private String fullName;
    private int age;
    private char gender; // 'M', 'F', 'O'

    // Default constructor
    public AbstractPerson() {
        this("Unknown", 0, 'O'); // constructor chaining via this()
    }

    // Parameterized constructor
    public AbstractPerson(String fullName, int age, char gender) {
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
    }

    // Controlled access via getters/setters
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public char getGender() { return gender; }
    public void setGender(char gender) { this.gender = gender; }

    // Abstract method
    public abstract String whoAmI();

    @Override
    public String toString() {
        return String.format("%s{name='%s', age=%d, gender=%c}",
                getClass().getSimpleName(), fullName, age, gender);
    }
}


