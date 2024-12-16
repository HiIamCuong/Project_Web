package UTESHOP.dao.implement;

import java.util.ArrayList;
import java.util.List;

import UTESHOP.configs.JPAConfig;
import UTESHOP.dao.IOrderDao;
import UTESHOP.entity.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

public class OrderDao implements IOrderDao {

	@Override
	public Order insert(Order order) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.persist(order);
			Order newOrder = enma.merge(order);
			trans.commit();
			return newOrder;
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	@Override
	public List<Order> findAll() {
		EntityManager em = JPAConfig.getEntityManager();
        try {
            String jpql = "SELECT ord FROM Order ord";
            Query query = em.createQuery(jpql, Order.class);
            return query.getResultList();
        } finally {
            em.close();
        }
	}

	@Override
	public Order findById(int id) {
		 EntityManager em = JPAConfig.getEntityManager();
	        try {
	            return em.find(Order.class, id);
	        } finally {
	            em.close();
	        }
	}

	@Override
	public void insertFnc(Order order) {
		 EntityManager em = JPAConfig.getEntityManager();
	        try {
	            em.getTransaction().begin();
	            em.persist(order);
	            em.getTransaction().commit();
	        } finally {
	            em.close();
	        }
	}

	@Override
	public void update(Order order) {
		EntityManager em = JPAConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(order);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
	}

