package UTESHOP.dao.implement;

import UTESHOP.configs.JPAConfig;
import UTESHOP.dao.IOrderDetailDao;
import UTESHOP.entity.OrderDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class OrderDetailDao implements IOrderDetailDao {

	@Override
	public void insert(OrderDetail orderDetail) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.persist(orderDetail);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

}
