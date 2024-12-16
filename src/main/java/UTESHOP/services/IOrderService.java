package UTESHOP.services;

import java.util.List;

import UTESHOP.entity.Order;

public interface IOrderService {
	Order insert(Order order);

	List<Order> findAll();

	Order findById(int id);

	void update(Order order);
	
	List<Object[]> getRevenueByTime(String date, String timeUnit);
	
	List<Order> findByUserId(int userId);
	
	List<Order> findByUserIdAndStatus(int userId, int status);
	
	boolean updateStatus(int orderId, String newStatus);
	
	double getTotalRevenueByTime(String date, String timeUnit);
}
