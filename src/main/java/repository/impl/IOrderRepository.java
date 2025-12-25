package repository.impl;

import dto.OrdersDto;
import entity.Orders;

import java.util.List;

public interface IOrderRepository {
    List<OrdersDto> findAllOrders();
    boolean deleteOrder(int orderId);
    Orders findOrderById(int orderId);
    boolean confirmOrder(int orderId, int adminId);
    boolean updateOrderStatus(int orderId, String status);

    Orders findCartByCustomerId(int customerId);
    int createCartForCustomer(int customerId);

    boolean updateOrderStatusAndTotalPrice(int orderId, double totalPrice, String status);
    boolean updateStatusAndTotal(int orderId, double total);
    List<OrdersDto> search(String keyword);
}
