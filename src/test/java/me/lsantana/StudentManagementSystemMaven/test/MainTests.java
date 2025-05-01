package me.lsantana.StudentManagementSystemMaven.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;

import me.lsantana.StudentManagementSystemMaven.Main;
import me.lsantana.StudentManagementSystemMaven.domain.Student;
import me.lsantana.StudentManagementSystemMaven.exception.StudentNotFoundException;
import me.lsantana.StudentManagementSystemMaven.services.StudentService;

public class MainTests {
	
    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;
	
	@BeforeEach
	public void setUpStreams() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
	}
	
    @AfterEach
    public void restoreStreams() {
        System.setIn(System.in);
        System.setOut(System.out);
    }
    
    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    private String getOutput() {
        return testOut.toString();
    }
    
    @Test
    public void testFindStudentById_ValidId() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Leandro Santana", 27, "U1234"));

        Student result = StudentService.findStudentById(students, "U1234");
        assertNotNull(result);
        assertEquals("Leandro Santana", result.getName());
        assertEquals(27, result.getAge());
        assertEquals("U1234", result.getStudentId());
    }
    
    @Test
    public void testFindStudentById_InvalidId() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("John Doe", 20, "S1234"));

        Exception exception = assertThrows(StudentNotFoundException.class, () -> {
        	StudentService.findStudentById(students, "S9999");
        });

        assertEquals("Student with ID 'S9999' not found.", exception.getMessage());
    }
    
    @Test
    public void testMain_AddNewStudent() {
        String input = "2\nJane Doe\n25\nS5678\n2\nMath\nScience\n4\n";
        provideInput(input);

        Main.main(new String[]{});

        String output = getOutput();
        assertTrue(output.contains("Enter student name:"));
        assertTrue(output.contains("Enter student age:"));
        assertTrue(output.contains("Enter student ID:"));
        assertTrue(output.contains("How many courses to enroll for Jane Doe?"));
        assertTrue(output.contains("Student added successfully!"));
    }
    
    @Test
    public void testMain_AddNewStudent_EmptyName() {
        String input = "2\n\nJane Doe\n25\nS5678\n2\nMath\nScience\n4\n";
        provideInput(input);

        Main.main(new String[]{});

        String output = getOutput();
        assertTrue(output.contains("Invalid name! Please, enter a valid name."));
    }
    
    @Test
    public void testMain_AddNewStudent_InvalidName() {
        String input = "2\n12345\nJane Doe\n25\nS5678\n2\nMath\nScience\n4\n";
        provideInput(input);

        Main.main(new String[]{});

        String output = getOutput();
        assertTrue(output.contains("Invalid name! Please, enter a valid name."));
    }
    
    @Test
    public void testMain_AddNewStudent_InvalidAge() {
        String input = "2\nJane Doe\n-5\n25\nS5678\n2\nMath\nScience\n4\n";
        provideInput(input);

        Main.main(new String[]{});

        String output = getOutput();
        assertTrue(output.contains("Invalid age! Please, enter a valid age."));
    }
    
    @Test
    public void testMain_AddNewStudent_InvalidNumberOfCourses() {
        String input = "2\nJane Doe\n25\nS5678\n-2\n2\nMath\nScience\n4\n";
        provideInput(input);

        Main.main(new String[]{});

        String output = getOutput();
        assertTrue(output.contains("Invalid number of courses! Please, enter a valid number."));
    }
    
    @Test
    public void testMain_AddNewStudent_RepeatedId() {
        String input = "2\nJane Doe\n25\nU1111\nU4444\n2\nMath\nScience\n4\n";
        provideInput(input);

        Main.main(new String[]{});

        String output = getOutput();
        assertTrue(output.contains("Invalid ID or ID already exists! Please, enter a valid ID."));
    }
    
    @Test
    public void testMain_AddNewStudent_InvalidId() {
        String input = "2\nJane Doe\n25\n@\nU4444\n2\nMath\nScience\n4\n";
        provideInput(input);

        Main.main(new String[]{});

        String output = getOutput();
        assertTrue(output.contains("Invalid ID or ID already exists! Please, enter a valid ID."));
    }
    
    @Test
    public void testMain_SearchStudent() {
        String input = "1\nU1111\n4\n";
        provideInput(input);

        Main.main(new String[]{});

        String output = getOutput();
        assertTrue(output.contains("Enter student ID to search:"));
        assertTrue(output.contains("Leandro Santana"));
    }
    
    @Test
    public void testMain_SearchStudent_NotFound() {
        String input = "1\nU4444\n4\n";
        provideInput(input);

        Main.main(new String[]{});

        String output = getOutput();
        assertTrue(output.contains("Student with ID 'U4444' not found."));
    }
    
    @Test
    public void testMain_InvalidMenuOption() {
        String input = "5\n4\n";
        provideInput(input);

        Main.main(new String[]{});

        String output = getOutput();
        assertTrue(output.contains("Invalid option! Please, enter a valid option."));
    }
    
    @Test
    public void testMain_GetAllStudents() {
        String input = "3\n4\n";
        provideInput(input);

        Main.main(new String[]{});

        String output = getOutput();
        assertTrue(output.contains("Leandro Santana"));
        assertTrue(output.contains("Nathalia Carrer"));
        assertTrue(output.contains("Leticia Rodrigues"));
    }
    
    @Test
    public void testMain_ExitProgram() {
        String input = "4\n";
        provideInput(input);

        Main.main(new String[]{});

        String output = getOutput();
        assertTrue(output.contains("Exiting the program..."));
    }
    
    @Test
    public void testMain_MissMatchException() {
    	String input = "2\nJohn Doe\ntwenty\n4\n";
    	provideInput(input);
    	
    	Main.main(new String[]{});
    	
    	String output = getOutput();
    	assertTrue(output.contains("Invalid parameter! Please, try again."));
    }
    

}