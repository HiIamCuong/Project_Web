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

@WebServlet(urlPatterns = { "/product-details", "/search-products", "/new-arrivals" })
public class ProductController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private IProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productIdParam = req.getParameter("id");

        if (productIdParam != null) {
            handleProductDetails(req, resp, productIdParam);
        } else if (req.getServletPath().equals("/new-arrivals")) {
            handleNewArrivals(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Product ID is required");
        }     
    }

    private void handleProductDetails(HttpServletRequest req, HttpServletResponse resp, String productIdParam) throws ServletException, IOException {
        try {
            int productId = Integer.parseInt(productIdParam);
            Product product = productService.getProductById(productId);
            List<Product> relatedProducts = productService.getRelatedProducts(productId);
            req.setAttribute("product", product);
            req.setAttribute("relatedProducts", relatedProducts);
            req.getRequestDispatcher(Constant.PRODUCTS).forward(req, resp);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid product ID");
        }
    }

    private void handleNewArrivals(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> newArrivalProducts = productService.getNewArrivalProducts();
        System.out.println("Number of new arrival products: " + newArrivalProducts.size());
        req.setAttribute("newArrivalProducts", newArrivalProducts);
        req.getRequestDispatcher(Constant.NEW_ARRIVAL).forward(req, resp);
    }
}
