package UTESHOP.dao.implement;

import java.util.List;

import UTESHOP.configs.JPAConfig;
import UTESHOP.dao.IUserDao;
import UTESHOP.entity.Address;
import UTESHOP.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class UserDao implements IUserDao {

	@Override
	public int count() {
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "SELECT count(c) FROM User c";
		Query query = enma.createQuery(jpql);
		return ((Long) query.getSingleResult()).intValue();
	}

	@Override
	public List<User> findAll(int page, int pagesize) {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<User> query = enma.createNamedQuery("User.findAll", User.class);
		query.setFirstResult(page * pagesize);
		query.setMaxResults(pagesize);
		return query.getResultList();
	}

	@Override
	public List<User> findByFullname(String fullname) {
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "SELECT c FROM User c WHERE c.fullname like :fullname";
		TypedQuery<User> query = enma.createQuery(jpql, User.class);
		query.setParameter("fullname", "%" + fullname + "%");
		return query.getResultList();
	}

	@Override
	public List<User> findAll() {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<User> query = enma.createNamedQuery("User.findAll", User.class);
		return query.getResultList();
	}

	@Override
	public User findById(int id) {
		EntityManager enma = JPAConfig.getEntityManager();
		User user = enma.find(User.class, id);
		return user;
	}

	@Override
	public User findByEmail(String email) {
		EntityManager enma = JPAConfig.getEntityManager();

		try {
			String jpql = "SELECT c FROM User c WHERE c.email = :email";
			TypedQuery<User> query = enma.createQuery(jpql, User.class);
			query.setParameter("email", email);
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
			User user = enma.find(User.class, id);
			if (user != null) {
				enma.remove(user);
			} else {
				throw new Exception("Không tìm thấy");
			}
			enma.remove(user);
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
	public void update(User user) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.merge(user);
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
	public void insert(User user) {
		EntityManager enma = JPAConfig.getEntityManager();
	    EntityTransaction trans = enma.getTransaction();
	    try {
	        trans.begin();

	        // Nếu Address không phải là mới (đã có ID), sử dụng merge
	        if (user.getAddress() != null && user.getAddress().getAddress_id() > 0) {
	            Address managedAddress = enma.merge(user.getAddress());
	            user.setAddress(managedAddress);
	        }

	        // Persist User
	        enma.persist(user);
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
	public Boolean checkExistPhone(String phone) {
		EntityManager em = JPAConfig.getEntityManager();

		try {
			Long count = (Long) em.createQuery("SELECT COUNT(c) FROM User c WHERE c.phone = :phone")
					.setParameter("phone", phone).getSingleResult();
			return count > 0;
		} catch (NoResultException e) {
			return false;
		} finally {
			if (em.isOpen()) {
				em.close();
			}
		}
	}

	@Override
	public Boolean checkCode(String email, String code) {
		User user = findByEmail(email);
		if (user.getCode().equals(code)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean checkPassword(String email, String password) {
		EntityManager enma = JPAConfig.getEntityManager();
        try {
            String jpql = "SELECT u FROM User u WHERE u.email = :email AND u.password = :password";
            TypedQuery<User> query = enma.createQuery(jpql, User.class);
            query.setParameter("email", email);
            query.setParameter("password", password);  // Mật khẩu cần được mã hóa trước khi lưu
            User user = query.getSingleResult();
            return user != null;
        } catch (Exception e) {
            return false;
        } finally {
            enma.close();
        }
	}

	@Override
	public List<User> findAllCustomers() {
		EntityManager em = JPAConfig.getEntityManager();
        try {
            String jpql = "SELECT u FROM User u WHERE u.role.id = 2";
            Query query = em.createQuery(jpql, User.class);
            return query.getResultList();
        } finally {
            em.close();
        }
	}

	@Override
	public List<User> findAllAdmin() {
		EntityManager em = JPAConfig.getEntityManager();
        try {
            String jpql = "SELECT u FROM User u WHERE u.role.id = 1";
            Query query = em.createQuery(jpql, User.class);
            return query.getResultList();
        } finally {
            em.close();
        }
	}

}