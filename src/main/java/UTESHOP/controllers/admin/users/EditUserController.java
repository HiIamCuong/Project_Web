package UTESHOP.controllers.admin.users;

import java.io.IOException;

import UTESHOP.entity.Address;
import UTESHOP.entity.User;
import UTESHOP.services.IRoleService;
import UTESHOP.services.IUserService;
import UTESHOP.services.implement.RoleService;
import UTESHOP.services.implement.UserService;
import UTESHOP.utils.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet(urlPatterns = {"/admin/user/edit"})
public class EditUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IUserService userService= new UserService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id =Integer.parseInt(req.getParameter("id"));
		User user = userService.findById(id);
		req.setAttribute("user", user);
		req.getRequestDispatcher(Constant.ADMIN_EDIT_USER).forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        
        int id =Integer.parseInt(req.getParameter("id"));     
        User user = userService.findById(id);

        if (user == null) {
            String alertMsg = "User not find!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher(Constant.ADMIN_USER).forward(req, resp);
            return;
        }
        String image = req.getParameter("images");
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
        String alertMsg = "";
        // Kiểm tra email và số điện thoại có bị trùng hay không
        if (!email.equals(user.getEmail())) {
            alertMsg = "Email is already in use!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher(Constant.ADMIN_EDIT_USER).forward(req, resp);
            return;
        }
        if (!phone.equals(user.getPhone()) && userService.checkExistPhone(phone)) {
            alertMsg = "Phone number is already in use!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher(Constant.ADMIN_EDIT_USER).forward(req, resp);
            return;
        }

     // Cập nhật thông tin người dùng
        if ("1".equals(status))
        {
        	user.setStatus(1);
        }
        else
        {
        	user.setStatus(0);
        }
        user.setEmail(email);
        user.setFullname(fullname);
        user.setPhone(phone);
        user.setPassword(password);
        if (image!= "")
        {
        	if (image.substring(0,5) =="https")
        	{user.setImage(image);}
        	else
        	{user.setImage("uploads/"+image);}
        	
        } 	
        if ("Admin".equals(role))
        {
        	IRoleService roleService = new RoleService();
        	user.setRole(roleService.findById(1));
        }
        else if ("User".equals(role))
        {
        	IRoleService roleService = new RoleService();
        	user.setRole(roleService.findById(2));
        }
        // Tạo hoặc cập nhật Address
        Address address = user.getAddress(); // Lấy Address hiện tại của User nếu có
        address.setCity(city);
        address.setDistrict(district);
        address.setWard(ward);
        address.setDetail(detail);

        // Gán lại Address cho User
        user.setAddress(address);

        // Cập nhật thông tin người dùng
        userService.update(user);

        alertMsg = "User updated successfully!";
        req.setAttribute("alert", alertMsg);
        req.getRequestDispatcher(Constant.ADMIN_USER).forward(req, resp);
	}

}
