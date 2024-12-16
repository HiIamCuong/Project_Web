package UTESHOP.dao;

import java.util.List;

import java.util.Set;

import UTESHOP.entity.CartItem;

public interface ICartItemDao {
	List<CartItem> findByCartId(int cart_id);
	
	int totalPrice(Set<CartItem> listCartItem);
	
	void delete(int id) throws Exception;
	void insert(CartItem cartItem);
	CartItem findById(int id);

	void update(CartItem cartItem);
}
