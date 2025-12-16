package service;

import dto.OrderItemDto;
import entity.OrderItem;
import repository.OrderItemRepository;
import repository.impl.IOrderItemRepository;
import service.impl.IOrderItemService;

import java.util.List;

public class OrderItemService implements IOrderItemService {
    private final IOrderItemRepository orderItemService = new OrderItemRepository();
    @Override
    public boolean addItem(int orderId, int bookId, int quantity, double price) {
        return orderItemService.addItem(orderId, bookId, quantity, price);
    }

    @Override
    public boolean updateQuantity(int orderItemId, int quantity) {
        return orderItemService.updateQuantity(orderItemId, quantity);
    }

    @Override
    public boolean removeItem(int orderItemId) {
        return orderItemService.removeItem(orderItemId);
    }

    @Override
    public List<OrderItemDto> getItemsByOrderId(int orderId) {
        return orderItemService.getItemsByOrderId(orderId);
    }

    @Override
    public OrderItem findItemById(int orderItemId, int bookId) {
        return orderItemService.findItemById(orderItemId, bookId);
    }

    @Override
    public double calculateTotalPrice(int orderId) {
        return orderItemService.calculateTotalPrice(orderId);
    }

    @Override
    public boolean cancelItemsByOrderId(int orderId) {
        return orderItemService.cancelItemsByOrderId(orderId);
    }

    @Override
    public boolean increaseQuantity(int orderId, int bookId, int quantity) {
        return orderItemService.increaseQuantity(orderId, bookId, quantity);
    }

    @Override
    public boolean existItemInOrder(int orderId, int bookId) {
        return orderItemService.existItemInOrder(orderId, bookId);
    }


}
