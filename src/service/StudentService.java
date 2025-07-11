package service;

import model.Student;
import java.util.ArrayList;
import java.util.List;

public class StudentService {
    private final List<Student> students = new ArrayList<>();
    private static final int MAX_STUDENTS = 100;
    
    public void addStudent(Student student) throws IllegalStateException {
        if(students.size() >= MAX_STUDENTS) {
            throw new IllegalStateException("Maximum number of students reached");
        }
        
        if(student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }
        
        if(students.stream().anyMatch(s -> s.getStudentId() == student.getStudentId())) {
            throw new IllegalArgumentException("Student ID already exists");
        }
        
        students.add(student);
    }
    
    public boolean deleteStudent(int studentId) {
        return students.removeIf(s -> s.getStudentId() == studentId);
    }
    
    public List<Student> searchStudents(String name) {
        if(name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Search name cannot be empty");
        }
        
        String searchTerm = name.trim().toLowerCase();
        List<Student> result = new ArrayList<>();
        
        for(Student s : students) {
            if(s.getFullName().toLowerCase().contains(searchTerm)) {
                result.add(s);
            }
        }
        return result;
    }
    
    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }
    
    public int getStudentCount() {
        return students.size();
    }
}