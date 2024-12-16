package UTESHOP.services;

import UTESHOP.entity.Cart;

public interface ICartService {
	Cart findByUser(int user_id);
	void insert(Cart cart);
	void delete(int id) throws Exception;
}
