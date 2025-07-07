package business;

import entity.IManager;
import entity.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderManager implements IManager <Order> {
	public List <Order> orderList = new ArrayList <>();

	@Override
	public void add (Order item) {
		orderList.add(item);
		System.out.println("Đơn hàng được thêm thành công!");
	}

	@Override
	public void update (int index, Order item) {
		if (index == -1) {
			System.err.println("Không tồn tại đơn hàng này!");
		} else {
			orderList.get(index).setCustomerName(item.getCustomerName());
			System.out.println("Cập nhật thành công!");
		}
	}

	@Override
	public void delete (int index) {
		if (index == -1) {
			System.err.println("Không tồn tại đơn hàng này!");
		} else {
			orderList.remove(index);
			System.out.println("Xóa thành công!");
		}
	}

	@Override
	public void display () {
		System.out.println("Danh sách đơn hàng:");
		for (Order order : orderList) {
			order.displayData();
		}
		System.out.println("-------------");
	}

	public int findIndexById (String id) {
		for (int i = 0; i < orderList.size(); i++) {
			if (orderList.get(i).getOrderId().equals(id)) {
				return i;
			}
		}
		return -1;
	}
}
