package business;

import entity.Bus;
import validator.Validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class BusManager {
    private final Map<Integer, Bus> busMap = new HashMap<>();

    public void addBus(Scanner scanner) {
        Bus bus = new Bus();
        bus.inputData(scanner, this);
        busMap.put(bus.getId(), bus);
        System.out.println("Thêm xe thành công");
    }

    public void updateBus(Scanner scanner) {
        System.out.println("Nhập vào mã xe khách muốn cập nhật:");
        String idInput = scanner.nextLine();
        if (Validation.isPositiveInteger(idInput)) {
            int idUpdate = Integer.parseInt(idInput);
            Optional<Map.Entry<Integer, Bus>> busSearch = busMap.entrySet().stream().filter(item -> item.getKey() == idUpdate).findFirst();
            busSearch.ifPresentOrElse(busEntry -> {
                boolean exit = false;
                do {
                    System.out.println("1. Cập nhật biển số xe");
                    System.out.println("2. Cập nhật số ghế ngồi");
                    System.out.println("3. Cập nhật ngày bắt đầu");
                    System.out.println("4.Thoát cập nhật");
                    System.out.print("Lựa chọn của bạn: ");
                    String choiceInput = scanner.nextLine();
                    if (Validation.isIntegerInRange(choiceInput, 1, 4)) {
                        int choice = Integer.parseInt(choiceInput);
                        Bus bus = busEntry.getValue();
                        switch (choice) {
                            case 1 -> bus.setLicensePlate(bus.inputLicensePlate(scanner, this));
                            case 2 -> bus.setCapacity(bus.inputCapacity(scanner));
                            case 3 -> bus.setJoiningDate(bus.inputJoiningDate(scanner));
                            default -> exit = true;
                        }
                    } else {
                        System.err.println("Nhập số nguyên từ 1-4");
                    }
                } while (!exit);
            }, () -> System.err.println("Không tồn tại mã xe khách này!"));
        } else {
            System.err.println("Vui lòng nhập mã xe khách hợp lệ!");
        }
    }

    public void deleteBus(Scanner scanner) {
        System.out.println("Nhập vào mã xe khách muốn xóa:");
        String idInput = scanner.nextLine();
        if (Validation.isPositiveInteger(idInput)) {
            int idDelete = Integer.parseInt(idInput);
            Optional<Map.Entry<Integer, Bus>> busSearch = busMap.entrySet().stream().filter(item -> item.getKey() == idDelete).findFirst();
            busSearch.ifPresentOrElse(busEntry -> {
                busMap.entrySet().remove(busEntry);
            }, () -> System.err.println("Không tồn tại mã xe khách này!"));
        } else {
            System.err.println("Vui lòng nhập mã xe khách hợp lệ!");
        }
    }

    public void displayBusMap() {
        if (busMap.isEmpty()) {
            System.out.println("Danh sách chưa có xe nào");
        } else {
            System.out.println("Danh sách xe:");
            busMap.entrySet().forEach(System.out::println);
        }
        int totalCapacity = busMap.values().stream().mapToInt(Bus::getCapacity).sum();
        System.out.print("Tổng số ghế ngồi tất cả các xe: " + totalCapacity);
    }

    public void filterBusByDate(Scanner scanner) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        System.out.println("Nhập vào ngày muốn tìm:");
        String dateInput = scanner.nextLine();
        if (Validation.isValidDate(dateInput, "dd-MM-yyyy")) {
            LocalDate joinDate = LocalDate.parse(dateInput, formatter);
            System.out.printf("Danh sách xe hoạt động ngày %s\n", dateInput);
            busMap.values().stream().filter(item -> item.getJoiningDate().toLocalDate().equals(joinDate)).forEach(System.out::println);
        } else {
            System.err.println("Nhập sai định dạng!");
        }
    }

    public void findBusByLicensePlate(Scanner scanner) {
        System.out.println("Nhập vào biển số xe muốn tìm:");
        String licensePlate = scanner.nextLine();
        if (!Validation.isEmpty(licensePlate)) {
            List<Bus> busSearch = busMap.values().stream().filter(item -> item.getLicensePlate().equals(licensePlate)).toList();
            if (!busSearch.isEmpty()) {
                busSearch.forEach(System.out::println);
            } else {
                System.out.printf("Không tồn tại biển số xe %s\n", licensePlate);
            }
        } else {
            System.err.println("Không được để trống!");
        }
    }

    public boolean isIdExists(int id) {
        return busMap.keySet().stream().anyMatch(item -> item == id);
    }

    public boolean isPlateExists(String plate) {
        return busMap.values().stream().anyMatch(item -> item.getLicensePlate().equals(plate));
    }
}
