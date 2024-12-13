package UTESHOP.dao;

import java.util.List;

import UTESHOP.entity.PaymentMethod;



public interface IPaymentDao {
	List<PaymentMethod> findAll();
	
	List<PaymentMethod> findPaymentActive();
	
	PaymentMethod findById(int payment_id);
	
	void insert(PaymentMethod paymentMethod);

    void update(PaymentMethod paymentMethod);

    void delete(PaymentMethod paymentMethod);
}
