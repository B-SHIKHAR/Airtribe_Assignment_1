package main.java.com.airtribe.studentManagement.entity;

import main.java.com.airtribe.studentManagement.contract.AcademicEvaluator;
import main.java.com.airtribe.studentManagement.contract.Identifiable;

/**
 * Student demonstrates multilevel inheritance (AbstractPerson -> Student -> GraduateStudent),
 * data hiding, super()/this() chaining, primitives, method overloading, and dynamic dispatch.
 */
public class Student extends AbstractPerson implements Identifiable, AcademicEvaluator {
    // Primitive fields + controlled access
    private int id;               // int
    private String department;    // instance
    private String course;        // instance
    private boolean active;       // boolean
    private byte attendance;      // byte
    private short credits;        // short
    private long feesDue;         // long
    private float cgpa;           // float
    private double percentile;    // double
    private char gradeBand;       // char

    // Static variable (shared)
    public static int totalStudents = 0;

    // Final variable example
    public final String registrar = "University Registrar";

    // Default constructor
    public Student() {
        this(0, "Unknown", 18, 'O', "General", "Undeclared"); // this() chaining
    }

    // Parameterized constructor
    public Student(int id, String fullName, int age, char gender, String department, String course) {
        super(fullName, age, gender); // super() chaining
        this.id = id;
        this.department = department;
        this.course = course;
        this.active = true;
        this.attendance = (byte) 85;
        this.credits = (short) 20;
        this.feesDue = 30000L;
        this.cgpa = 7.5f;
        this.percentile = 70.0;
        this.gradeBand = 'B';
        totalStudents++;
    }

    // Overloaded constructor (different parameters)
    public Student(int id, String fullName) {
        this(id, fullName, 18, 'O', "General", "Undeclared");
    }

    // Getters/Setters (controlled access)
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public byte getAttendance() { return attendance; }
    public void setAttendance(byte attendance) { this.attendance = attendance; }

    public short getCredits() { return credits; }
    public void setCredits(short credits) { this.credits = credits; }

    public long getFeesDue() { return feesDue; }
    public void setFeesDue(long feesDue) { this.feesDue = feesDue; }

    public float getCgpa() { return cgpa; }
    public void setCgpa(float cgpa) { this.cgpa = cgpa; }

    public double getPercentile() { return percentile; }
    public void setPercentile(double percentile) { this.percentile = percentile; }

    public char getGradeBand() { return gradeBand; }
    public void setGradeBand(char gradeBand) { this.gradeBand = gradeBand; }

    // Method overloading: update academic
    public void updateAcademic(float cgpa) {
        this.cgpa = cgpa;
        this.percentile = cgpa * 10.0; // implicit widening float -> double
    }

    public void updateAcademic(float cgpa, double percentile) {
        this.cgpa = cgpa;
        this.percentile = percentile;
    }

    // Dynamic dispatch via interface method
    @Override
    public double calculateGrade() {
        double base = getCgpa() * 10.0;
        int bonus = getAttendance(); // byte -> int (implicit)
        return base + bonus * 0.1;
    }

    @Override
    public String getLevel() {
        return "Undergraduate";
    }

    // Identifiable interface
    @Override
    public String getDisplayName() {
        return getFullName() + " (" + department + ", " + course + ")";
    }

    @Override
    public String whoAmI() {
        return "I am a student.";
    }

    @Override
    public String toString() {
        return String.format(
                "Student{id=%d, name='%s', dept='%s', course='%s', cgpa=%.2f, percentile=%.2f, active=%b}",
                id, getFullName(), department, course, cgpa, percentile, active);
    }

}

