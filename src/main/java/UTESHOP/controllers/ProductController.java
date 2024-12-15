package UTESHOP.controllers;

import UTESHOP.services.IProductService;
import UTESHOP.services.implement.ProductService;
import UTESHOP.utils.Constant;
import UTESHOP.entity.Product;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = { "/product-details", "/search-products" })
public class ProductController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private IProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Retrieve the product ID from the request parameter
        String productIdParam = req.getParameter("id");

        if (productIdParam != null) {
            try {
                int productId = Integer.parseInt(productIdParam); // Convert the ID to an integer
                // Fetch the product from the service by ID
                Product product = productService.getProductById(productId);

                // Fetch related products based on the product ID
                List<Product> relatedProducts = productService.getRelatedProducts(productId);

                // Set the product and related products as request attributes
                req.setAttribute("product", product);
                req.setAttribute("relatedProducts", relatedProducts);

                // Forward the request to the JSP page for displaying the product and related products
                req.getRequestDispatcher(Constant.PRODUCTS).forward(req, resp);
            } catch (NumberFormatException e) {
                // Handle invalid ID format
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid product ID");
            }
        } else {
            // Handle case where no ID is provided
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Product ID is required");
        }     
    }
}
