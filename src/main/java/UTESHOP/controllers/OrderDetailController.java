package UTESHOP.controllers;

import UTESHOP.entity.OrderDetail;
import UTESHOP.services.implement.OrderDetailService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/history/details")
public class OrderDetailController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private OrderDetailService orderDetailService = new OrderDetailService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Lấy orderId từ request
            int orderId = Integer.parseInt(request.getParameter("orderId"));

            // Lấy danh sách chi tiết đơn hàng
            List<OrderDetail> orderDetails = orderDetailService.findByOrderId(orderId);

            // Đặt orderDetails vào attribute để gửi đến JSP
            request.setAttribute("orderDetails", orderDetails);

            // Forward đến JSP để hiển thị
            request.getRequestDispatcher("/views/orderDetails.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving order details.");
        }
    }
}
