package business;

import entity.IManager;
import entity.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderManager implements IManager<Order> {
    public List<Order> orderList = new ArrayList<>();

    @Override
    public void add(Order item) {

    }

    @Override
    public void update(int index, Order item) {

    }

    @Override
    public void delete(int index) {

    }

    @Override
    public void display() {

    }
}
