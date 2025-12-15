package repository.impl;

import entity.Orders;

import java.util.List;

public interface IOrderRepository {
    List<Orders> findAllOrders();
    boolean addOrder(Orders order);
    boolean deleteOrder(int orderId);
    Orders findOrderById(int orderId);
    boolean confirmOrder(int orderId, int adminId);
    boolean updateOrderStatus(int orderId, String status);

    Orders findCartByCustomerId(int customerId);
    int createCartForCustomer(int customerId);

    double calculateTotalPrice(int orderId);
    boolean checkoutOrder(int orderId, double totalPrice);
}
