package service.impl;

import entity.Orders;

import java.util.List;

public interface IOrderService {
    List<Orders> findAll();
    boolean add(Orders orders);
    boolean delete(int id);
    boolean update(Orders orders);
    boolean search(Orders orders);
    Orders findById(int id);
    boolean updateStatus(int orderId, String newStatus);
}
