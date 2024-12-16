package UTESHOP.controllers;

import jakarta.servlet.ServletException;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import UTESHOP.entity.Product; // Ensure the import is correct
import UTESHOP.services.implement.ProductService;
import UTESHOP.utils.Constant;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query = request.getParameter("query"); // Get the search query
        String category = request.getParameter("category");

        // Get the page parameter, defaulting to 1 if not present
        String pageParam = request.getParameter("page");
        int page = 1; // Default to page 1
        if (pageParam != null && !pageParam.isEmpty()) {
            try {
                page = Integer.parseInt(pageParam);
                if (page < 1) {
                    page = 1; // Ensure page number is at least 1
                }
            } catch (NumberFormatException e) {
                page = 1; // Default to 1 if parsing fails
            }
        }

        int pageSize = 10; // Set your desired page size

        ProductService productService = new ProductService();
        List<Product> searchResults = productService.searchProducts(query, category, page, pageSize); // Call the service to perform the search

        int totalPages = productService.countTotalPages(pageSize, query, category); // Total pages
        request.setAttribute("searchResults", searchResults); // Store results in the request
        request.setAttribute("query", query); // Store the original query for display
        request.setAttribute("category", category); // Optionally retain category
        request.setAttribute("currentPage", page); // Current page for pagination
        request.setAttribute("totalPages", totalPages); // Total pages
        
        request.getRequestDispatcher(Constant.SEARCH_RESULT).forward(request, response); // Forward to results page
    }
}
