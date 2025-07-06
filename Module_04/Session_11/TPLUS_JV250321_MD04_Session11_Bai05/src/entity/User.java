package entity;

import validation.Validator;

import java.util.Scanner;

public class User {
	private String name;
	private String email;
	private String phone;

	public User () {
	}

	public User (String name, String email, String phone) {
		this.name = name;
		this.email = email;
		this.phone = phone;
	}

	public String getName () {
		return name;
	}

	public void setName (String name) {
		this.name = name;
	}

	public String getEmail () {
		return email;
	}

	public void setEmail (String email) {
		this.email = email;
	}

	public String getPhone () {
		return phone;
	}

	public void setPhone (String phone) {
		this.phone = phone;
	}

	public void displayUser () {
		System.out.printf("Name: %s - Email: %s - Phone: %s\n", this.name, this.email, this.phone);
	}

	public void inputData (Scanner scanner) {
		this.name = inputName(scanner);
		this.email = inputEmail(scanner);
		this.phone = inputPhone(scanner);
	}

	public String inputName (Scanner scanner) {
		System.out.print("Nhập vào tên: ");
		do {
			String name = scanner.nextLine();
			if (!Validator.isEmpty(name)) {
				return name;
			} else {
				System.err.println("Vui lòng không để trống!");
			}
		} while (true);
	}

	public String inputEmail (Scanner scanner) {
		System.out.print("Nhập vào email: ");
		do {
			String email = scanner.nextLine();
			if (!Validator.isEmpty(email)) {
				return email;
			} else {
				System.err.println("Vui lòng không để trống!");
			}
		} while (true);
	}

	public String inputPhone (Scanner scanner) {
		System.out.print("Nhập vào số điện thoại: ");
		do {
			String phone = scanner.nextLine();
			if (!Validator.isEmpty(phone)) {
				return phone;
			} else {
				System.err.println("Vui lòng không để trống!");
			}
		} while (true);
	}
}
