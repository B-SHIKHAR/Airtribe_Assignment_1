package main.java.com.airtribe.studentManagement.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import main.java.com.airtribe.studentManagement.entity.*;
/**
 * Enrollment CRUD using ArrayList.
 */
public class EnrollmentService {
    private final List<Enrollment> enrollments = new ArrayList<>();

    public void add(Enrollment e) { enrollments.add(e); }

    public List<Enrollment> viewAll() { return new ArrayList<>(enrollments); }

    public Enrollment findById(int id) {
        for (Enrollment e : enrollments) {
            if (e.getId() == id) return e;
        }
        return null;
    }

    public List<Enrollment> findByStudentId(int studentId) {
        List<Enrollment> result = new ArrayList<>();
        for (Enrollment e : enrollments) {
            if (e.getStudentId() == studentId) result.add(e);
        }
        return result;
    }

    public boolean updateMarks(int id, float marks, char grade) {
        Enrollment e = findById(id);
        if (e == null) return false;
        e.setMarks(marks);
        e.setGrade(grade);
        return true;
    }

    public boolean delete(int id) {
        Iterator<Enrollment> it = enrollments.iterator();
        while (it.hasNext()) {
            Enrollment e = it.next();
            if (e.getId() == id) {
                it.remove();
                return true;
            }
        }
        return false;
    }
}

