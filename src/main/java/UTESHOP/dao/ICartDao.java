package UTESHOP.dao;

import UTESHOP.entity.Cart;

public interface ICartDao {
	Cart findByUser(int user_id);
	void insert(Cart cart);
	void delete(int id) throws Exception;
}
