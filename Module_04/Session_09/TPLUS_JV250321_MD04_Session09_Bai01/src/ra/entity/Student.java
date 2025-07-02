package ra.entity;

import java.util.Scanner;

public class Student {
	private String studentId;
	private String studentName;
	private int age;
	private String major;

	public Student () {
	}

	public Student (String studentId, String studentName, int age, String major) {
		this.studentId = studentId;
		this.studentName = studentName;
		this.age = age;
		this.major = major;
	}

	public String getStudentId () {
		return studentId;
	}

	public void setStudentId (String studentId) {
		this.studentId = studentId;
	}

	public String getStudentName () {
		return studentName;
	}

	public void setStudentName (String studentName) {
		this.studentName = studentName;
	}

	public int getAge () {
		return age;
	}

	public void setAge (int age) {
		this.age = age;
	}

	public String getMajor () {
		return major;
	}

	public void setMajor (String major) {
		this.major = major;
	}

	public void inputData (Scanner scanner) {
		this.studentId = inputStudentId(scanner);
		this.studentName = inputStudentName(scanner);
		this.age = inputAge(scanner);
		this.major = inputMajor(scanner);
	}

	public String inputStudentId (Scanner scanner) {
		System.out.print("Nhập mã sinh viên: ");
		String studentId = scanner.nextLine();
		return studentId;
	}

	public String inputStudentName (Scanner scanner) {
		System.out.print("Nhập tên sinh viên: ");
		String studentName = scanner.nextLine();
		return studentName;
	}

	public int inputAge (Scanner scanner) {
		System.out.print("Nhập vào tuổi sinh viên: ");
		int age = Integer.parseInt(scanner.nextLine());
		return age;
	}

	public String inputMajor (Scanner scanner) {
		System.out.print("Nhập chuyên ngành của sinh viên: ");
		String major = scanner.nextLine();
		return major;
	}

	public void displayData () {
		System.out.printf("Id: %s - Name: %s - Age: %d - Major: %s\n",
				this.studentId, this.studentName, this.age, this.major);
	}
}
