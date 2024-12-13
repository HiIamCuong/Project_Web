package UTESHOP.services.implement;

import java.util.List;

import UTESHOP.dao.IPromoteDao;
import UTESHOP.dao.implement.PromoteDao;
import UTESHOP.entity.Promote;
import UTESHOP.services.IPromoteService;

public class PromoteService implements IPromoteService{
	IPromoteDao promoteDao = new PromoteDao();
	@Override
	public List<Promote> findAll() {
		return promoteDao.findAll();
	}

	@Override
	public List<Promote> findPromoteForOrder(int totalPrice) {
		return promoteDao.findPromoteForOrder(totalPrice);
	}

	@Override
	public Promote findById(int id) {
		return promoteDao.findById(id);
	}

	@Override
	public void insert(Promote promote) {
		promoteDao.insert(promote);
	}

	@Override
	public void update(Promote promote) {
		promoteDao.update(promote);
	}

	@Override
	public void delete(Promote promote) {
		promoteDao.delete(promote);
	}

	@Override
	public List<Promote> findByVoucherCodeOrPercent(String voucherCode, Integer discountPercent) {
		return promoteDao.findByVoucherCodeOrPercent(voucherCode, discountPercent);
	}

}
