package presentation;

import entity.Developer;
import entity.Employee;
import entity.Manager;

public class Main {
	public static void main (String[] args) {
		Employee employee = new Employee("Hùng", "A01", 15000000);
		Manager manager = new Manager("Bình", "M01", 30000000, 10000000);
		Developer developer = new Developer("Dũng", "D01", 20000000, "Java");

		System.out.printf("Lương của nhân viên: %,.1f\n", employee.getSalary());
		System.out.printf("Lương của giám đốc: %,.1f\n", manager.getSalary());
		System.out.printf("Lương của Dev: %,.1f\n", developer.getSalary());
		System.out.println("---------------");

		System.out.println("Nhân viên:\n" + employee.toString());
		System.out.println("Giám đốc:\n" + manager.toString());
		System.out.println("Lập trình viên:\n" + developer.toString());
		System.out.println("---------------");

		System.out.println("Tăng lương cho lập trình viên");
		developer.increaseSalary(15000000);
		System.out.printf("Lương của Dev: %,.1f\n", developer.getSalary());
		System.out.println("Lập trình viên:\n" + developer.toString());
	}
}
