package UTESHOP.controllers.admin;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import UTESHOP.entity.Address;
import UTESHOP.entity.Cart;
import UTESHOP.entity.CartItem;
import UTESHOP.entity.Order;
import UTESHOP.entity.OrderDetail;
import UTESHOP.entity.PaymentMethod;
import UTESHOP.entity.Product;
import UTESHOP.entity.Promote;
import UTESHOP.entity.User;
import UTESHOP.services.IAddressService;
import UTESHOP.services.ICartItemService;
import UTESHOP.services.ICartService;
import UTESHOP.services.IOrderDetailService;
import UTESHOP.services.IOrderService;
import UTESHOP.services.IPaymentService;
import UTESHOP.services.IProductService;
import UTESHOP.services.IPromoteService;
import UTESHOP.services.IUserService;
import UTESHOP.services.implement.AddressService;
import UTESHOP.services.implement.CartItemService;
import UTESHOP.services.implement.CartService;
import UTESHOP.services.implement.OrderDetailService;
import UTESHOP.services.implement.OrderService;
import UTESHOP.services.implement.PaymentService;
import UTESHOP.services.implement.ProductService;
import UTESHOP.services.implement.PromoteService;
import UTESHOP.services.implement.UserService;
import UTESHOP.utils.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;



@WebServlet(urlPatterns = { "/admin/checkout", "/admin/checkout/updateaddress", "/admin/order" })
public class CheckoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public ICartService cartService = new CartService();
	public ICartItemService cartItemService = new CartItemService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		if (url.contains("/admin/checkout")) {
			HttpSession session = req.getSession();
			User user = (User) session.getAttribute("account");
			req.setAttribute("user", user);

			IPaymentService paymentService = new PaymentService();
			List<PaymentMethod> listPayment = paymentService.findPaymentActive();
			req.setAttribute("listPayment", listPayment);

			Cart cart = cartService.findByUser(user.getId());
			Set<CartItem> listCartItem = cart.getCartItems();
			req.setAttribute("listCartItem", listCartItem);
			
			int total = cartItemService.totalPrice(listCartItem);
			req.setAttribute("total", total);
			
			IPromoteService promoteService = new PromoteService();
			List<Promote> listPromote = promoteService.findPromoteForOrder(total);
			req.setAttribute("listPromote", listPromote);
			
			req.getRequestDispatcher(Constant.CHECKOUT_ADMIN).forward(req, resp);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		if (url.contains("/admin/checkout/updateaddress")) {
			String city = req.getParameter("city");
			String district = req.getParameter("district");
			String ward = req.getParameter("ward");
			String detail = req.getParameter("detail");
			int address_id = Integer.parseInt(req.getParameter("address_id"));
			
			IAddressService addressService = new AddressService();
			Address address = new Address(address_id, city, district, ward, detail);
			addressService.update(address);
			
			HttpSession session = req.getSession();
			User user = (User) session.getAttribute("account");
			user.setAddress(address);
			session.setAttribute("account", user);
			
			resp.sendRedirect(req.getContextPath() + "/admin/checkout");
		} else if (url.contains("/admin/order")) {
			Order order = new Order();
			
			int user_id = Integer.parseInt(req.getParameter("user_id"));
			IUserService userService = new UserService();
			User user = userService.findById(user_id);
			order.setUser(user);
			
			int total_price = Integer.parseInt(req.getParameter("total_price"));
			order.setTotal_price(total_price);
			
			order.setOrder_date(new Date());
			
			order.setStatus("Pending");
			
			String note = req.getParameter("noteOrder");
			order.setNote(note);
			
			int payment_id = Integer.parseInt(req.getParameter("payment"));
			IPaymentService paymentService = new PaymentService();
			PaymentMethod payment = paymentService.findById(payment_id);
			order.setPayment(payment);
			
			String promoteIdString = req.getParameter("promote");
			if (promoteIdString != null) {
				int id = Integer.parseInt(promoteIdString);
				IPromoteService promoteService = new PromoteService();
				Promote promote = promoteService.findById(id);
				order.setPromote(promote);
				
				promote.setQuantityUsed(promote.getQuantityUsed()+1);
				promoteService.update(promote);
			}
			
			IOrderService orderService = new OrderService();
			Order newOrder = orderService.insert(order);
			
			Cart cart = cartService.findByUser(user_id);
			Set<CartItem> setCartItem = cart.getCartItems();
			
			IOrderDetailService detailService = new OrderDetailService();
			for (CartItem x:setCartItem) {
				OrderDetail orderDetail = new OrderDetail();
				
				IProductService productService = new ProductService();
				Product product = x.getProduct();
				product.setQuantity(product.getQuantity()-1);
				productService.update(product);
				
				orderDetail.setProduct(x.getProduct());
				orderDetail.setQuantity(x.getQuantity());
				orderDetail.setOrder(newOrder);
				orderDetail.setPrice(x.getProduct().getPrice());
				orderDetail.setSize(x.getSize());
				detailService.insert(orderDetail);
			}
			
			try {
				cartService.delete(cart.getCart_id());
			} catch (Exception e) {
				e.printStackTrace();
			}
			req.setAttribute("payment", payment);
			req.getRequestDispatcher(Constant.ORDER_SUCCESS_ADMIN).forward(req, resp);
		}
	}
}
