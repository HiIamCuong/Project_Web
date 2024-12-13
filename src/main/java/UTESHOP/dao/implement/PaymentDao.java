package UTESHOP.dao.implement;

import java.util.ArrayList;
import java.util.List;

import UTESHOP.configs.JPAConfig;
import UTESHOP.dao.IPaymentDao;
import UTESHOP.entity.PaymentMethod;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;


public class PaymentDao implements IPaymentDao {

	@Override
	public List<PaymentMethod> findAll() {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<PaymentMethod> query = enma.createNamedQuery("PaymentMethod.findAll", PaymentMethod.class);
		return query.getResultList();
	}

	@Override
	public List<PaymentMethod> findPaymentActive() {
		List<PaymentMethod> all = findAll();
		List<PaymentMethod> listPayment = new ArrayList<>();
		for (PaymentMethod x : all) {
			if (x.getStatus() == 1) {
				listPayment.add(x);
			}
		}
		return listPayment;
	}

	@Override
	public PaymentMethod findById(int payment_id) {
		EntityManager enma = JPAConfig.getEntityManager();
		PaymentMethod payment = enma.find(PaymentMethod.class, payment_id);
		return payment;
	}

	@Override
	public void insert(PaymentMethod paymentMethod) {
		EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            enma.persist(paymentMethod);
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
	public void update(PaymentMethod paymentMethod) {
		EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            enma.merge(paymentMethod);
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
	public void delete(PaymentMethod paymentMethod) {
		EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            paymentMethod = enma.merge(paymentMethod);
            enma.remove(paymentMethod);
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
