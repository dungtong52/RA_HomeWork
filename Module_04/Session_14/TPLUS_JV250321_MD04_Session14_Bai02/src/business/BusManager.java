package business;

import entity.Bus;
import validator.Validation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BusManager {
	private final Map <Integer, Bus> busMap = new HashMap <>();

	public void addBus (Scanner scanner) {
		Bus bus = new Bus();
		bus.inputData(scanner);
		busMap.put(bus.getId(), bus);
		System.out.println("Thêm xe thành công");
	}

	public void updateBus (Scanner scanner) {
	}

	public void deleteBus (Scanner scanner) {
	}

	public void displayBusMap () {
		if (busMap.isEmpty()) {
			System.out.println("Danh sách chưa có xe nào");
		} else {
			System.out.println("Danh sách xe:");
			busMap.entrySet().forEach(System.out::println);
		}
		int totalCapacity = busMap.values().stream()
				.mapToInt(item -> item.getCapacity()).sum();
		System.out.print("Tổng số ghế ngồi tất cả các xe: " + totalCapacity);
	}

	public void filterBusByDate (Scanner scanner) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		System.out.println("Nhập vào ngày muốn tìm:");
		String dateInput = scanner.nextLine();
		if (Validation.isValidDate(dateInput, "dd-MM-yyyy")) {
			LocalDateTime joinDate = LocalDateTime.parse(dateInput, formatter);
			System.out.printf("Danh sách xe hoạt động ngày %s\n", dateInput);
			busMap.values().stream()
					.filter(item -> item.getJoiningDate().toLocalDate().equals(joinDate))
					.forEach(System.out::println);
		} else {
			System.err.println("Nhập sai định dạng!");
		}
	}

	public void findBusByLicensePlate (Scanner scanner) {
		System.out.println("Nhập vào biển số xe muốn tìm:");
		String licensePlate = scanner.nextLine();
		if (!Validation.isEmpty(licensePlate)) {
			List <Bus> busSearch = busMap.values().stream()
					.filter(item -> item.getLicensePlate().equals(licensePlate))
					.toList();
			if (!busSearch.isEmpty()) {
				busSearch.forEach(System.out::println);
			} else {
				System.out.printf("Không tồn tại biển số xe %s\n", licensePlate);
			}
		} else {
			System.err.println("Không được để trống!");
		}
	}

	public boolean isIdExists (int id) {
		return busMap.keySet().stream().anyMatch(item -> item == id);
	}
}
