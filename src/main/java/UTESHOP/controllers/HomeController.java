package UTESHOP.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import UTESHOP.entity.Cart;
import UTESHOP.entity.CartItem;
import UTESHOP.entity.Product;
import UTESHOP.entity.User;
import UTESHOP.services.ICartService;
import UTESHOP.services.IProductService;
import UTESHOP.services.implement.CartService;
import UTESHOP.services.implement.ProductService;
import UTESHOP.utils.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet(urlPatterns = {"/home"})
public class HomeController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public ICartService cartService = new CartService();
    private IProductService productService = new ProductService();
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
		// Fetch the product list from the database
        List<Product> products = productService.getAllProducts();
        //Fetch best-selling products
        List<Product> bestSellingProducts = productService.getBestSellingProducts();
        
        
        // Set the products as a request attribute so it can be accessed in JSP
        req.setAttribute("products", products);
        // Set the best-selling products as a request attribute
        req.setAttribute("bestSellingProducts", bestSellingProducts);
        
        
		req.getSession().setAttribute("cartItemCount", cartItemCount);
		req.getRequestDispatcher(Constant.HOME).forward(req, resp);
	}

}