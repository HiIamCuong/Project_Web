package UTESHOP.dao;

import java.time.LocalDate;
import java.util.List;

import UTESHOP.entity.Product;



public interface IProductDao {
	List<Product> findAll();
	Product findById(int id);	
	void insert(Product product);	
	void update(Product product);
	void delete(int id);
	List<Product> findByName(String keyword);
	List<Product> findProductActive();
	List<Product> findAll(int page, int pagesize);
	int countProduct(int pageSize);
	int countProduct(int pageSize, String keyword);
	List<Product> findByName(int page, int pagesize, String keyword);
	int productCount(int category_id);
	List<Product> getAllProducts(); 
    List<Product> getNewArrivals();
    Product getProductById(int productId);
    List<Product> getRelatedProducts(int productId);
    List<Product> getBestSellingProducts();
    List<Product> getNewArrivalProducts(); // Add this method
}
