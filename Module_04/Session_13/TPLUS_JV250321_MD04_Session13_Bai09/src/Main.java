import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {
	public static void main (String[] args) {
		List <Order> orderList = new ArrayList <>();
		orderList.add(new Order(1, "Quy", LocalDate.of(2025, 3, 20), Optional.of(LocalDate.of(2025, 3, 25))));
		orderList.add(new Order(2, "Lan", LocalDate.of(2025, 3, 21), Optional.empty()));
		orderList.add(new Order(3, "Minh", LocalDate.of(2025, 3, 22), Optional.of(LocalDate.of(2025, 3, 23))));
		orderList.add(new Order(4, "Huyen", LocalDate.of(2025, 3, 23), Optional.empty()));
		orderList.add(new Order(5, "Viet", LocalDate.of(2025, 3, 23), Optional.of(LocalDate.of(2025, 3, 30))));

		// Liet ke don hang da giao
		System.out.println("Đơn hàng đã giao:");
		orderList.stream()
				.filter(order -> order.getDeliveryDate().isPresent())
				.forEach(System.out::println);

		// Liet ke don hang chua giao
		System.out.println("Đơn hàng chưa giao:");
		orderList.stream()
				.filter(order -> order.getDeliveryDate().isEmpty())
				.forEach(System.out::println);

		// Dem so don giao hang trong khoang 2025-03-17 den 2025-03-23
		LocalDate fromDate = LocalDate.of(2025, 3, 17);
		LocalDate toDate = LocalDate.of(2025, 3, 23);

		long count = orderList.stream()
				.map(Order::getDeliveryDate)
				.filter(Optional::isPresent)
				.map(Optional::get)
				.filter(date -> !date.isBefore(fromDate) && !date.isAfter(toDate))
				.count();
		System.out.printf("So don hang da giao tu %s den %s: %d đơn\n", fromDate, toDate, count);

		// In thong tin
		System.out.println("Danh sách tất cả đơn hàng:");
		orderList.forEach(System.out::println);
	}
}
