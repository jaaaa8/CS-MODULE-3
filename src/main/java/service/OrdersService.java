package service;

import dto.OrdersDto;
import entity.Orders;
import repository.OrderItemRepository;
import repository.OrdersRepository;
import repository.impl.IOrderItemRepository;
import repository.impl.IOrderRepository;
import service.impl.IOrdersService;
import util.ConnectDB;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrdersService implements IOrdersService {
    private final IOrderRepository ordersRepository = new OrdersRepository();
    private final IOrderItemRepository orderItemRepository = new OrderItemRepository();

    @Override
    public List<OrdersDto> findAllOrders() {
        return ordersRepository.findAllOrders();
    }

    @Override
    public boolean deleteOrder(int orderId) {
        return ordersRepository.deleteOrder(orderId);
    }

    @Override
    public Orders findOrderById(int orderId) {
        return ordersRepository.findOrderById(orderId);
    }

    @Override
    public boolean confirmOrder(int orderId, int adminId) {
        return ordersRepository.confirmOrder(orderId, adminId);
    }

    @Override
    public boolean updateOrderStatus(int orderId, String status) {
        return ordersRepository.updateOrderStatus(orderId, status);
    }

    @Override
    public Orders findCartByCustomerId(int customerId) {
        return ordersRepository.findCartByCustomerId(customerId);
    }

    @Override
    public int createCartForCustomer(int customerId) {
        return ordersRepository.createCartForCustomer(customerId);
    }

    @Override
    public boolean checkout(int customerId) {
        Connection conn = null;
        try {
            conn = ConnectDB.getConnection();
            conn.setAutoCommit(false);
            Orders cart = ordersRepository.findCartByCustomerId(customerId);

            if (cart == null) {
                conn.rollback();
                return false;
            }
            int orderId = cart.getId();
            double total = orderItemRepository.calculateTotalPrice(orderId);

            if (total < 0) {
                conn.rollback();
                return false;
            }
            boolean updated = ordersRepository.updateOrderStatusAndTotalPrice(orderId, total, "PENDING");
            if (!updated) {
                conn.rollback();
                return false;
            }
            conn.commit();
            return true;

        } catch (Exception e) {
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return false;

        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
