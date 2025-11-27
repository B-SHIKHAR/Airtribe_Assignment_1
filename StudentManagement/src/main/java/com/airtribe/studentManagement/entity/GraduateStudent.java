package main.java.com.airtribe.studentManagement.entity;

/**
 * GraduateStudent extends Student (multilevel inheritance) and refines behavior (overriding).
 */
public class GraduateStudent extends Student {

    private String thesisTitle;
    private String advisor;

    // Parameterized constructor
    public GraduateStudent(int id, String fullName, int age, char gender, String department,
                           String course, String thesisTitle, String advisor) {
        super(id, fullName, age, gender, department, course); // super() chaining
        this.thesisTitle = thesisTitle;
        this.advisor = advisor;
        setCredits((short) 32);
        setCgpa(8.8f);
        setPercentile(82.0);
        setGradeBand('A');
    }

    // Default constructor
    public GraduateStudent() {
        this(0, "Unknown Graduate", 22, 'O', "General", "Undeclared", "NA", "NA");
    }

    public String getThesisTitle() { return thesisTitle; }
    public void setThesisTitle(String thesisTitle) { this.thesisTitle = thesisTitle; }

    public String getAdvisor() { return advisor; }
    public void setAdvisor(String advisor) { this.advisor = advisor; }

    // Override dynamic dispatch method
    @Override
    public double calculateGrade() {
        double base = getCgpa() * 11.0;
        double thesisWeight = thesisTitle != null && !thesisTitle.isEmpty() ? 5.0 : 0.0;
        return base + thesisWeight;
    }

    @Override
    public String getLevel() {
        return "Graduate";
    }

    @Override
    public String getDisplayName() {
        return super.getDisplayName() + " | Thesis: " + thesisTitle;
    }

    @Override
    public String whoAmI() {
        return "I am a graduate student.";
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" [thesis='%s', advisor='%s']", thesisTitle, advisor);
    }
}

