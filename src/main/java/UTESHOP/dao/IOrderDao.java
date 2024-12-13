package UTESHOP.dao;

import java.util.List;

import UTESHOP.entity.Order;
public interface IOrderDao {
	Order insert(Order order);

	List<Order> findAll();
	Order findById(int id);
	void insertFnc(Order order);
	void update(Order order);
	void delete(int id);	
}
