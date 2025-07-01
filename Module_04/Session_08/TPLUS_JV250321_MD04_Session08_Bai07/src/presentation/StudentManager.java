package presentation;

import entity.Student;

import java.util.Scanner;

public class StudentManager {
	public static Student[] students = new Student[100];
	public static int currentIndex = 0;

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		do {
			System.out.println("\n----- MENU ------");
			System.out.println("1. Add Student");
			System.out.println("2. Show All Student");
			System.out.println("3. Find Student by Name");
			System.out.println("4. Exit");
			System.out.print("Enter your choice: ");
			int choice = Integer.parseInt(scanner.nextLine());

			switch (choice) {
				case 1:
					// Add Student
					addStudent(scanner);
					break;
				case 2:
					// Show All Student
					showAllStudent();
					break;
				case 3:
					// Find Student by Name
					findStudentByName(scanner);
					break;
				case 4:
					System.exit(0);
				default:
					System.err.println("Please enter a number between 1-4");
			}
		} while (true);
	}

	public static void addStudent (Scanner scanner) {
		students[currentIndex] = new Student();
		students[currentIndex].inputData(scanner);
		currentIndex++;
		System.out.println("Add student success!");
	}

	public static void showAllStudent () {
		System.out.println("----- List Student -----");
		for (int i = 0; i < currentIndex; i++) {
			students[i].displayDataStudent();
		}
	}

	public static void findStudentByName (Scanner scanner) {
		System.out.print("Enter student's name to find: ");
		String nameInputForFind = scanner.nextLine();

		boolean isExists = false;
		for (int i = 0; i < currentIndex; i++) {
			if (students[i].getName().toLowerCase().equals(nameInputForFind.toLowerCase())) {
				students[i].displayDataStudent();
				isExists = true;
			}
		}
		if (!isExists) {
			System.out.println("Student not found");
		}
	}

}
