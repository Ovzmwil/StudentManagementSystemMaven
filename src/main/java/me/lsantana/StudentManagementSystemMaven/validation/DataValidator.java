package me.lsantana.StudentManagementSystemMaven.validation;

import java.util.List;

import me.lsantana.StudentManagementSystemMaven.domain.Student;
import me.lsantana.StudentManagementSystemMaven.exception.StudentNotFoundException;
import me.lsantana.StudentManagementSystemMaven.services.StudentService;

public class DataValidator {
	
	public static boolean isValidName(String name) {
		return name != null && name.matches("[a-zA-Z ]+");
	}

	public static boolean isValidAge(int age) {
		return age > 0 && age < 120;
	}

	public static boolean isValidId(List<Student> students, String id) {
		Student student = null;
		try {
			student = StudentService.findStudentById(students, id);
		} catch (StudentNotFoundException e) {

		}

		return id != null && id.matches("^[A-Za-z0-9]+$") && student == null;
	}

	public static boolean isValidNumberOfCourses(int numCourses) {
		return numCourses >= 0;
	}

}
