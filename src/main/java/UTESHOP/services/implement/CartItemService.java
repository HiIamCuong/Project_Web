package UTESHOP.services.implement;

import java.util.List;
import java.util.Set;

import UTESHOP.dao.ICartItemDao;
import UTESHOP.dao.implement.CartItemDao;
import UTESHOP.entity.CartItem;
import UTESHOP.services.ICartItemService;


public class CartItemService implements ICartItemService {
	ICartItemDao cartItemDao = new CartItemDao();
	@Override
	public List<CartItem> findByCartId(int cart_id) {
		return cartItemDao.findByCartId(cart_id);
	}

	@Override
	public int totalPrice(Set<CartItem> listCartItem) {
		return cartItemDao.totalPrice(listCartItem);
	}

	@Override
	public void delete(int id) throws Exception {
		cartItemDao.delete(id);
	}

	@Override
	public CartItem findById(int id) {
		return cartItemDao.findById(id);
	}

	@Override
	public void update(CartItem cartItem) {
		cartItemDao.update(cartItem);
	}

}
