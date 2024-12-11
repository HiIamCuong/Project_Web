package UTESHOP.controllers;

import java.io.IOException;
import java.time.LocalDateTime;

import UTESHOP.dao.implement.SendMail;
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
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/register"})
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public IUserService userService = new UserService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(Constant.REGISTER).forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String email = req.getParameter("email");
		String fullname = req.getParameter("fullname");
		String phone = req.getParameter("phone");
		String password = req.getParameter("password");
		String city = req.getParameter("city");
		String district = req.getParameter("district");
		String ward = req.getParameter("ward");
		String detail = req.getParameter("detail");
		
		User user = userService.findByEmail(email);
		
		String alertMsg = "";
		if (user == null) {
			if (userService.checkExistPhone(phone)) {
				alertMsg = "For security reasons, this phone number has been linked to another account. Please provide a unique phone number to proceed with registration!";
				req.setAttribute("alert", alertMsg);
				req.getRequestDispatcher(Constant.REGISTER).forward(req, resp);
				return;
			}
			
			SendMail sm = new SendMail();
			String code = sm.getRandom();
			
			IAddressService addressService = new AddressService();
			Address address = new Address(city, district, ward, detail);
			Address newAddress = addressService.insert(address);
					
			IRoleService roleService = new RoleService();
			User userRegister = new User(fullname, email, code, password, phone, 0, LocalDateTime.now(), roleService.findById(2), newAddress);
			
			userService.insert(userRegister);
			HttpSession session = req.getSession();
			session.setAttribute("account", userRegister);
			boolean test = sm.SendEmail(userRegister);
			
			if (test) {
				resp.sendRedirect(req.getContextPath() + "/verifycodeaccount");
			} else {
				alertMsg = "There was an error while sending the email!";
				req.setAttribute("alert", alertMsg);
				req.setAttribute("fullname", fullname);
				req.setAttribute("email", email);
				req.setAttribute("phone", phone);
				req.getRequestDispatcher(Constant.REGISTER).forward(req, resp);
			}
		}	else {
			alertMsg = "For security reasons, this email address has been linked to another account. Please provide a unique email address to proceed with registration!";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher(Constant.REGISTER).forward(req, resp);
		}
	}
}
