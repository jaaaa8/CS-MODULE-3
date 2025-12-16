package service.impl;

import entity.Orders;

import java.util.List;

public interface IOrderRepository {
    List<Orders> findAll();
    boolean add(Orders orders);
    boolean delete(int id);
    boolean update(Orders orders);
    boolean search(Orders orders);
    Orders findById(int id);
    boolean updateStatus(int orderId, String newStatus, int confirmedByAccountId);

    // Cần sửa lại phương thức này để khớp với IRepository (nếu bạn dùng nó)
    boolean updateStatus(int id, String newStatus);
}
