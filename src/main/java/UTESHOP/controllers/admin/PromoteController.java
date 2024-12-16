package UTESHOP.controllers.admin;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import UTESHOP.entity.Promote;
import UTESHOP.services.implement.PromoteService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/promote")
public class PromoteController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private PromoteService promoteService = new PromoteService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String action = request.getParameter("action");
        try {
            if (action == null || action.equals("list")) {
                listPromotes(request, response);
            } else if (action.equals("delete")) {
                deletePromote(request, response);
            } else if (action.equals("add")) {
                addPromoteForm(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            savePromote(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }

    private void listPromotes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Promote> promotes = promoteService.findAll();
        request.setAttribute("promotes", promotes);
        request.getRequestDispatcher("/views/admin/Promote.jsp").forward(request, response);
    }

    private void addPromoteForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("promote", new Promote());
        request.getRequestDispatcher("/views/admin/AddPromote.jsp").forward(request, response);
    }

    private void savePromote(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Promote promote = new Promote();
        promote.setVoucherCode(request.getParameter("voucherCode"));
        promote.setStartDate(LocalDateTime.parse(request.getParameter("startDate")));
        promote.setEndDate(LocalDateTime.parse(request.getParameter("endDate")));
        promote.setDiscountPercent(Integer.parseInt(request.getParameter("discountPercent")));
        promote.setQuantity(Integer.parseInt(request.getParameter("quantity")));
        promote.setQuantityUsed(0); // Default 0
        promote.setMinOrderTotal(Double.parseDouble(request.getParameter("minOrderTotal")));

        promoteService.insert(promote);
        response.sendRedirect(request.getContextPath() + "/admin/promote?action=list");
    }

    private void deletePromote(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        promoteService.delete(id);
        response.sendRedirect(request.getContextPath() + "/admin/promote?action=list");
    }
}
