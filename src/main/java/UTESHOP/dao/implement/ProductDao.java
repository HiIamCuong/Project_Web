package UTESHOP.dao.implement;

import java.time.LocalDate;
import java.util.List;

import UTESHOP.configs.JPAConfig;
import UTESHOP.dao.IProductDao;
import UTESHOP.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class ProductDao implements IProductDao {

	@Override
	public List<Product> findAll() {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Product> query = enma.createNamedQuery("Product.findAll", Product.class);
		return query.getResultList();
	}

	@Override
	public Product findById(int id) {
		EntityManager enma = JPAConfig.getEntityManager();
		Product product = enma.find(Product.class, id);
		return product;
	}

	@Override
	public void insert(Product product) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.persist(product);
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
	public void update(Product product) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.merge(product);
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
			Product product = enma.find(Product.class, id);
			if (product != null) {
				enma.remove(product);
			} else {
				throw new Exception("Không tìm thấy");
			}
			enma.remove(product);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		} finally {
			enma.close();
		}
	}

	@Override
	public List<Product> findByName(String keyword) {
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "SELECT c FROM Product c WHERE c.name like :name";
		TypedQuery<Product> query = enma.createQuery(jpql, Product.class);
		query.setParameter("name", "%" + keyword + "%");
		return query.getResultList();
	}

	@Override
	public List<Product> findProductActive() {
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "SELECT c FROM Product c WHERE c.status = 1";
		TypedQuery<Product> query = enma.createQuery(jpql, Product.class);
		return query.getResultList();
	}

	@Override
	public List<Product> findAll(int page, int pagesize) {
		EntityManager em = JPAConfig.getEntityManager();
	    return em.createQuery("SELECT p FROM Product p", Product.class)
	             .setFirstResult((page - 1) * pagesize)
	             .setMaxResults(pagesize)
	             .getResultList();
	}

	@Override
	public int countProduct(int pageSize) {
		EntityManager enma = JPAConfig.getEntityManager();
		String countQuery = "SELECT COUNT(u) FROM Product u";
		Long count = (Long) enma.createQuery(countQuery).getSingleResult();
		int totalPages = (int) Math.ceil(count.doubleValue() / (double) pageSize);
		return totalPages;
	}

	@Override
	public int countProduct(int pageSize, String keyword) {
		EntityManager enma = JPAConfig.getEntityManager();
		String countQuery = "SELECT COUNT(u) FROM Product u WHERE u.name like :name";
		Query query = enma.createQuery(countQuery);
		query.setParameter("name", "%" + keyword + "%");
		Long count = (Long) query.getSingleResult();
		int totalPages = (int) Math.ceil(count.doubleValue() / (double) pageSize);
		return totalPages;
	}

	@Override
	public List<Product> findByName(int page, int pagesize, String keyword) {
		EntityManager enma = JPAConfig.getEntityManager();
		String queryStr = "SELECT u FROM Product u WHERE u.name like :name";
		TypedQuery<Product> query = enma.createQuery(queryStr, Product.class);
		query.setParameter("name", "%" + keyword + "%");
		query.setFirstResult((page - 1) * pagesize);
		query.setMaxResults(pagesize);
		return query.getResultList();
	}

	@Override
	public int productCount(int category_id) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			String jpql = "SELECT COUNT(p) FROM Product p WHERE p.category.category_id = :category_id";
			TypedQuery<Long> query = em.createQuery(jpql, Long.class);
			query.setParameter("category_id", category_id);

			Long count = query.getSingleResult();
			return count.intValue();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			em.close();
		}
	}

	@Override
	public List<Product> getAllProducts() {
		EntityManager entityManager = JPAConfig.getEntityManager();
		try {
			TypedQuery<Product> query = entityManager.createNamedQuery("Product.findAll", Product.class);
			return query.getResultList();
		} finally {
			entityManager.close();
		}
	}

	@Override
	public List<Product> getNewArrivals() {
		EntityManager entityManager = JPAConfig.getEntityManager();
		List<Product> products = entityManager
				.createQuery("SELECT p FROM Product p WHERE p.status = :status ORDER BY p.createDate DESC",
						Product.class)
				.setParameter("status", 1) // assuming status = 1 means available
				.setMaxResults(5) // limit to 5 new arrivals
				.getResultList();
		return products;
	}

	@Override
	public Product getProductById(int productId) {
		EntityManager entityManager = JPAConfig.getEntityManager();
		try {
			return entityManager.find(Product.class, productId); // Finds the product by primary key
		} finally {
			entityManager.close();
		}
	}

	@Override
	public List<Product> getRelatedProducts(int productId) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			// Query to fetch related products based on some criteria (e.g., same category)
			String jpql = "SELECT p FROM Product p WHERE p.category = "
					+ "(SELECT p2.category FROM Product p2 WHERE p2.product_id = :productId) "
					+ "AND p.product_id <> :productId";
			TypedQuery<Product> query = em.createQuery(jpql, Product.class);
			query.setParameter("productId", productId);
			query.setMaxResults(5); // Limit the number of related products
			return query.getResultList();
		} finally {
			em.close();
		}
	}
	
	@Override
	public List<Product> getBestSellingProducts() {
	    EntityManager entityManager = JPAConfig.getEntityManager();
	    try {
	        // Adjust the field name based on your actual entity definition
	        String jpql = "SELECT p FROM Product p WHERE p.isBestSeller = true"; // or use the correct field name
	        TypedQuery<Product> query = entityManager.createQuery(jpql, Product.class);
	        return query.getResultList();
	    } finally {
	        entityManager.close();
	    }
	}
	
	@Override
	public List<Product> getNewArrivalProducts() {
	    EntityManager entityManager = JPAConfig.getEntityManager();
	    try {
	        // Adjust JPQL if using JPQL or use native SQL if needed
	        String jpql = "SELECT * FROM products WHERE createDate > DATEADD(DAY, -30, GETDATE())";
	        TypedQuery<Product> query = entityManager.createQuery(jpql, Product.class);
	        return query.getResultList();
	    } finally {
	        entityManager.close();
	    }
	}
}
