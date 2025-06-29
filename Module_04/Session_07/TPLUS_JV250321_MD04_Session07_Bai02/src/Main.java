public class Main {
	public static void main (String[] args) {
		Vehicle car = new Car("Lexus LX540", 180);
		Vehicle bike = new Bike("Yamaha R1", 150);

		System.out.println("----- Car -----");
		car.start();
		car.displayInfo();

		System.out.println("----- Bike -----");
		bike.start();
		bike.displayInfo();
	}
}
