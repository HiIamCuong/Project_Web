package UTESHOP.services;

import java.util.List;
import UTESHOP.entity.Product;

public interface ISearchService {
	List<Product> searchProducts(String keyword);
}
