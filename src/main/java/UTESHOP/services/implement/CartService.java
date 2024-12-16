package UTESHOP.services.implement;

import UTESHOP.dao.ICartDao;
import UTESHOP.dao.implement.CartDao;
import UTESHOP.entity.Cart;
import UTESHOP.services.ICartService;

public class CartService implements ICartService {
	ICartDao cartDao = new CartDao();
	@Override
	public Cart findByUser(int user_id) {
		return cartDao.findByUser(user_id);
	}

	@Override
	public void delete(int id) throws Exception {
		cartDao.delete(id);
	}

	@Override
	public void insert(Cart cart) {
		cartDao.insert(cart);
	}

}
