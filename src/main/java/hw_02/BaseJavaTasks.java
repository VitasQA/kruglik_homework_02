package hw_02;

public class BaseJavaTasks {

    public boolean isEven(int n) {
        return n % 2 == 0;
    }

    public String checkAccess(int age) {
        if (age >= 18) {
            return "Allowed";
        } else {
            return "Denied";
        }
    }

    public String getGrade(int score) {
        if (score < 0 || score > 100) return "Invalid";
        if (score >= 90) return "A";
        if (score >= 80) return "B";
        if (score >= 70) return "C";
        if (score >= 60) return "D";
        return "F";
    }
}