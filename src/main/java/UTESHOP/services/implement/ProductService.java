package UTESHOP.services.implement;

import java.util.List;

import UTESHOP.configs.JPAConfig;
import UTESHOP.dao.IProductDao;
import UTESHOP.dao.implement.ProductDao;
import UTESHOP.entity.Product;
import UTESHOP.services.IProductService;
import jakarta.persistence.EntityManager;



public class ProductService implements IProductService{
	IProductDao productDao = new ProductDao();
	@Override
	public List<Product> findAll() {
		return productDao.findAll();
	}

	@Override
	public Product findById(int id) {
		return productDao.findById(id);
	}

	@Override
	public void insert(Product product) {
		productDao.insert(product);
	}

	@Override
	public void update(Product product) {
		productDao.update(product);
	}

	@Override
	public void delete(int id) {
		productDao.delete(id);
	}

	@Override
	public List<Product> findByName(String keyword) {
		return productDao.findByName(keyword);
	}

	@Override
	public List<Product> findProductActive() {
		return productDao.findProductActive();
	}

	@Override
	public List<Product> findAll(int page, int pagesize) {
		return productDao.findAll(page, pagesize);
	}

	@Override
	public int countProduct(int pageSize) {
		return productDao.countProduct(pageSize);
	}

	@Override
	public int countProduct(int pageSize, String keyword) {
		return productDao.countProduct(pageSize, keyword);
	}

	@Override
	public List<Product> findByName(int page, int pagesize, String keyword) {
		return productDao.findByName(page, pagesize, keyword);
	}

	@Override
	public int productCount(int category_id) {
		return productDao.productCount(category_id);
	}

	@Override
	public List<Product> getAllProducts() {
		return productDao.getAllProducts();
	}

	@Override
	public List<Product> getRelatedProducts(int productId) {
		 return productDao.getRelatedProducts(productId);
	}

	@Override
	public Product getProductById(int productId) {
		// Logic to fetch the product by ID, for example, using JPA or JDBC
        // Assuming the use of JPA:
        EntityManager em = JPAConfig.getEntityManager();
        return em.find(Product.class, productId);
	}
	public ProductService() {
        this.productDao = new ProductDao();
    }

}
