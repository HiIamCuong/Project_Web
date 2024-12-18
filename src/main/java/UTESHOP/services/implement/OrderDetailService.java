package UTESHOP.services.implement;

import java.util.List;

import UTESHOP.dao.IOrderDetailDao;
import UTESHOP.dao.implement.OrderDetailDao;
import UTESHOP.entity.OrderDetail;
import UTESHOP.services.IOrderDetailService;


public class OrderDetailService implements IOrderDetailService {
	IOrderDetailDao orderDao = new OrderDetailDao();

	@Override
	public void insert(OrderDetail orderDetail) {
		orderDao.insert(orderDetail);
	}
	public List<OrderDetail> findByOrderId(int orderId){
		return orderDao.findByOrderId(orderId);
	}
}
