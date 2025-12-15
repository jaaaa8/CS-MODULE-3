package repository;

import entity.Orders;
import repository.impl.IOrderRepository;

import java.util.List;

public class OrdersRepository implements IOrderRepository {
    private final String SELECT_ALL_ORDERS = "SELECT * FROM orders";
    private final String INSERT_ORDER = "INSERT INTO orders (customer_id, status, total) VALUES (?, 'CART', ?)";
    private final String UPDATE_ORDER = "UPDATE orders SET customer_id = ?, order_date = ?, status = ? WHERE order_id = ?";
    private final String DELETE_ORDER = "DELETE FROM orders WHERE order_id = ?";
    private final String FIND_ORDER_BY_ID = "SELECT * FROM orders WHERE order_id = ?";
    private final String FIND_CART_BY_CUSTOMER_ID = "SELECT * FROM orders WHERE customer_id = ? AND status = 'CART'LIMIT 1";
    private final String CREATE_CART_FOR_CUSTOMER = "INSERT INTO orders (customer_id, status) VALUES (?, 'CART')";
    private final String CHECKOUT_ORDER = "UPDATE orders SET status = 'PENDING', total = ? WHERE order_id = ? AND status = 'CART'";
    private final String CONFIRM_ORDER = "UPDATE orders SET status = 'CONFIRMED', confirmed_by = ? WHERE order_id = ? AND status = 'PENDING'";

    @Override
    public List<Orders> findAllOrders() {
        return List.of();
    }

    @Override
    public boolean addOrder(Orders order) {
        return false;
    }

    @Override
    public boolean deleteOrder(int orderId) {
        return false;
    }

    @Override
    public Orders findOrderById(int orderId) {
        return null;
    }

    @Override
    public boolean confirmOrder(int orderId, int adminId) {
        return false;
    }

    @Override
    public boolean updateOrderStatus(int orderId, String status) {
        return false;
    }

    @Override
    public Orders findCartByCustomerId(int customerId) {
        return null;
    }

    @Override
    public int createCartForCustomer(int customerId) {
        return 0;
    }

    @Override
    public double calculateTotalPrice(int orderId) {
        return 0;
    }

    @Override
    public boolean checkoutOrder(int orderId, double totalPrice) {
        return false;
    }
}
