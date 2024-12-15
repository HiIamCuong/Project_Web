package UTESHOP.controllers.admin.users;

import java.io.IOException;
import java.time.LocalDateTime;
import UTESHOP.entity.Address;
import UTESHOP.entity.User;
import UTESHOP.services.IAddressService;
import UTESHOP.services.IRoleService;
import UTESHOP.services.IUserService;
import UTESHOP.services.implement.AddressService;
import UTESHOP.services.implement.RoleService;
import UTESHOP.services.implement.UserService;
import UTESHOP.utils.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet(urlPatterns = {"/admin/user/add"})
public class AddUserController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	IUserService userService= new UserService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(Constant.ADMIN_ADD_USER).forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String email = req.getParameter("email");
		String fullname = req.getParameter("fullname");
		String phone = req.getParameter("phone");
		String password = req.getParameter("password");
		String role = req.getParameter("role");
		String city = req.getParameter("city");
		String district = req.getParameter("district");
		String ward = req.getParameter("ward");
		String detail = req.getParameter("detail");
		String status = req.getParameter("status");
		String image="https://static.vecteezy.com/system/resources/previews/009/749/751/non_2x/avatar-man-icon-cartoon-male-profile-mascot-illustration-head-face-business-user-logo-free-vector.jpg";
		User user = userService.findByEmail(email);
		
		String alertMsg = "";
		if (user == null) {
			if (userService.checkExistPhone(phone)) {
				alertMsg = "For security reasons, this phone number has been linked to another account. Please provide a unique phone number to proceed with registration!";
				req.setAttribute("alert", alertMsg);
				req.getRequestDispatcher(Constant.ADMIN_ADD_USER).forward(req, resp);
				return;
			}
						
			IAddressService addressService = new AddressService();
			Address address = new Address(city, district, ward, detail);
			Address newAddress = addressService.insert(address);
					
			IRoleService roleService = new RoleService();
			User newUser = new User(fullname, email, null, password, phone, 1, LocalDateTime.now(), roleService.findById(2), newAddress);
			if ("Admin".equals(role))
	        {
				newUser.setRole(roleService.findById(1));
	        }
			
			newUser.setImage(image);
			userService.insert(newUser);
			alertMsg = "Add User successfully!";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher(Constant.ADMIN_USER).forward(req, resp);
		}
	}

}
