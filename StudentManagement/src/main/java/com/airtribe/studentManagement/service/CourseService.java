package main.java.com.airtribe.studentManagement.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import main.java.com.airtribe.studentManagement.entity.Course;

/**
 * Course CRUD using ArrayList.
 */
public class CourseService {
    private final List<Course> courses = new ArrayList<>();

    public void add(Course c) { courses.add(c); }

    public List<Course> viewAll() { return new ArrayList<>(courses); }

    public Course findById(int id) {
        for (Course c : courses) {
            if (c.getId() == id) return c;
        }
        return null;
    }

    public List<Course> findByTitle(String q) {
        List<Course> result = new ArrayList<>();
        for (Course c : courses) {
            if (c.getTitle().toLowerCase().contains(q.toLowerCase())) {
                result.add(c);
            }
        }
        return result;
    }

    public boolean updateTitle(int id, String newTitle) {
        Course c = findById(id);
        if (c == null) return false;
        c.setTitle(newTitle);
        return true;
    }

    public boolean delete(int id) {
        Iterator<Course> it = courses.iterator();
        while (it.hasNext()) {
            Course c = it.next();
            if (c.getId() == id) {
                it.remove();
                return true;
            }
        }
        return false;
    }
}

