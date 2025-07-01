package entity;

public class Garage {
	private Car[] cars;
	private int currentIndex;

	public Garage (int capacity) {
		cars = new Car[capacity];
		currentIndex = 0;
	}

	public void addCar(Car car){
		cars[currentIndex] = car;
		currentIndex++;
	}

	public void startAll(){
		System.out.println("Khởi động tất cả xe trong gara:");
		for(int i = 0; i < currentIndex; i++){
			System.out.println(cars[i].start());
		}
	}

	public void stopAll(){
		System.out.println("Dừng tất cả xe trong gara:");
		for(int i = 0; i < currentIndex; i++){
			System.out.println(cars[i].stop());
		}
	}
	public void refuelAll(){
		System.out.println("Nạp nhiên liệu cho tất cả xe trong gara:");
		for(int i = 0; i < currentIndex; i++){
			if(cars[i] instanceof IRefuelable){
				((IRefuelable) cars[i]).refuel();
			}
		}
	}

}
