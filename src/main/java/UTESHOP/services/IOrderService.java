package UTESHOP.services;

import java.util.List;

import UTESHOP.entity.Order;

public interface IOrderService {
	Order insert(Order order);

	List<Order> findAll();

	Order findById(int id);

	void update(Order order);
}
