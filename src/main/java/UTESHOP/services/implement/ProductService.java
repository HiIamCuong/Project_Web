package UTESHOP.services.implement;

import java.util.ArrayList;
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
	
	public List<Product> searchProducts(String keyword) {
	    return productDao.findByName(keyword);
	}

	public List<Product> searchProducts(String keyword, String category, int page, int pageSize) {
	    List<Product> allProducts = productDao.getAllProducts(); // Retrieve all products
	    List<Product> filteredProducts = new ArrayList<>();

	    for (Product product : allProducts) {
	        boolean matchesKeyword = (keyword == null || keyword.isEmpty() || 
	                                  product.getName().toLowerCase().contains(keyword.toLowerCase()));
	        boolean matchesCategory = (category == null || category.isEmpty() || 
	                                   (product.getCategory() != null && 
	                                   product.getCategory().getName().equalsIgnoreCase(category)));

	        if (matchesKeyword && matchesCategory) {
	            filteredProducts.add(product);
	        }
	    }

	    // Implement pagination
	    int start = (page - 1) * pageSize;
	    int end = Math.min(start + pageSize, filteredProducts.size());
	    
	    return filteredProducts.subList(start, end); // Return the paginated list
	}
	
	public int countTotalPages(int pageSize, String keyword, String category) {
	    List<Product> allProducts = getFilteredProducts(keyword, category); // Retrieve filtered products
	    int totalProducts = allProducts.size();
	    return (int) Math.ceil((double) totalProducts / pageSize);
	}

	private List<Product> getFilteredProducts(String keyword, String category) {
	    List<Product> allProducts = productDao.getAllProducts(); // Retrieve all products
	    List<Product> filteredProducts = new ArrayList<>();

	    for (Product product : allProducts) {
	        boolean matchesKeyword = (keyword == null || keyword.isEmpty() || 
	                                  product.getName().toLowerCase().contains(keyword.toLowerCase()));
	        boolean matchesCategory = (category == null || category.isEmpty() || 
	                                   (product.getCategory() != null && 
	                                   product.getCategory().getName().equalsIgnoreCase(category)));

	        if (matchesKeyword && matchesCategory) {
	            filteredProducts.add(product);
	        }
	    }
	    return filteredProducts;
	}
	
    @Override
    public List<Product> getBestSellingProducts() {
        return productDao.getBestSellingProducts();
    }
    
    @Override
    public List<Product> getNewArrivalProducts() {
        return productDao.getNewArrivalProducts(); // Call the DAO method
    }
}
