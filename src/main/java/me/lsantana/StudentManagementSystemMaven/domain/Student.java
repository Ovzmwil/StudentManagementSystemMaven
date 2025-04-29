package me.lsantana.StudentManagementSystemMaven.domain;

import java.util.ArrayList;
import java.util.List;

public class Student {
	private String name;
	private int age;
	private String studentId;
	private List<String> courses;

	public Student(String name, int age, String studentId) {
		super();
		this.name = name;
		this.age = age;
		this.studentId = studentId;
		this.courses = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public List<String> getCourses() {
		return courses;
	}

	public void setCourses(List<String> courses) {
		this.courses = courses;
	}

	public void enrollCourse(String course) {
		if (courses.contains(course)) {
			System.out.println("Course already added.");
		} else {
			courses.add(course);
		}

	}

	public void printStudentInfo() {
		System.out.println("Name: " + name);
		System.out.println("Age: " + age);
		System.out.println("Student ID: " + studentId);
		System.out.println("Enrolled Courses: " + courses);
	}

}
