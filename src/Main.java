import model.Student;
import service.StudentService;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final StudentService service = new StudentService();
    private static final Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        displayWelcomeMessage();
        
        boolean running = true;
        while(running) {
            displayMenu();
            int choice = getMenuChoice();
            
            switch(choice) {
                case 1: 
                    addStudent(); 
                    break;
                case 2: 
                    deleteStudent(); 
                    break;
                case 3: 
                    searchStudents(); 
                    break;
                case 4: 
                    displayAllStudents(); 
                    break;
                case 5: 
                    running = false; 
                    break;
                default: 
                    System.out.println("Invalid option. Please try again.");
            }
        }
        
        System.out.println("\nThank you for using Student Management System!");
        scanner.close();
    }
    
    // Các phương thức khác giữ nguyên...
    private static void displayWelcomeMessage() {
        System.out.println("====================================");
        System.out.println("  STUDENT MANAGEMENT SYSTEM");
        System.out.println("====================================");
    }
    
    private static void displayMenu() {
        System.out.println("\nMAIN MENU");
        System.out.println("1. Add Student");
        System.out.println("2. Delete Student");
        System.out.println("3. Search Students");
        System.out.println("4. Display All Students");
        System.out.println("5. Exit");
        System.out.print("Enter your choice (1-5): ");
    }
    
    private static int getMenuChoice() {
        try {
            return scanner.nextInt();
        } catch(InputMismatchException e) {
            scanner.nextLine();
            return -1;
        } finally {
            scanner.nextLine();
        }
    }
    
    private static void addStudent() {
        System.out.println("\nADD NEW STUDENT");
        
        try {
            System.out.print("Enter Student ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            
            System.out.print("Enter Full Name: ");
            String name = scanner.nextLine();
            
            System.out.print("Enter GPA (0.0-4.0): ");
            double gpa = scanner.nextDouble();
            scanner.nextLine();
            
            Student student = new Student(id, name, gpa);
            service.addStudent(student);
            System.out.println("\nStudent added successfully!");
            System.out.println("Current student count: " + service.getStudentCount());
        } catch(InputMismatchException e) {
            System.out.println("Invalid input type. Please enter numbers for ID and GPA.");
            scanner.nextLine();
        } catch(IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static void deleteStudent() {
        System.out.println("\nDELETE STUDENT");
        System.out.print("Enter Student ID to delete: ");
        
        try {
            int id = scanner.nextInt();
            scanner.nextLine();
            
            if(service.deleteStudent(id)) {
                System.out.println("Student deleted successfully!");
                System.out.println("Current student count: " + service.getStudentCount());
            } else {
                System.out.println("Student not found with ID: " + id);
            }
        } catch(InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number for Student ID.");
            scanner.nextLine();
        }
    }
    
    private static void searchStudents() {
        System.out.println("\nSEARCH STUDENTS");
        System.out.print("Enter student name (or part of name): ");
        String name = scanner.nextLine();
        
        try {
            List<Student> results = service.searchStudents(name);
            if(results.isEmpty()) {
                System.out.println("No students found matching: " + name);
            } else {
                System.out.println("\nSEARCH RESULTS (" + results.size() + " found)");
                displayStudentTable(results);
            }
        } catch(IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static void displayAllStudents() {
        List<Student> students = service.getAllStudents();
        if(students.isEmpty()) {
            System.out.println("\nNo students in the system.");
        } else {
            System.out.printf("\nALL STUDENTS (%d total)\n", students.size());
            displayStudentTable(students);
        }
    }
    
    private static void displayStudentTable(List<Student> students) {
        System.out.println("--------------------------------------------------");
        System.out.printf("%-8s %-30s %s\n", "ID", "NAME", "GPA");
        System.out.println("--------------------------------------------------");
        for(Student s : students) {
            System.out.println(s);
        }
        System.out.println("--------------------------------------------------");
    }
}