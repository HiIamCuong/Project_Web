package UTESHOP.controllers.admin.product;

import java.io.IOException;


import UTESHOP.entity.Product;

import UTESHOP.services.IProductService;


import UTESHOP.services.implement.ProductService;


import UTESHOP.utils.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet(urlPatterns = {"/admin/product/edit"})
public class EditProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IProductService productService= new ProductService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id =Integer.parseInt(req.getParameter("id"));
		Product product = productService.findById(id);
		req.setAttribute("product", product);
		req.getRequestDispatcher(Constant.ADMIN_EDIT_PRODUCT).forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        
        int id =Integer.parseInt(req.getParameter("id"));     
        Product product = productService.findById(id);

        if (product == null) {
            String alertMsg = "Product not find!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher(Constant.ADMIN_PRODUCT).forward(req, resp);
            return;
        }     
        String name = req.getParameter("productName");
        String image = req.getParameter("images");
        String price = req.getParameter("price");
        String material = req.getParameter("material");
        String color = req.getParameter("color");
        String size = req.getParameter("size");
        String quantity = req.getParameter("quantity");
        String description = req.getParameter("description");
        String status = req.getParameter("status");
        String alertMsg = "";
     // Cập nhật thông tin người dùng
        if (image!= "")
        {
        	if (image.substring(0,5) =="https")
        	{product.setImage(image);}
        	else
        	{product.setImage("uploads/"+image);}
        	
        } 	
        
        if ("1".equals(status))
        {
        	product.setStatus(1);
        }
        else
        {
        	product.setStatus(0);
        }
        product.setName(name);
        product.setPrice(Integer.parseInt(price));
        product.setMaterial(material);
        product.setColor(color);
        product.setSize(size);
        product.setQuantity(Integer.parseInt(quantity));
        product.setDescription(description);
        // Cập nhật thông tin
        productService.update(product);
        alertMsg = "Product updated successfully!";
        req.setAttribute("alert", alertMsg);
        req.getRequestDispatcher(Constant.ADMIN_PRODUCT).forward(req, resp);
	}

}
