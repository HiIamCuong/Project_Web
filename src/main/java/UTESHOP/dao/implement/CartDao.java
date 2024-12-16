package UTESHOP.dao.implement;

import UTESHOP.configs.JPAConfig;
import UTESHOP.dao.ICartDao;
import UTESHOP.entity.Cart;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;


public class CartDao implements ICartDao {

	@Override
	public Cart findByUser(int user_id) {
		EntityManager enma = JPAConfig.getEntityManager();
        String jpql = "SELECT c FROM Cart c WHERE c.user.id = :id";
        TypedQuery<Cart> query = enma.createQuery(jpql, Cart.class);
        query.setParameter("id", user_id);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
	}

	@Override
	public void delete(int id) throws Exception {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			Cart cart = enma.find(Cart.class, id);
			if (cart != null) {
				enma.remove(cart);
			} else {
				throw new Exception("Không tìm thấy");
			}
			enma.remove(cart);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	@Override
	public void insert(Cart cart) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.persist(cart);
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
