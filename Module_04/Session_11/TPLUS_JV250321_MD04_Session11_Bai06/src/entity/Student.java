package entity;

import java.util.Scanner;

public class Student {
	private int id;
	private String name;

	public Student () {
	}

	public Student (int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId () {
		return id;
	}

	public void setId (int id) {
		this.id = id;
	}

	public String getName () {
		return name;
	}

	public void setName (String name) {
		this.name = name;
	}

	public void displayData () {
		System.out.printf("ID: %d - Name: %s\n", this.id, this.name);
	}

	public void inputData (Scanner scanner) {
		this.id = inputId();
		this.name = inputName(scanner);
	}

	public int inputId () {
		AttendanceManager manager = new AttendanceManager();
		int maxId = 0;
		for (Student student : manager.studentList) {
			if (student.getId() > maxId) {
				maxId = student.getId();
			}
		}
		return maxId + 1;
	}

	public String inputName (Scanner scanner) {
		System.out.println("Nhập tên sinh viên: ");
		String name = scanner.nextLine();
		return name;
	}
}
