package entity;

public class Student {
	private String name;
	private int age;
	private double grade;

	public Student () {
	}

	public Student (String name, int age, double grade) {
		this.name = name;
		this.age = age;
		this.grade = grade;
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

	public double getGrade () {
		return grade;
	}

	public void setGrade (double grade) {
		this.grade = grade;
	}

	public void displayData () {
		System.out.printf("|  %s %10s  |  %s  %2d  |  %s  %.1f  |\n",
				"name:", this.name, "age:", this.age, "grade:", this.grade);
	}
}

