package UTESHOP.services.implement; // Ensure this matches your structure

import UTESHOP.services.ISearchService; // Import the interface
import java.util.List;
import UTESHOP.dao.IProductDao;
import UTESHOP.dao.implement.ProductDao;
import UTESHOP.entity.Product;

public class SearchService implements ISearchService {
    private IProductDao productDao;

    public SearchService() {
        this.productDao = new ProductDao();
    }

    @Override
    public List<Product> searchProducts(String keyword) {
        return productDao.findByName(keyword);
    }
}
