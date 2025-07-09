package business;

import entity.Subject;
import validator.Validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SubjectManager<T extends Subject> {
	private final List <T> subjectList = new ArrayList <>();

	public void addSubject (Scanner scanner) {
		T newSubject = (T) new Subject();
		newSubject.inputData(scanner, this);
		subjectList.add(newSubject);
		System.out.println("Thêm môn học thành công");
	}

	public void deleteSubject (Scanner scanner) {
		System.out.println("Nhập ID cần xóa:");
		String idInput = scanner.nextLine();
		if (Validation.isPositiveInteger(idInput)) {
			int idDelete = Integer.parseInt(idInput);
			subjectList.stream()
					.filter(item -> item.getCode() == idDelete).findFirst()
					.ifPresentOrElse(subject -> {
						System.out.printf("Bạn có chắc chắn muốn xóa môn học có ID %d ('Y')\\n", idDelete);
						if (scanner.nextLine().equalsIgnoreCase("Y")) {
							subjectList.remove(subject);
						}
					}, () -> System.err.printf("Không tìm thấy ID %d của môn học\n", idDelete));
		} else {
			System.err.println("ID nhập vào không đúng định dạng!");
		}
	}

	public void displaySubjectList () {
		System.out.println("Danh sách môn học:");
		subjectList.forEach(System.out::println);
	}

	public void findSubjectByName (Scanner scanner) {
		System.out.println("Nhập tên môn học muốn tìm");
		String nameSearch = scanner.nextLine();
		if (!Validation.isEmpty(nameSearch)) {
			List <T> subjectSearchList = subjectList.stream()
					.filter(item -> item.getName().toLowerCase().contains(nameSearch.toLowerCase()))
					.toList();
			if (!subjectSearchList.isEmpty()) {
				subjectSearchList.forEach(System.out::println);
			} else {
				System.out.println("Không tìm thấy môn học này");
			}
		} else {
			System.err.println("Vui lòng không để trống!");
		}
	}

	public void filterSubjectByCredit () {
		final int CREDIT_FILTER = 3;
		List <T> subjectSearchList = subjectList.stream()
				.filter(item -> item.getCredits() > CREDIT_FILTER).toList();
		if (!subjectSearchList.isEmpty()) {
			subjectSearchList.forEach(System.out::println);
		} else {
			System.err.println("Không tìm thấy môn học nào");
		}
	}

	public boolean isIdExists (int id) {
		return subjectList.stream()
				.anyMatch(subject -> subject.getCode() == id);
	}
}
