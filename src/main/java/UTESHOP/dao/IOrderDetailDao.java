package UTESHOP.dao;

import java.util.List;

import UTESHOP.entity.OrderDetail;

public interface IOrderDetailDao {
	void insert(OrderDetail orderDetail);
	List<OrderDetail> findByOrderId(int orderId);
}
