package UTESHOP.controllers.admin.users;
import java.io.IOException;
import java.util.List;
import UTESHOP.entity.User;
import UTESHOP.services.IUserService;
import UTESHOP.services.implement.UserService;
import UTESHOP.utils.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = { "/admin/user","/admin/deleteUser"})
public class UserController  extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IUserService userService= new UserService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		if (url.contains("user")) 
		{
			HttpSession session = req.getSession();
			User user = (User) session.getAttribute("account");			
			if (user == null) {
				resp.sendRedirect(req.getContextPath() + "/login");
	            return;
			} 
			else 
			{
				List<User> list = userService.findAll();
				list.removeIf(User -> User.getId() == user.getId());
				req.setAttribute("listAllUser", list);
			}
			req.getRequestDispatcher(Constant.ADMIN_USER).forward(req, resp);
		}
		else if (url.contains("/deleteUser"))
		{
			String id = req.getParameter("id");
			try {
				userService.delete(Integer.parseInt(id));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			resp.sendRedirect(req.getContextPath() + "/admin/user");
		}
	}
}
