package UTESHOP.controllers;

import java.io.IOException;

import UTESHOP.entity.Address;
import UTESHOP.entity.Cart;
import UTESHOP.entity.CartItem;
import UTESHOP.entity.Product;
import UTESHOP.entity.User;
import UTESHOP.services.ICartItemService;
import UTESHOP.services.ICartService;
import UTESHOP.services.IProductService;
import UTESHOP.services.implement.CartItemService;
import UTESHOP.services.implement.CartService;
import UTESHOP.services.implement.ProductService;
import UTESHOP.utils.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/addtocart"})
public class AddToCartController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public ICartService cartService = new CartService();
    public IProductService productService = new ProductService();
    public ICartItemService cartItemService = new CartItemService();
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("account");

        if (user == null) {
            String alertMsg = "User not logged in!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher(Constant.LOGIN).forward(req, resp);
            return;
        }

        // Lấy productId từ form submit
        String productIdStr = req.getParameter("id");
        if (productIdStr != null && !productIdStr.isEmpty()) {
            try {
                int productId = Integer.parseInt(productIdStr); // Convert từ String sang int
                Product product = productService.findById(productId);
                
                if (product != null) {
                    Cart cart = cartService.findByUser(user.getId());
                    if (cart == null) {
                        cart = new Cart();
                        cart.setUser(user);
                        cartService.insert(cart);
                    }

                    // Thêm sản phẩm vào giỏ hàng
                    CartItem cartItem = new CartItem();
                    cartItem.setProduct(product);
                    cartItem.setQuantity(1);
                    cartItem.setCart(cart);
                    cartItem.setSize(product.getSize());
                    cartItemService.insert(cartItem);

                    session.setAttribute("account", user);
                    req.setAttribute("alert", "Add successfully!");
                    req.getRequestDispatcher(Constant.HOME).forward(req, resp);
                } else {
                    req.setAttribute("alert", "Product not found!");
                    req.getRequestDispatcher(Constant.HOME).forward(req, resp);
                }
            } catch (NumberFormatException e) {
                req.setAttribute("alert", "Invalid product ID!");
                req.getRequestDispatcher(Constant.HOME).forward(req, resp);
            }
        } else {
            req.setAttribute("alert", "Product ID is missing!");
            req.getRequestDispatcher(Constant.HOME).forward(req, resp);
        }

        resp.sendRedirect(req.getContextPath() + "/home");
    }
}

