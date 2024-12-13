package UTESHOP.controllers;

import java.io.IOException;
import java.util.Set;

import UTESHOP.entity.Cart;
import UTESHOP.entity.CartItem;
import UTESHOP.entity.User;
import UTESHOP.services.ICartItemService;
import UTESHOP.services.ICartService;
import UTESHOP.services.implement.CartItemService;
import UTESHOP.services.implement.CartService;
import UTESHOP.utils.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet(urlPatterns = { "/cart","/remove","/delete", "/update"})
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public ICartService cartService = new CartService();
	public ICartItemService cartItemService = new CartItemService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		if (url.contains("cart")) {
			HttpSession session = req.getSession();
			User user = (User) session.getAttribute("account");			
			if (user == null) {
				resp.sendRedirect(req.getContextPath() + "/login");
	            return;
			} else {
				Cart cart = cartService.findByUser(user.getId());
				if (cart == null) {
					req.setAttribute("alert", "Your shopping cart is currently empty. Start shopping now!");
				} 
				else 
				{
					Set<CartItem> listCartItem = cart.getCartItems();
					if (listCartItem.size() == 0) 
					{
						int id = cart.getCart_id();
						try {
							cartService.delete(id);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						req.setAttribute("alert", "Your shopping cart is currently empty. Start shopping now!");
					}
					else
					{
						req.setAttribute("listCartItem", listCartItem);	
			            int total = cartItemService.totalPrice(listCartItem);
						req.setAttribute("total", total);
					}	
				}
			}
			req.getRequestDispatcher(Constant.CART).forward(req, resp);
		}
		else if (url.contains("/delete"))
		{
			String id = req.getParameter("id");
			try {
				cartItemService.delete(Integer.parseInt(id));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			resp.sendRedirect(req.getContextPath() + "/cart");
		}
		else if (url.contains("/remove"))
		{
			HttpSession session = req.getSession();
			User user = (User) session.getAttribute("account");
			Cart cart = cartService.findByUser(user.getId());
			int id = cart.getCart_id();
			try {
				cartService.delete(id);
				resp.sendRedirect(req.getContextPath() + "/cart");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (url.contains("/update"))
		{
			String id = req.getParameter("id");
			String qty = req.getParameter("quantity");
			if (Integer.parseInt(qty)==0)
			{
				try {
					cartItemService.delete(Integer.parseInt(id));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else
			{
				CartItem cartItem=cartItemService.findById(Integer.parseInt(id));
				cartItem.setQuantity(Integer.parseInt(qty));
				try {
					cartItemService.update(cartItem);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
			resp.sendRedirect(req.getContextPath() + "/cart");
		}
		HttpSession session = req.getSession();
		int cartItemCount = 0;
		if (session != null && session.getAttribute("account") != null) 
		{
			User u = (User) session.getAttribute("account"); 
			Cart cart = cartService.findByUser(u.getId());
			if (cart == null) 
			{
				cartItemCount=0;
				req.setAttribute("alert", "Your shopping cart is currently empty. Start shopping now!");
			} 
			else 
			{
				Set<CartItem> listCartItem = cart.getCartItems();
				cartItemCount = listCartItem.size();
			}
		}
		req.getSession().setAttribute("cartItemCount", cartItemCount);
	}
}