	@Override
	public void delete(int id) {
		 EntityManager em = JPAConfig.getEntityManager();
	        try {
	            em.getTransaction().begin();
	            Order order = em.find(Order.class, id);
	            if (order != null) {
	                em.remove(order);
	            }
	            em.getTransaction().commit();
	        } finally {
	            em.close();
	        }
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getRevenueByTime(String date, String timeUnit) {
	    EntityManager em = JPAConfig.getEntityManager();
	    String jpql = "";
	    List<Object[]> result = new ArrayList<>();

	    try {
	        switch (timeUnit) {
	            case "day":
	                // Lấy dữ liệu doanh thu trong một ngày, chia thành khoảng 4 giờ
	                jpql = "SELECT HOUR(o.order_date), SUM(o.total_price) " +
	                       "FROM Order o " +
	                       "WHERE DATE(o.order_date) = :date " +
	                       "GROUP BY HOUR(o.order_date)";
	                Query queryDay = em.createQuery(jpql, Object[].class);
	                queryDay.setParameter("date", date);
	                List<Object[]> dayResults = queryDay.getResultList();
	                
	                // Gom nhóm theo khoảng 4 giờ
	                double[] hourlyTotals = new double[6]; // 6 khoảng 4 giờ
	                for (Object[] row : dayResults) {
	                    int hour = (int) row[0];
	                    double total = (double) row[1];
	                    int bucket = hour / 4; // Nhóm theo 4 giờ
	                    hourlyTotals[bucket] += total;
	                }
	                for (int i = 0; i < 6; i++) {
	                    result.add(new Object[]{String.format("%02d:00-%02d:00", i * 4, (i + 1) * 4), hourlyTotals[i]});
	                }
	                break;

	            case "month":
	                // Lấy dữ liệu doanh thu trong một tháng, chia thành khoảng 5 ngày
	                jpql = "SELECT DAY(o.order_date), SUM(o.total_price) " +
	                       "FROM Order o " +
	                       "WHERE YEAR(o.order_date) = :year AND MONTH(o.order_date) = :month " +
	                       "GROUP BY DAY(o.order_date)";
	                String[] dateParts = date.split("-");
	                int year = Integer.parseInt(dateParts[0]);
	                int month = Integer.parseInt(dateParts[1]);
	                Query queryMonth = em.createQuery(jpql, Object[].class);
	                queryMonth.setParameter("year", year);
	                queryMonth.setParameter("month", month);
	                List<Object[]> monthResults = queryMonth.getResultList();

	                // Gom nhóm theo 5 ngày
	                double[] dailyTotals = new double[6]; // 6 khoảng 5 ngày
	                for (Object[] row : monthResults) {
	                    int day = (int) row[0];
	                    double total = (double) row[1];
	                    int bucket = (day - 1) / 5; // Nhóm theo 5 ngày
	                    dailyTotals[bucket] += total;
	                }
	                for (int i = 0; i < 6; i++) {
	                    result.add(new Object[]{String.format("Day %d-%d", i * 5 + 1, (i + 1) * 5), dailyTotals[i]});
	                }
	                break;

	            case "year":
	                // Lấy dữ liệu doanh thu trong một năm, chia thành khoảng 2 tháng
	                jpql = "SELECT MONTH(o.order_date), SUM(o.total_price) " +
	                       "FROM Order o " +
	                       "WHERE YEAR(o.order_date) = :year " +
	                       "GROUP BY MONTH(o.order_date)";
	                Query queryYear = em.createQuery(jpql, Object[].class);
	                year = Integer.parseInt(date);
	                queryYear.setParameter("year", year);
	                List<Object[]> yearResults = queryYear.getResultList();

	                // Gom nhóm theo 2 tháng
	                double[] monthlyTotals = new double[6]; // 6 khoảng 2 tháng
	                for (Object[] row : yearResults) {
	                    int monthValue = (int) row[0];
	                    double total = (double) row[1];
	                    int bucket = (monthValue - 1) / 2; // Nhóm theo 2 tháng
	                    monthlyTotals[bucket] += total;
	                }
	                for (int i = 0; i < 6; i++) {
	                    result.add(new Object[]{String.format("Month %d-%d", i * 2 + 1, (i + 1) * 2), monthlyTotals[i]});
	                }
	                break;

	            default:
	                throw new IllegalArgumentException("Invalid time unit: " + timeUnit);
	        }

	        return result;
	    } finally {
	        em.close();
	    }
	}
	
	@SuppressWarnings("unchecked")
	public List<Order> findByUserId(int userId) {
	    EntityManager em = JPAConfig.getEntityManager();
	    try {
	        if (userId == 0) {
	            return findAll(); // Lấy tất cả đơn hàng nếu userId là 0
	        } else {
	            // JPQL để tìm các đơn hàng của người dùng theo user_id
	            String jpql = "SELECT o FROM Order o WHERE o.user_id = :userId";
	            Query query = em.createQuery(jpql, Order.class);
	            query.setParameter("userId", userId);
	            return query.getResultList();
	        }
	    } finally {
	        em.close();
	    }
	}
	
	 // Hàm tìm đơn hàng theo order_id và status
    @SuppressWarnings("unchecked")
    public List<Order> findByUserIdAndStatus(int userId, int status) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            // JPQL để tìm đơn hàng theo user_id và status
            String jpql = "SELECT ord FROM Order ord WHERE ord.user_id = :userId AND ord.status = :status";
            Query query = em.createQuery(jpql, Order.class);
            query.setParameter("userId", userId);
            query.setParameter("status", status);
            return query.getResultList();  // Trả về danh sách các đơn hàng thỏa mãn
        } finally {
            em.close();
        }
    }
    
    public boolean updateStatus(int orderId, String newStatus) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = em.getTransaction();
            transaction.begin();

            // Tìm đơn hàng theo order_id
            Order order = em.find(Order.class, orderId);
            if (order != null) {
                // Cập nhật trạng thái
                order.setStatus(newStatus);
                em.merge(order);
            }

            transaction.commit();
            return true; // Cập nhật thành công
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback(); // Hoàn tác nếu có lỗi
            }
            e.printStackTrace();
            return false; // Cập nhật thất bại
        } finally {
            em.close();
        }
    }

    public double getTotalRevenueByTime(String date, String timeUnit) {
        EntityManager em = JPAConfig.getEntityManager();
        double totalRevenue = 0.0;
        String jpql = "";

        try {
            switch (timeUnit) {
                case "day":
                    // Tổng doanh thu trong ngày
                    jpql = "SELECT SUM(o.total_price) " +
                           "FROM Order o " +
                           "WHERE DATE(o.order_date) = :date";
                    Query queryDay = em.createQuery(jpql);
                    queryDay.setParameter("date", date);
                    totalRevenue = (Double) queryDay.getSingleResult();
                    break;

                case "month":
                    // Tổng doanh thu trong tháng
                    jpql = "SELECT SUM(o.total_price) " +
                           "FROM Order o " +
                           "WHERE YEAR(o.order_date) = :year AND MONTH(o.order_date) = :month";
                    String[] dateParts = date.split("-");
                    int year = Integer.parseInt(dateParts[0]);
                    int month = Integer.parseInt(dateParts[1]);
                    Query queryMonth = em.createQuery(jpql);
                    queryMonth.setParameter("year", year);
                    queryMonth.setParameter("month", month);
                    totalRevenue = (Double) queryMonth.getSingleResult();
                    break;

                case "year":
                    // Tổng doanh thu trong năm
                    jpql = "SELECT SUM(o.total_price) " +
                           "FROM Order o " +
                           "WHERE YEAR(o.order_date) = :year";
                    int yearOnly = Integer.parseInt(date);
                    Query queryYear = em.createQuery(jpql);
                    queryYear.setParameter("year", yearOnly);
                    totalRevenue = (Double) queryYear.getSingleResult();
                    break;

                default:
                    throw new IllegalArgumentException("Invalid time unit: " + timeUnit);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Nếu không có kết quả (null), tổng doanh thu sẽ là 0
            totalRevenue = 0.0;
        } finally {
            em.close();
        }

        return totalRevenue;
    }
}
