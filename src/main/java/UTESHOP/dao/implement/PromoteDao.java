package UTESHOP.dao.implement;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import UTESHOP.configs.JPAConfig;
import UTESHOP.dao.IPromoteDao;
import UTESHOP.entity.Promote;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class PromoteDao implements IPromoteDao {

	@Override
	public List<Promote> findAll() {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Promote> query = enma.createNamedQuery("Promote.findAll", Promote.class);
		return query.getResultList();
	}

	@Override
	public List<Promote> findPromoteForOrder(int totalPrice) {
		List<Promote> listPromote = new ArrayList<>();
		LocalDateTime currentDate = LocalDateTime.now();
		
		List<Promote> all = findAll();
		for (Promote x: all) {
			if ((currentDate.isEqual(x.getStartDate()) || currentDate.isAfter(x.getStartDate())) 
					&& (currentDate.isEqual(x.getEndDate()) || currentDate.isBefore(x.getEndDate()))
					&& x.getQuantity() > x.getQuantityUsed()
					&& totalPrice >= x.getMinOrderTotal()) {
				listPromote.add(x);
			}
		}
		return listPromote;
	}

	@Override
	public Promote findById(int id) {
		EntityManager enma = JPAConfig.getEntityManager();
		Promote promote = enma.find(Promote.class, id);
		return promote;
	}

	@Override
	public void insert(Promote promote) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.persist(promote);
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
	public void update(Promote promote) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.merge(promote);
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
	public void delete(int id) {
		EntityManager enma = JPAConfig.getEntityManager();
	    EntityTransaction trans = enma.getTransaction();
	    try {
	        trans.begin();
	        Promote promote = enma.find(Promote.class, id);
	        if (promote != null) {
	            enma.remove(promote);
	        }
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
	public List<Promote> findByVoucherCodeOrPercent(String voucherCode, Integer discountPercent) {
		EntityManager enma = JPAConfig.getEntityManager();
	    try {
	        String jpql = "SELECT p FROM Promote p WHERE " +
	                      "(:voucherCode IS NULL OR p.voucherCode = :voucherCode) AND " +
	                      "(:discountPercent IS NULL OR p.discountPercent = :discountPercent)";

	        TypedQuery<Promote> query = enma.createQuery(jpql, Promote.class);

	        // Nếu voucherCode null hoặc rỗng, set thành null trong query
	        query.setParameter("voucherCode", (voucherCode != null && !voucherCode.trim().isEmpty()) ? voucherCode : null);

	        // Nếu discountPercent null, truyền giá trị null vào query
	        query.setParameter("discountPercent", discountPercent);

	        return query.getResultList();
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw e;
	    } finally {
	        enma.close();
	    }
	}

}
