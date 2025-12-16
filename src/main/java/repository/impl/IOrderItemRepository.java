package repository.impl;

import dto.OrderItemDto;
import entity.OrderItem;

import java.util.List;


public interface IOrderItemRepository {
    boolean addItem(int orderId, int bookId, int quantity, double price);
    boolean updateQuantity(int orderItemId, int quantity);
    boolean removeItem(int orderItemId);
    List<OrderItemDto> getItemsByOrderId(int orderId);
    OrderItem findItemById(int orderItemId, int bookId);
    double calculateTotalPrice(int orderId);
    boolean cancelItemsByOrderId(int orderId);
    boolean increaseQuantity(int orderId, int bookId, int quantity);
    boolean existItemInOrder(int orderId, int bookId);
}
