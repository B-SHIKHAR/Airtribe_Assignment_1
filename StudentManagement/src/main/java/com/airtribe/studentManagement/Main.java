package main.java.com.airtribe.studentManagement;

import java.util.List;
import java.util.Scanner;

import main.java.com.airtribe.studentManagement.entity.Course;
import main.java.com.airtribe.studentManagement.entity.Enrollment;
import main.java.com.airtribe.studentManagement.entity.GraduateStudent;
import main.java.com.airtribe.studentManagement.entity.Student;
import main.java.com.airtribe.studentManagement.exception.StudentNotFoundException;
import main.java.com.airtribe.studentManagement.service.CourseService;
import main.java.com.airtribe.studentManagement.service.EnrollmentService;
import main.java.com.airtribe.studentManagement.service.StudentService;
import main.java.com.airtribe.studentManagement.util.AppConstants;

public class Main {
    private final StudentService studentService = new StudentService();
    private final CourseService courseService = new CourseService();
    private final EnrollmentService enrollmentService = new EnrollmentService();
    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
         AppConstants constants = new AppConstants();
         System.out.println(constants.about());
         System.out.println(AppConstants.APP_TITLE);

        new Main().start();
    }

    private void start() {
        seedData();
        boolean running = true;
        while (running) {
            showMenu();
            int choice = readInt();
            switch (choice) {
                case 1 -> addStudentFlow();
                case 2 -> viewStudentsFlow();
                case 3 -> updateStudentFlow();
                case 4 -> deleteStudentFlow();
                case 5 -> searchStudentFlow();
                case 6 -> runtimePolymorphismDemo();
                case 7 -> courseMenu();
                case 8 -> enrollmentMenu();
                case 0 -> running = false;
                default -> System.out.println("Invalid choice.");
            }
        }
        System.out.println("Goodbye.");
    }

    private void showMenu() {
        System.out.println("""
                ---- Main Menu ----
                1. Add Student
                2. View Students
                3. Update Student
                4. Delete Student
                5. Search Student (ID/Name)
                6. Compute Runtime Grade (dynamic dispatch)
                7. Course Management
                8. Enrollment Management
                0. Exit
                """);
        System.out.print("Enter choice: ");
    }

    private void addStudentFlow() {
        System.out.println("""
                Add Student:
                1. Undergraduate
                2. Graduate
                3. Quick Add (id + name)
                """);
        System.out.print("Select: ");
        int sel = readInt();
        if (sel == 1) {
            System.out.print("ID: ");
            int id = readInt();
            System.out.print("Name: "); String name = scanner.nextLine();
            System.out.print("Age: "); int age = readInt();
            System.out.print("Gender (M/F/O): "); char gender = readChar();
            System.out.print("Department: "); String dept = scanner.nextLine();
            System.out.print("Course: "); String course = scanner.nextLine();
            Student s = new Student(id, name, age, gender, dept, course);
            studentService.add(s);
            System.out.println("Added: " + s);
        } else if (sel == 2) {
            System.out.print("ID: ");
            int id = readInt();
            System.out.print("Name: "); String name = scanner.nextLine();
            System.out.print("Age: "); int age = readInt();
            System.out.print("Gender (M/F/O): "); char gender = readChar();
            System.out.print("Department: "); String dept = scanner.nextLine();
            System.out.print("Course: "); String course = scanner.nextLine();
            System.out.print("Thesis Title: "); String thesis = scanner.nextLine();
            System.out.print("Advisor: "); String advisor = scanner.nextLine();
            GraduateStudent gs = new GraduateStudent(id, name, age, gender, dept, course, thesis, advisor);
            studentService.add(gs); // multiple implementations; base class reference
            System.out.println("Added: " + gs);
        } else if (sel == 3) {
            System.out.print("ID: ");
            int id = readInt();
            System.out.print("Name: ");
            String name = scanner.nextLine();
            studentService.add(id, name);
            try {
                System.out.println("Quick added: " + studentService.findById(id));
            } catch (StudentNotFoundException ignored) {}
        } else {
            System.out.println("Invalid selection.");
        }
    }

    private void viewStudentsFlow() {
        List<Student> all = studentService.viewAll();
        if (all.isEmpty()) System.out.println("No students.");
        else all.forEach(s -> System.out.println(" - " + s));
        System.out.printf("Total students (static): %d%n", Student.totalStudents);
    }

    private void updateStudentFlow() {
        System.out.print("ID to update: "); int id = readInt();
        System.out.println("""
                Update:
                1. Name
                2. Academics (CGPA & Percentile)
                """);
        System.out.print("Select: "); int sel = readInt();
        boolean ok = false;
        if (sel == 1) {
            System.out.print("New Name: "); String name = scanner.nextLine();
            ok = studentService.updateName(id, name);
        } else if (sel == 2) {
            System.out.print("New CGPA (float): "); float cgpa = readFloat();
            System.out.print("New Percentile (double): "); double p = readDouble();
            ok = studentService.updateAcademics(id, cgpa, p);
        }
        System.out.println(ok ? "Update successful." : "Update failed.");
    }

    private void deleteStudentFlow() {
        System.out.print("ID to delete: "); int id = readInt();
        boolean ok = studentService.delete(id);
        System.out.println(ok ? "Deleted." : "Delete failed.");
    }

    private void searchStudentFlow() {
        System.out.println("""
                Search:
                1. By ID
                2. By Name
                """);
        System.out.print("Select: "); int sel = readInt();
        if (sel == 1) {
            System.out.print("ID: "); int id = readInt();
            try {
                System.out.println(studentService.findById(id));
            } catch (StudentNotFoundException e) {
                System.out.println(e.getMessage());
            }
        } else if (sel == 2) {
            System.out.print("Name contains: "); String q = scanner.nextLine();
            var list = studentService.findByName(q);
            if (list.isEmpty()) System.out.println("No matches.");
            else list.forEach(System.out::println);
        } else {
            System.out.println("Invalid selection.");
        }
    }

    private void runtimePolymorphismDemo() {
        System.out.print("ID: "); int id = readInt();
        try {
            double grade = studentService.computeRuntimeGrade(id);
            System.out.printf("Computed grade: %.2f%n", grade);
        } catch (StudentNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    // Course submenu
    private void courseMenu() {
        System.out.println("""
                --- Course Management ---
                1. Add Course
                2. View Courses
                3. Update Title
                4. Delete Course
                5. Search by Title
                0. Back
                """);
        System.out.print("Choice: "); int ch = readInt();
        switch (ch) {
            case 1 -> {
                System.out.print("ID: "); int id = readInt();
                System.out.print("Code: "); String code = scanner.nextLine();
                System.out.print("Title: "); String title = scanner.nextLine();
                System.out.print("Credits (short): "); short credits = (short) readInt();
                var c = new Course(id, code, title, credits, true);
                courseService.add(c);
                System.out.println("Added: " + c);
            }
            case 2 -> courseService.viewAll().forEach(System.out::println);
            case 3 -> {
                System.out.print("ID: "); int id = readInt();
                System.out.print("New Title: "); String t = scanner.nextLine();
                System.out.println(courseService.updateTitle(id, t) ? "Updated." : "Update failed.");
            }
            case 4 -> {
                System.out.print("ID: "); int id = readInt();
                System.out.println(courseService.delete(id) ? "Deleted." : "Delete failed.");
            }
            case 5 -> {
                System.out.print("Title contains: "); String q = scanner.nextLine();
                var list = courseService.findByTitle(q);
                if (list.isEmpty()) System.out.println("No matches.");
                else list.forEach(System.out::println);
            }
            case 0 -> {}
            default -> System.out.println("Invalid.");
        }
    }

    // Enrollment submenu
    private void enrollmentMenu() {
        System.out.println("""
                --- Enrollment Management ---
                1. Add Enrollment
                2. View Enrollments
                3. Update Marks/Grade
                4. Delete Enrollment
                5. Search by Student ID
                0. Back
                """);
        System.out.print("Choice: "); int ch = readInt();
        switch (ch) {
            case 1 -> {
                System.out.print("ID: "); int id = readInt();
                System.out.print("Student ID: "); int sid = readInt();
                System.out.print("Course ID: "); int cid = readInt();
                System.out.print("Marks (float): "); float m = readFloat();
                System.out.print("Grade (char): "); char g = readChar();
                var e = new Enrollment(id, sid, cid, m, g, true);
                enrollmentService.add(e);
                System.out.println("Added: " + e);
            }
            case 2 -> enrollmentService.viewAll().forEach(System.out::println);
            case 3 -> {
                System.out.print("ID: "); int id = readInt();
                System.out.print("Marks (float): "); float m = readFloat();
                System.out.print("Grade (char): "); char g = readChar();
                System.out.println(enrollmentService.updateMarks(id, m, g) ? "Updated." : "Update failed.");
            }
            case 4 -> {
                System.out.print("ID: "); int id = readInt();
                System.out.println(enrollmentService.delete(id) ? "Deleted." : "Delete failed.");
            }
            case 5 -> {
                System.out.print("Student ID: "); int sid = readInt();
                var list = enrollmentService.findByStudentId(sid);
                if (list.isEmpty()) System.out.println("No matches.");
                else list.forEach(System.out::println);
            }
            case 0 -> {}
            default -> System.out.println("Invalid.");
        }
    }

    // Seed data for quick demo
    private void seedData() {
        studentService.add(new Student(1, "Aarav Verma", 19, 'M', "CS", "DSA"));
        studentService.add(new GraduateStudent(2, "Diya Rao", 23, 'F', "ECE", "VLSI", "Low-power Design", "Prof. Sen"));
        courseService.add(new Course(101, "CS101", "Intro to CS", (short) 4, true));
        courseService.add(new Course(102, "CS102", "Data Structures", (short) 4, true));
    }

    // Input helpers with local scope
    private int readInt() {
        while (true) {
            try { return Integer.parseInt(scanner.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.print("Enter a valid integer: "); }
        }
    }
    private float readFloat() {
        while (true) {
            try { return Float.parseFloat(scanner.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.print("Enter a valid float: "); }
        }
    }
    private double readDouble() {
        while (true) {
            try { return Double.parseDouble(scanner.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.print("Enter a valid double: "); }
        }
    }
    private char readChar() {
        String s = scanner.nextLine().trim();
        return s.isEmpty() ? 'O' : s.charAt(0);
    }
}

