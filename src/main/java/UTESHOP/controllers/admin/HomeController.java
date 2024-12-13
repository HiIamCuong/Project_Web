package UTESHOP.controllers.admin;

import java.io.IOException;
import java.util.Set;

import UTESHOP.entity.Cart;
import UTESHOP.entity.CartItem;
import UTESHOP.entity.User;
import UTESHOP.services.ICartService;
import UTESHOP.services.implement.CartService;
import UTESHOP.utils.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet(urlPatterns = { "/admin/home"})
public class HomeController extends HttpServlet  {
	private static final long serialVersionUID = 1L;
	public ICartService cartService = new CartService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		int cartItemCount = 0;
		if (session != null && session.getAttribute("account") != null) 
		{
			User u = (User) session.getAttribute("account"); 
			Cart cart = cartService.findByUser(u.getId());
			if (cart == null) 
			{
				cartItemCount=0;
			} 
			else 
			{
				Set<CartItem> listCartItem = cart.getCartItems();
				cartItemCount = listCartItem.size();
			}
		}
		req.getSession().setAttribute("cartItemCount", cartItemCount);
		req.getRequestDispatcher(Constant.ADMIN_HOME).forward(req, resp);
	}

}
