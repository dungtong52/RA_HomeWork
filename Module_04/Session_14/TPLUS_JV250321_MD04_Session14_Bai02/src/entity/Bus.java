package entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Bus {
	private int id;
	private String licensePlate;
	private int capacity;
	private LocalDateTime joiningDate;

	public Bus () {
	}

	public Bus (int id, String licensePlate, int capacity, LocalDateTime joiningDate) {
		this.id = id;
		this.licensePlate = licensePlate;
		this.capacity = capacity;
		this.joiningDate = joiningDate;
	}

	public int getId () {
		return id;
	}

	public void setId (int id) {
		this.id = id;
	}

	public String getLicensePlate () {
		return licensePlate;
	}

	public void setLicensePlate (String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public int getCapacity () {
		return capacity;
	}

	public void setCapacity (int capacity) {
		this.capacity = capacity;
	}

	public LocalDateTime getJoiningDate () {
		return joiningDate;
	}

	public void setJoiningDate (LocalDateTime joiningDate) {
		this.joiningDate = joiningDate;
	}

	public void inputData (Scanner scanner) {
		this.id = inputId(scanner);
		this.licensePlate = inputLicensePlate(scanner);
		this.capacity = inputCapacity(scanner);
		this.joiningDate = inputJoiningDate(scanner);
	}

	public int inputId (Scanner scanner) {
	}

	public String inputLicensePlate (Scanner scanner) {
		
	}

	public int inputCapacity (Scanner scanner) {
	}

	public LocalDateTime inputJoiningDate (Scanner scanner) {
	}

	@Override
	public String toString () {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		return String.format("ID: %d - License Plate: %s - Capacity: %d - Joining Date: %s",
				this.id, this.licensePlate, this.capacity, formatter.format(this.joiningDate));
	}
}
