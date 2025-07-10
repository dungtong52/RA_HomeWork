package presentation;

import entry.Student;
import validator.Validation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentManager {
	public static List <Student> studentList = new ArrayList <>();
	private static final String URL = "jdbc:mysql://localhost:3306/student_db";
	private static final String USER = "root";
	private static final String PASSWORD = "root";

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);

		do {
			System.out.println("\n************ MENU ************");
			System.out.println(
					"1. Hiển thị danh sách sinh viên.\n" +
							"2. Thêm mới sinh viên.\n" +
							"3. Sửa sinh viên.\n" +
							"4. Xóa sinh viên.\n" +
							"5. Tìm kiếm sinh viên.\n" +
							"6. Thoát");
			System.out.print("Lựa chọn của bạn: ");
			String choice = scanner.nextLine();
			if (Validation.isIntegerInRange(choice, 1, 6)) {
				switch (Integer.parseInt(choice)) {
					case 1 -> displayAllStudent();
					case 2 -> addStudent(scanner);
					case 3 -> updateStudent(scanner);
					case 4 -> deleteStudent(scanner);
					case 5 -> searchStudentByName(scanner);
					default -> System.exit(0);
				}
			} else {
				System.err.println("Nhập số nguyên từ 1-6");
			}
		} while (true);
	}

	public static void displayAllStudent () {
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		     CallableStatement statement = connection.prepareCall("{CALL get_all_students()}")) {
			ResultSet resultSet = statement.executeQuery();

			studentList.clear();
			while (resultSet.next()) {
				Student student = new Student(
						resultSet.getInt("student_id"),
						resultSet.getString("full_name"),
						resultSet.getDate("date_of_birth").toLocalDate(),
						resultSet.getString("email")
				);
				studentList.add(student);
			}
			if (!studentList.isEmpty()) {
				System.out.println("Danh sách sinh viên:");
				studentList.forEach(System.out::println);
			} else {
				System.out.println("Không có sinh viên nào.");
			}
		} catch (SQLException e) {
			System.out.println("Lỗi hiển thị danh sách: " + e.getMessage());
		}
	}

	public static void addStudent (Scanner scanner) {
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		     CallableStatement statement = connection.prepareCall("{CALL add_student(?, ?, ?)}")) {
			Student newStudent = new Student();
			newStudent.inputData(scanner);

			statement.setString(1, newStudent.getStudentName());
			statement.setDate(2, java.sql.Date.valueOf(newStudent.getDob()));
			statement.setString(3, newStudent.getEmail());

			statement.execute();
			System.out.println("Thêm sinh viên thành công!");

		} catch (SQLException e) {
			System.out.println("Lỗi thêm sinh viên: " + e.getMessage());
		}
	}

	public static void updateStudent (Scanner scanner) {
		System.out.print("Nhập ID sinh viên cần cập nhật: ");
		String input = scanner.nextLine();
		if (Validation.isPositiveInteger(input)) {
			int studentIdUpdate = Integer.parseInt(input);
			Student existing = findStudentById(studentIdUpdate);

			if (existing != null) {
				Student updateStudent = new Student();
				updateStudent.inputData(scanner);

				try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				     CallableStatement statement = connection.prepareCall("{CALL update_student(?, ?, ?, ?)}")) {

					statement.setInt(1, studentIdUpdate);
					statement.setString(2, updateStudent.getStudentName());
					statement.setDate(3, java.sql.Date.valueOf(updateStudent.getDob()));
					statement.setString(4, updateStudent.getEmail());

					int rows = statement.executeUpdate();
					if (rows > 0) {
						existing.setStudentName(updateStudent.getStudentName());
						existing.setDob(updateStudent.getDob());
						existing.setEmail(updateStudent.getEmail());
						System.out.println("Cập nhật thành công");
					} else {
						System.err.println("Cập nhật thất bại!");
					}
				} catch (SQLException e) {
					System.out.println("Lỗi cập nhật sinh viên: " + e.getMessage());
				}
			} else {
				System.err.println("Mã sinh viên không tồn tại");
			}
		} else {
			System.err.println("Nhập vào số nguyên dương");
		}
	}

	public static void deleteStudent (Scanner scanner) {
		System.out.print("Nhập ID sinh viên cần cập nhật: ");
		String input = scanner.nextLine();
		if (Validation.isPositiveInteger(input)) {
			int studentIdDelete = Integer.parseInt(input);
			Student existing = findStudentById(studentIdDelete);

			if (existing != null) {

				try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				     CallableStatement statement = connection.prepareCall("{CALL delete_student(?)}")) {

					statement.setInt(1, studentIdDelete);

					int rows = statement.executeUpdate();
					if (rows > 0) {
						studentList.remove(existing);
						System.out.println("Xóa thành công");
					} else {
						System.err.println("Xóa thất bại!");
					}
				} catch (SQLException e) {
					System.out.println("Lỗi xóa sinh viên: " + e.getMessage());
				}
			} else {
				System.err.println("Mã sinh viên không tồn tại");
			}
		} else {
			System.err.println("Nhập vào số nguyên dương");
		}
	}

	public static void searchStudentByName (Scanner scanner) {
		System.out.print("Nhập vào tên sinh viên muốn tìm: ");
		String name = scanner.nextLine();
		if (!Validation.isEmpty(name)) {
			try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
			     CallableStatement statement = connection.prepareCall("{CALL search_student(?)}")) {

				statement.setString(1, name);
				ResultSet resultSet = statement.executeQuery();
				List <Student> searchStudentList = new ArrayList <>();

				while (resultSet.next()) {
					Student searchStudent = new Student(
							resultSet.getInt("student_id"),
							resultSet.getString("full_name"),
							resultSet.getDate("date_of_birth").toLocalDate(),
							resultSet.getString("email")
					);
					searchStudentList.add(searchStudent);
				}
				if (!searchStudentList.isEmpty()) {
					System.out.println("Sinh viên tìm thấy: ");
					searchStudentList.forEach(System.out::println);
				} else {
					System.out.println("Không có sinh viên nào.");
				}
			} catch (SQLException e) {
				System.out.println("Lỗi tìm kiếm sinh viên theo tên: " + e.getMessage());
			}

		} else {
			System.err.println("Không được để trống!");
		}
	}

	public static Student findStudentById (int id) {
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		     CallableStatement statement = connection.prepareCall("{CALL find_student_by_id(?)}")) {

			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				return new Student(
						resultSet.getInt("student_id"),
						resultSet.getString("full_name"),
						resultSet.getDate("date_of_birth").toLocalDate(),
						resultSet.getString("email")
				);
			}

		} catch (SQLException e) {
			System.out.println("Lỗi tìm sinh viên theo ID: " + e.getMessage());
		}
		return null;
	}

	public static boolean isEmailExist (String email) {
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		     CallableStatement statement = connection.prepareCall("{CALL check_email_exist(?)}")) {

			statement.setString(1, email);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				int count = resultSet.getInt("count_email");
				return count > 0;
			}

		} catch (SQLException e) {
			System.out.println("Lỗi kiểm tra email tồn tại: " + e.getMessage());
		}
		return false;
	}

}
