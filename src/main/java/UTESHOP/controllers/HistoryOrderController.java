package UTESHOP.controllers;

import UTESHOP.entity.Order;
import UTESHOP.services.implement.OrderService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/history")
public class HistoryOrderController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private OrderService orderService = new OrderService(); // Sử dụng OrderService

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> orders;

        // Lấy user_id từ session (giả sử rằng user_id được lưu trong session sau khi người dùng đăng nhập)
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("user_id");

        // Kiểm tra xem user_id có tồn tại không
        if (userId == null) {
            response.sendRedirect("login.jsp"); // Nếu không có user_id trong session, chuyển hướng tới trang đăng nhập
            return;
        }

        // Lấy tham số status từ request (nếu có)
        String statusParam = request.getParameter("status");
        int status = statusParam != null && !statusParam.isEmpty() ? Integer.parseInt(statusParam) : 0; // Mặc định là 0 (lấy tất cả)

        try {
            // Gọi hàm findByStatus của OrderService nếu status khác 0, nếu không thì lấy tất cả đơn hàng
            if (status != 0) {
                orders = orderService.findByUserIdAndStatus(userId, status); // Truyền user_id và status để lấy đơn hàng theo status
            } else {
                orders = orderService.findByUserId(userId); // Lấy tất cả đơn hàng của user
            }

            // Chuyển kết quả vào attribute của request để hiển thị trong JSP
            request.setAttribute("orders", orders);
            request.setAttribute("status", status); // Cung cấp giá trị status để biết trạng thái hiện tại trong JSP

            // Chuyển hướng tới JSP để hiển thị kết quả
            request.getRequestDispatcher("/views/historyOrder.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while fetching the order history.");
        }
    }
}
