package entity;

public class ElectricCar extends Car implements IRefuelable {
	@Override
	public String start () {
		return String.format("Xe điện [%s] (Năm SX: [%d], Giá: [$%,.1f]) đã khởi động không tiếng ồn.",
				super.getModel(), super.getYear(), super.getPrice());
	}

	@Override
	public String stop () {
		return String.format("Xe điện [%s] (Năm SX: [%d], Giá: [$%,.1f]) đã dừng và ngắt kết nối động cơ.",
				super.getModel(), super.getYear(), super.getPrice());
	}

	@Override
	public void refuel () {
		System.out.printf("Xe điện [%s] (Năm SX: [%d], Giá: [$%,.1f]) đang sạc điện...\n",
				super.getModel(), super.getYear(), super.getPrice());
	}
}
