package entity;

import java.util.Scanner;

public class Student {
	private String name;
	private int age;
	private double gpa;

	public Student () {
	}

	public Student (String name, int age, double gpa) {
		this.name = name;
		this.age = age;
		this.gpa = gpa;
	}

	public String getName () {
		return name;
	}

	public void setName (String name) {
		this.name = name;
	}

	public int getAge () {
		return age;
	}

	public void setAge (int age) {
		this.age = age;
	}

	public double getGpa () {
		return gpa;
	}

	public void setGpa (double gpa) {
		this.gpa = gpa;
	}

	public void displayDataStudent () {
		System.out.printf("Name: %5s, Age: %2d, GPA: %3.1f\n", getName(), getAge(), getGpa());
	}

	public void inputData (Scanner scanner) {
		System.out.print("Enter student's name: ");
		String nameInput = scanner.nextLine();
		setName(nameInput);

		System.out.print("Enter student's age: ");
		int ageInput = Integer.parseInt(scanner.nextLine());
		setAge(ageInput);

		System.out.print("Enter student's gpa: ");
		double gpaInput = Double.parseDouble(scanner.nextLine());
		setGpa(gpaInput);
	}
}
