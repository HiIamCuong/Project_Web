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
	List<Object[]> getRevenueByTime(String date, String timeUnit) ;
	List<Order> findByUserId(int userId);
	List<Order> findByUserIdAndStatus(int userId, int status);
	double getTotalRevenueByTime(String date, String timeUnit);
	boolean updateStatus(int orderId, String newStatus);
}
