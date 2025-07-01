package presentation;

import entity.ElectricCar;
import entity.Garage;
import entity.GasCar;

public class Main {
	public static void main (String[] args) {
		ElectricCar byd = new ElectricCar();
		byd.setModel("BYD Dolphin");
		byd.setYear(2023);
		byd.setPrice(75000.00);

		GasCar toyota = new GasCar();
		toyota.setModel("Toyota Corolla");
		toyota.setYear(2022);
		toyota.setPrice(50000.00);

		// Tạo garage và thêm xe
		Garage myGarage = new Garage(100);
		myGarage.addCar(byd);
		myGarage.addCar(toyota);

		myGarage.startAll();
		myGarage.stopAll();
		myGarage.refuelAll();
	}
}
