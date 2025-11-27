package main.java.com.airtribe.studentManagement.entity;

import main.java.com.airtribe.studentManagement.contract.Identifiable;

/**
 * Enrollment ties Student and Course; implements Identifiable; demonstrates typecasting.
 */
public class Enrollment implements Identifiable {
    private int id;
    private int studentId;
    private int courseId;
    private float marks;       // primitive
    private char grade;        // primitive
    private boolean active;    // primitive

    // Default constructor
    public Enrollment() {
        this(0, 0, 0, 0.0f, 'U', true);
    }

    // Parameterized constructor
    public Enrollment(int id, int studentId, int courseId, float marks, char grade, boolean active) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.marks = marks;
        this.grade = grade;
        this.active = active;
    }

    public int getId() { return id; }
    public int getStudentId() { return studentId; }
    public int getCourseId() { return courseId; }
    public float getMarks() { return marks; }
    public char getGrade() { return grade; }
    public boolean isActive() { return active; }

    public void setMarks(float marks) { this.marks = marks; }
    public void setGrade(char grade) { this.grade = grade; }
    public void setActive(boolean active) { this.active = active; }

    // Example of explicit casting during grade calculation
    public double percentage() {
        double d = (double) marks; // explicit float -> double
        return d; // assuming marks already as percentage
    }

    @Override
    public String getDisplayName() {
        return "Enrollment: student=" + studentId + ", course=" + courseId + ", grade=" + grade;
    }

    @Override
    public String toString() {
        return String.format("Enrollment{id=%d, studentId=%d, courseId=%d, marks=%.2f, grade=%c, active=%b}",
                id, studentId, courseId, marks, grade, active);
    }
}

