package entity;

public class GasCar extends Car implements IRefuelable{
	@Override
	public String start () {
		return String.format("Xe chạy xăng [%s] (Năm SX: [%d], Giá: [$%,.1f]) đã khởi động với tiếng động cơ.",
				super.getModel(), super.getYear(), super.getPrice());
	}

	@Override
	public String stop () {
		return String.format("Xe chạy xăng [%s] (Năm SX: [%d], Giá: [$%,.1f]) đã dừng và động cơ tắt.",
				super.getModel(), super.getYear(), super.getPrice());
	}

	@Override
	public void refuel () {
		System.out.printf("Xe chạy xăng [%s] (Năm SX: [%d], Giá: [$%,.1f]) đang được đổ xăng...\n",
				super.getModel(), super.getYear(), super.getPrice());
	}
}
