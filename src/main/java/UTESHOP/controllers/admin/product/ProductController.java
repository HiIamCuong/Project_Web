package UTESHOP.controllers.admin.product;
import java.io.IOException;
import java.util.List;

import UTESHOP.entity.Product;
import UTESHOP.entity.User;
import UTESHOP.services.IProductService;

import UTESHOP.services.implement.ProductService;

import UTESHOP.utils.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = { "/admin/product","/admin/deleteProduct"})
public class ProductController  extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IProductService productService= new ProductService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		if (url.contains("product")) 
		{
			HttpSession session = req.getSession();
			User user = (User) session.getAttribute("account");			
			if (user == null) {
				resp.sendRedirect(req.getContextPath() + "/login");
	            return;
			} 
			else 
			{
				List<Product> list = productService.findAll();
				req.setAttribute("listAllProduct", list);
			}
			req.getRequestDispatcher(Constant.ADMIN_PRODUCT).forward(req, resp);
		}
		else if (url.contains("/deleteProduct"))
		{
			String id = req.getParameter("id");
			try {
				productService.delete(Integer.parseInt(id));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			resp.sendRedirect(req.getContextPath() + "/admin/product");
		}
	}
}
