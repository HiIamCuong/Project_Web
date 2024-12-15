package UTESHOP.controllers.admin.product;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import UTESHOP.dao.ICategoryDao;
import UTESHOP.dao.implement.CategoryDao;
import UTESHOP.entity.Address;
import UTESHOP.entity.Category;
import UTESHOP.entity.PaymentMethod;
import UTESHOP.entity.Product;
import UTESHOP.entity.User;
import UTESHOP.services.IAddressService;
import UTESHOP.services.ICategoryService;
import UTESHOP.services.IPaymentService;
import UTESHOP.services.IProductService;
import UTESHOP.services.IRoleService;
import UTESHOP.services.IUserService;
import UTESHOP.services.implement.AddressService;
import UTESHOP.services.implement.CategoryService;
import UTESHOP.services.implement.PaymentService;
import UTESHOP.services.implement.ProductService;
import UTESHOP.services.implement.RoleService;
import UTESHOP.services.implement.UserService;
import UTESHOP.utils.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet(urlPatterns = {"/admin/product/add"})
public class AddProductController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	IProductService productService= new ProductService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ICategoryDao categoryService = new CategoryDao();
		List<Category> listCategory = categoryService.findAll();
		req.setAttribute("listCategory", listCategory);
		req.getRequestDispatcher(Constant.ADMIN_ADD_PRODUCT).forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String name = req.getParameter("name");
	    String image = req.getParameter("images");
	    String price = req.getParameter("price");
	    String material = req.getParameter("material");
	    String color = req.getParameter("color");
	    String size = req.getParameter("size");
	    String quantity = req.getParameter("quantity");
	    String description = req.getParameter("description");
	    String status = req.getParameter("status");
	    String category = req.getParameter("category");
	    
		String alertMsg = "";
		if (category == null || category.isEmpty()) {
		    alertMsg = "Please select a category.";
		    req.setAttribute("alert", alertMsg);
		    req.getRequestDispatcher(Constant.ADMIN_ADD_PRODUCT).forward(req, resp);
		    return; // Ngừng xử lý nếu không có danh mục được chọn
		}
		Product newProduct = new Product();
		newProduct.setName(name);
		if (image.substring(0,5) =="https")
    	{newProduct.setImage(image);}
    	else
    	{newProduct.setImage("uploads/"+image);}
		newProduct.setPrice(Integer.parseInt(price));
		newProduct.setMaterial(material);
		newProduct.setColor(color);
		newProduct.setCreateDate(LocalDateTime.now());
		newProduct.setSize(size);
		newProduct.setQuantity(Integer.parseInt(quantity));
		newProduct.setDescription(description);
		ICategoryService categoryService= new CategoryService();
		if (category==null)
		{
			newProduct.setCategory(categoryService.findById(Integer.parseInt("1")));
		}
		else
		{
			newProduct.setCategory(categoryService.findById(Integer.parseInt(category)));
		}
		
			if ("1".equals(status))
	        {
				newProduct.setStatus(1);
	        }
			else
			{
				newProduct.setStatus(0);
			}
			if ("".equals(image))
			{
				newProduct.setImage(null);
			}
			productService.insert(newProduct);
			alertMsg = "Add Product successfully!";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher(Constant.ADMIN_PRODUCT).forward(req, resp);		
	}

}
