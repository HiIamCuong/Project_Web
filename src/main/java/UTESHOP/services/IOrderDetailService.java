package UTESHOP.services;

import java.util.List;

import UTESHOP.entity.OrderDetail;

public interface IOrderDetailService {
	void insert(OrderDetail orderDetail);
	List<OrderDetail> findByOrderId(int orderId);
}
