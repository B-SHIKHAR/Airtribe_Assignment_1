package main.java.com.airtribe.studentManagement.exception;

/**
 * Custom checked exception as requested.
 */
public class StudentNotFoundException extends Exception {
    public StudentNotFoundException(int id) {
        super("Student with id " + id + " not found.");
    }
    public StudentNotFoundException(String message) {
        super(message);
    }
}
