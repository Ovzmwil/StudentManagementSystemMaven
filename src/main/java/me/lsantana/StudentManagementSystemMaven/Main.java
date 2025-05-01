package me.lsantana.StudentManagementSystemMaven;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import me.lsantana.StudentManagementSystemMaven.domain.Student;
import me.lsantana.StudentManagementSystemMaven.exception.StudentNotFoundException;
import me.lsantana.StudentManagementSystemMaven.services.StudentService;
import me.lsantana.StudentManagementSystemMaven.validation.DataValidator;

public class Main {
	
	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		List<Student> students = new ArrayList<>();
		List<String> courses = new ArrayList<>();
		int coursesPerStudent = 23;
		boolean exit = false;

		//Initializing three students and add them to a list
		Student student1 = new Student("Leandro Santana", 27, "U1111");
		Student student2 = new Student("Nathalia Carrer", 37, "U2222");
		Student student3 = new Student("Leticia Rodrigues", 12, "U3333");
		students.add(student1);
		students.add(student2);
		students.add(student3);

		//Populating a list with 100 generic random courses
		for (int i = 1; i <= 100; i++) {
			courses.add("course " + i);
		}

		//For each student in the list, shuffle the courses list and add the first 23 courses to the student
		for (Student student : students) {
			Collections.shuffle(courses);
			List<String> first23courses = new ArrayList<>(courses.subList(0, coursesPerStudent));
			for (String course : first23courses) {
				student.enrollCourse(course);
			}
		}

		//Sorting the student list by name
		Collections.sort(students, new Comparator<Student>() {
			@Override
			public int compare(Student s1, Student s2) {
				return s1.getName().compareToIgnoreCase(s2.getName());
			}
		});

		//Creating a basic "MENU" for user to interact with application
		while(!exit) {
			System.out.println("\nMENU");
			System.out.println("1. Search for a student");
			System.out.println("2. Add a new student");
			System.out.println("3. Get all students");
			System.out.println("4. Exit");

			try {
				int choice = scanner.nextInt();
				scanner.nextLine();

				switch(choice) {
				case 1:
					System.out.print("Enter student ID to search: ");
					String searchId = scanner.nextLine();
					try {
						Student foundStudent = StudentService.findStudentById(students, searchId);
						foundStudent.printStudentInfo();
					} catch (StudentNotFoundException e) {
						System.out.println(e.getMessage());
					}
					break;
				case 2:
					System.out.println();
					System.out.print("Enter student name: ");
					String name = scanner.nextLine();

					while (!DataValidator.isValidName(name)) {
						System.out.println("Invalid name! Please, enter a valid name.");
						name = scanner.nextLine();
					}

					System.out.print("Enter student age: ");
					int age = scanner.nextInt();

					while (!DataValidator.isValidAge(age)) {
						System.out.println("Invalid age! Please, enter a valid age.");
						age = scanner.nextInt();
					}

					scanner.nextLine();
					System.out.print("Enter student ID: ");
					String studentId = scanner.nextLine();

					while (!DataValidator.isValidId(students, studentId)) {
						System.out.println("Invalid ID or ID already exists! Please, enter a valid ID.");
						studentId = scanner.nextLine();
					}

					Student newStudent = new Student(name, age, studentId);
					students.add(newStudent);

					System.out.print("How many courses to enroll for " + name + "? ");
					int numCourses = scanner.nextInt();
					scanner.nextLine();

					while(!DataValidator.isValidNumberOfCourses(numCourses)) {
						System.out.println("Invalid number of courses! Please, enter a valid number.");
						numCourses = scanner.nextInt();
					}

					for (int i = 0; i < numCourses; i++) {
						System.out.print("Enter course name: ");
						String course = scanner.nextLine();
						newStudent.enrollCourse(course);
					}
					System.out.println("Student added successfully!");
					break;
				case 3:
					for (Student student : students) {
						System.out.println();
						student.printStudentInfo();
					}
					break;
				case 4:
					System.out.println("Exiting the program...");
					exit = true;
					scanner.close();
					break;
				default:
					System.out.println("Invalid option! Please, enter a valid option.");
					break;
				}
			} catch (InputMismatchException e) {
				scanner.nextLine();
				System.out.println("Invalid parameter! Please, try again.");
			}
		}
	}

}
