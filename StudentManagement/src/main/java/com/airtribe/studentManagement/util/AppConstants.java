package main.java.com.airtribe.studentManagement.util;

/**
 * Final variables, static variables usage.
 */
public final class AppConstants { // final class example

    public static final String APP_TITLE = """
            ==============================
             AirTribe Student Management
            ==============================
            """;

    public static final String VERSION = "1.0.0";

    // final method example
    public final String about() {
        return "Student Management Console " + VERSION;
    }
}
