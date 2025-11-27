package main.java.com.airtribe.studentManagement.contract;

public interface AcademicEvaluator {
    double calculateGrade(); // contract for dynamic dispatch
    String getLevel(); // undergraduate/graduate
}
