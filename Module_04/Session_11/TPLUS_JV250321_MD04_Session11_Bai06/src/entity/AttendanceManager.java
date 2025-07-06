package entity;

import java.util.ArrayList;
import java.util.List;

public class AttendanceManager implements Manage <Student> {
	public List <Student> studentList = new ArrayList <>();

	@Override
	public void add (Student student) {
		studentList.add(student);
		System.out.println("Thêm sinh viên thành công!");
	}

	@Override
	public void update (int index, Student student) {
		display();
		if (index != -1) {
			studentList.get(index).setName(student.getName());
			System.out.println("Cập nhật thành công!");
		} else {
			System.out.println("Không tìm thấy sinh viên này");
		}
	}

	@Override
	public void delete (int index) {
		if (index != -1) {
			studentList.remove(index);
			System.out.println("Xóa thành công!");
		} else {
			System.out.println("Không tìm thấy sinh viên này");
		}
	}

	@Override
	public void display () {
		System.out.println("Danh sách sinh viên");
		for (Student student : studentList) {
			student.displayData();
		}
	}

	public int findIndexById (int id) {
		for (int i = 0; i < studentList.size(); i++) {
			if (studentList.get(i).getId() == id) {
				return i;
			}
		}
		return -1;
	}
}
