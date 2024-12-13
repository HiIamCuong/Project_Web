package UTESHOP.services;

import java.util.List;

import UTESHOP.entity.Promote;

public interface IPromoteService {
	List<Promote> findAll();
	
	List<Promote> findPromoteForOrder(int totalPrice);
	
	Promote findById(int id);
	
	void insert(Promote promote);

    void update(Promote promote);

    void delete(Promote promote);
    
	List<Promote> findByVoucherCodeOrPercent(String voucherCode, Integer discountPercent);
}
