package me.lsantana.StudentManagementSystemMaven.services;

import java.util.List;

import me.lsantana.StudentManagementSystemMaven.domain.Student;
import me.lsantana.StudentManagementSystemMaven.exception.StudentNotFoundException;


public class StudentService {

	public static Student findStudentById(List<Student> students, String studentId) {
		for (Student student : students) {
			if (student.getStudentId().equals(studentId)) {
				return student;
			}
		}
		throw new StudentNotFoundException("Student with ID '" + studentId + "' not found.");
	}
}
