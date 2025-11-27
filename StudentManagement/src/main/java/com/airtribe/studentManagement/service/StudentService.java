package main.java.com.airtribe.studentManagement.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import main.java.com.airtribe.studentManagement.entity.*;
import main.java.com.airtribe.studentManagement.exception.*;

/**
 * StudentService implements CRUD with ArrayList; demonstrates base class references and runtime polymorphism.
 */
public class StudentService {
    private final List<Student> students = new ArrayList<>(); // dynamic data

    // Create (overloaded)
    public void add(Student s) { students.add(s); }
    public void add(GraduateStudent gs) { students.add(gs); }
    public void add(int id, String name) {
        Student s = new Student(id, name);
        s.updateAcademic(7.0f);
        students.add(s);
    }

    // Read
    public List<Student> viewAll() {
        return new ArrayList<>(students);
    }

    public Student findById(int id) throws StudentNotFoundException {
        for (Student s : students) {
            if (s.getId() == id) return s; // base class ref; dynamic dispatch when calling s.calculateGrade()
        }
        throw new StudentNotFoundException(id);
    }

    public List<Student> findByName(String query) {
        List<Student> result = new ArrayList<>();
        for (Student s : students) {
            if (s.getFullName().toLowerCase().contains(query.toLowerCase())) {
                result.add(s);
            }
        }
        return result;
    }

    // Update (overloaded)
    public boolean updateName(int id, String newName) {
        for (Student s : students) {
            if (s.getId() == id) {
                s.setFullName(newName);
                return true;
            }
        }
        return false;
    }

    public boolean updateAcademics(int id, float cgpa, double percentile) {
        for (Student s : students) {
            if (s.getId() == id) {
                s.updateAcademic(cgpa, percentile);
                return true;
            }
        }
        return false;
    }

    // Delete
    public boolean delete(int id) {
        Iterator<Student> it = students.iterator();
        while (it.hasNext()) {
            Student s = it.next();
            if (s.getId() == id) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    // Runtime polymorphism demo
    public double computeRuntimeGrade(int id) throws StudentNotFoundException {
        Student s = findById(id);
        return s.calculateGrade(); // dynamic dispatch (Student vs GraduateStudent)
    }
}

