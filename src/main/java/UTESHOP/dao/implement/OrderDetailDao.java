package UTESHOP.dao.implement;

import java.util.List;

import UTESHOP.configs.JPAConfig;
import UTESHOP.dao.IOrderDetailDao;
import UTESHOP.entity.OrderDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

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
	
	@SuppressWarnings("unchecked")
	public List<OrderDetail> findByOrderId(int orderId) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            String jpql = "SELECT od FROM OrderDetail od WHERE od.order.order_id = :orderId";
            Query query = em.createQuery(jpql, OrderDetail.class);
            query.setParameter("orderId", orderId);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
