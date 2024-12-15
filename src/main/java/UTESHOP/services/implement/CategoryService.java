package UTESHOP.services.implement;

import java.util.List;

import UTESHOP.dao.ICategoryDao;
import UTESHOP.dao.implement.CategoryDao;
import UTESHOP.entity.Category;
import UTESHOP.services.ICategoryService;



public class CategoryService implements ICategoryService{
	ICategoryDao categoryDao = new CategoryDao();
	@Override
	public List<Category> findAll() {
		return categoryDao.findAll();
	}

	@Override
	public Category findById(int id) {
		return categoryDao.findById(id);
	}

	@Override
	public void insert(Category cate) {
		categoryDao.insert(cate);
	}

	@Override
	public void delete(int category_id) {
		categoryDao.delete(category_id);
	}

	@Override
	public void update(Category cate) {
		categoryDao.update(cate);
		
	}

}
