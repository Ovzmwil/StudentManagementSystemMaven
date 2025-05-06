package me.lsantana.StudentManagementSystemMaven.services;import java.util.Collections;
import java.util.Comparator;
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
	
	public static void sortStudentList(List<Student> students){
		
		Collections.sort(students, new Comparator<Student>() {
			@Override
			public int compare(Student s1, Student s2) {
				return s1.getName().compareToIgnoreCase(s2.getName());
			}
		});
	}
}
