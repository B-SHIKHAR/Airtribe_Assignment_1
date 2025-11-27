package main.java.com.airtribe.studentManagement.entity;

import main.java.com.airtribe.studentManagement.contract.Identifiable;

/**
 * Course entity implements Identifiable and uses primitives; demonstrates constructors and data hiding.
 */
public class Course implements Identifiable {
    private int id;
    private String code;
    private String title;
    private short credits;  // primitive
    private boolean active; // primitive

    // Default constructor
    public Course() {
        this(0, "NA", "Untitled", (short) 0, true);
    }

    // Parameterized constructor
    public Course(int id, String code, String title, short credits, boolean active) {
        this.id = id;
        this.code = code;
        this.title = title;
        this.credits = credits;
        this.active = active;
    }

    // Overriding constructor style not typical for POJO but default provided
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public short getCredits() { return credits; }
    public void setCredits(short credits) { this.credits = credits; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    @Override
    public String getDisplayName() {
        return code + " - " + title;
    }

    @Override
    public String toString() {
        return String.format("Course{id=%d, code='%s', title='%s', credits=%d, active=%b}",
                id, code, title, credits, active);
    }
}

