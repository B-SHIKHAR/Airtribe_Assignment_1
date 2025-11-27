package main.java.com.airtribe.studentManagement.contract;

public interface Identifiable {
    int getId();
    String getDisplayName();

    default String summary() {
        return getClass().getSimpleName() + "(" + getId() + "): " + getDisplayName();
    }
}
