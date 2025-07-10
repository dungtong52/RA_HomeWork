package entity;

import business.BusManager;
import validator.Validation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Bus {
    private int id;
    private String licensePlate;
    private int capacity;
    private LocalDateTime joiningDate;

    public Bus() {
    }

    public Bus(int id, String licensePlate, int capacity, LocalDateTime joiningDate) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.capacity = capacity;
        this.joiningDate = joiningDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public LocalDateTime getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDateTime joiningDate) {
        this.joiningDate = joiningDate;
    }

    public void inputData(Scanner scanner, BusManager busManager) {
        this.id = inputId(scanner, busManager);
        this.licensePlate = inputLicensePlate(scanner, busManager);
        this.capacity = inputCapacity(scanner);
        this.joiningDate = inputJoiningDate(scanner);
    }

    public int inputId(Scanner scanner, BusManager busManager) {
        do {
            System.out.println("Nhập mã xe khách:");
            String idInput = scanner.nextLine();
            if (Validation.isPositiveInteger(idInput)) {
                int id = Integer.parseInt(idInput);
                if (!busManager.isIdExists(id)) {
                    return id;
                } else {
                    System.err.println("Mã xe đã tồn tại, vui lòng nhập mã khác!");
                }
            } else {
                System.err.println("Vui lòng nhập vào số nguyên dương");
            }
        } while (true);
    }

    public String inputLicensePlate(Scanner scanner, BusManager busManager) {
        do {
            System.out.println("Nhập vào biển số xe:");
            String plate = scanner.nextLine();
            if (!Validation.isEmpty(plate)) {
                if (!busManager.isPlateExists(plate)) {
                    return plate;
                } else {
                    System.err.println("Biển số xe đã tồn tại!");
                }
            } else {
                System.err.println("Vui lòng nhập vào biển số xe đúng định dạng!");
            }
        } while (true);
    }

    public int inputCapacity(Scanner scanner) {
        do {
            System.out.println("Nhập vào số ghế ngồi của xe:");
            String capacity = scanner.nextLine();
            if (Validation.isPositiveInteger(capacity)) {
                return Integer.parseInt(capacity);
            } else {
                System.err.println("Vui lòng nhập vào đúng định dạng!");
            }
        } while (true);
    }

    public LocalDateTime inputJoiningDate(Scanner scanner) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        do {
            System.out.println("Nhập vào ngày bắt đầu (dd-MM-yyyy HH:mm):");
            String joiningDate = scanner.nextLine();
            if (Validation.isValidDate(joiningDate, "dd-MM-yyyy HH:mm")) {
                return LocalDateTime.parse(joiningDate, formatter);
            } else {
                System.err.println("Vui lòng nhập vào đúng định dạng ngày!");
            }
        } while (true);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return String.format("ID: %d - License Plate: %s - Capacity: %d - Joining Date: %s",
                this.id, this.licensePlate, this.capacity, formatter.format(this.joiningDate));
    }
}
