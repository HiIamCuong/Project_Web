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
}
