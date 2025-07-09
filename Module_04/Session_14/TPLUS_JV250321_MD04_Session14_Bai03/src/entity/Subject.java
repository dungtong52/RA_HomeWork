package entity;

import business.SubjectManager;
import validator.Validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Subject {
	private int code;
	private String name;
	private int credits;
	private LocalDate startDate;

	public Subject () {
	}

	public Subject (int code, String name, int credits, LocalDate startDate) {
		this.code = code;
		this.name = name;
		this.credits = credits;
		this.startDate = startDate;
	}

	public int getCode () {
		return code;
	}

	public void setCode (int code) {
		this.code = code;
	}

	public String getName () {
		return name;
	}

	public void setName (String name) {
		this.name = name;
	}

	public int getCredits () {
		return credits;
	}

	public void setCredits (int credits) {
		this.credits = credits;
	}

	public LocalDate getStartDate () {
		return startDate;
	}

	public void setStartDate (LocalDate startDate) {
		this.startDate = startDate;
	}

	public void inputData (Scanner scanner, SubjectManager <? extends Subject> subjectManager) {
		this.code = inputCode(scanner, subjectManager);
		this.name = inputSubjectName(scanner);
		this.credits = inputCredits(scanner);
		this.startDate = inputStartDate(scanner);
	}

	public int inputCode (Scanner scanner, SubjectManager <? extends Subject> subjectManager) {
		do {
			System.out.println("Nhập vào mã môn học:");
			String codeInput = scanner.nextLine();
			if (Validation.isPositiveInteger(codeInput)) {
				int code = Integer.parseInt(codeInput);
				if (!subjectManager.isIdExists(code)) {
					return code;
				} else {
					System.err.println("Mã môn học này đã tồn tại!");
				}
			} else {
				System.err.println("Sai định dạng mã môn học!");
			}
		} while (true);
	}

	public String inputSubjectName (Scanner scanner) {
		do {
			System.out.println("Nhập vào tên môn học:");
			String name = scanner.nextLine();
			if (!Validation.isEmpty(name)) {
				return name;
			} else {
				System.err.println("Vui lòng không để trống!");
			}
		} while (true);
	}

	public int inputCredits (Scanner scanner) {
		do {
			System.out.println("Nhập vào tín chỉ môn học:");
			String credits = scanner.nextLine();
			if (Validation.isPositiveInteger(credits)) {
				return Integer.parseInt(credits);
			} else {
				System.err.println("Vui lòng nhập vào số nguyên dương");
			}
		} while (true);
	}

	public LocalDate inputStartDate (Scanner scanner) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		do {
			System.out.println("Nhập vào ngày bắt đầu:");
			String dateInput = scanner.nextLine();
			if (Validation.isValidDate(dateInput, "dd-MM-yyyy")) {
				return LocalDate.parse(dateInput, formatter);
			} else {
				System.err.println("Nhập sai định dạng ngày tháng năm!");
			}
		} while (true);
	}

	@Override
	public String toString () {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		return String.format("Code: %d - Subject Name: %s - Credits: %d - Start: %s",
				this.code, this.name, this.credits, formatter.format(this.startDate));
	}
}
