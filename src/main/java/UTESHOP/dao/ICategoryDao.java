package UTESHOP.dao;

import java.util.List;

import UTESHOP.entity.Category;

public interface ICategoryDao {
List<Category> findAll();
	
	Category findById(int id);
	
	void insert (Category cate);
	void delete (int category_id);
	void update (Category cate);
}
