public class Bike extends Vehicle{
	public Bike (String name, int speed) {
		super(name, speed);
	}
	@Override
	public void displayInfo(){
		System.out.printf("Bike Name: %5s, Speed: %5s km/h\n", getName(), getSpeed());
	}
}
