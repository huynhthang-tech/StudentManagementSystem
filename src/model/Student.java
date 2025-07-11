package model;

public class Student {
    private int studentId;
    private String fullName;
    private double gpa;
    
    public Student(int studentId, String fullName, double gpa) {
        setStudentId(studentId);
        setFullName(fullName);
        setGpa(gpa);
    }
    
    // Getters
    public int getStudentId() { return studentId; }
    public String getFullName() { return fullName; }
    public double getGpa() { return gpa; }
    
    // Setters with validation
    public void setStudentId(int studentId) {
        if(studentId <= 0) {
            throw new IllegalArgumentException("Student ID must be positive");
        }
        this.studentId = studentId;
    }
    
    public void setFullName(String fullName) {
        if(fullName == null || fullName.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if(fullName.length() > 50) {
            throw new IllegalArgumentException("Name must be 50 characters or less");
        }
        this.fullName = fullName.trim();
    }
    
    public void setGpa(double gpa) {
        if(gpa < 0.0 || gpa > 4.0) {
            throw new IllegalArgumentException("GPA must be between 0.0 and 4.0");
        }
        this.gpa = gpa;
    }
    
    @Override
    public String toString() {
        return String.format("%-8d %-30s %.2f", studentId, fullName, gpa);
    }
}