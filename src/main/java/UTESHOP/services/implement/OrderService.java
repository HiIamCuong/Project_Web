package UTESHOP.services.implement;

import java.util.List;

import UTESHOP.dao.IOrderDao;
import UTESHOP.dao.implement.OrderDao;
import UTESHOP.entity.Order;
import UTESHOP.services.IOrderService;
public class OrderService implements IOrderService{
	IOrderDao orderDao = new OrderDao();

	@Override
	public Order insert(Order order) {
		return orderDao.insert(order);
	}

	@Override
	public List<Order> findAll() {
		return orderDao.findAll();
	}

	@Override
	public Order findById(int id) {
		return orderDao.findById(id);
	}

	@Override
	public void update(Order order) {
		orderDao.update(order);
	}

	public List<Object[]> getRevenueByTime(String date, String timeUnit) {
        return orderDao.getRevenueByTime(date, timeUnit);
    }
	
	public List<Order> findByUserId(int userId) {
		return orderDao.findByUserId(userId);
	}

	public List<Order> findByUserIdAndStatus(int userId, int status) {
		return orderDao.findByUserIdAndStatus(userId,status);
	}
	public boolean updateStatus(int orderId, String newStatus){
		return orderDao.updateStatus(orderId,newStatus);
	}
	public double getTotalRevenueByTime(String date, String timeUnit) {
		return orderDao.getTotalRevenueByTime(date, timeUnit);
	}

}
