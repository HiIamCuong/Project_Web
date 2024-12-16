package UTESHOP.controllers.admin;

import java.io.IOException;
import java.util.List;

import UTESHOP.services.implement.OrderService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

@WebServlet("/admin/revenue")
public class RevenueController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private OrderService orderService;

    @Override
    public void init() throws ServletException {
        super.init();
        orderService = new OrderService(); // Khởi tạo service
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String timeUnit = request.getParameter("timeUnit");
        String date = request.getParameter("date");

        if (timeUnit == null || timeUnit.isEmpty()) {
            timeUnit = "day"; // Mặc định là theo ngày
        }

        // Gọi service để lấy dữ liệu thống kê
        List<Object[]> statistics = orderService.getRevenueByTime(date, timeUnit);

        // Truyền dữ liệu qua request
        request.setAttribute("statistics", statistics);
        
        double totalRevenue = orderService.getTotalRevenueByTime(date, timeUnit);

        // Truyền tổng doanh thu vào request
        request.setAttribute("totalRevenue", totalRevenue);

        // Forward đến JSP
        request.getRequestDispatcher("/views/admin/revenue.jsp").forward(request, response);
    }
    
    
}
