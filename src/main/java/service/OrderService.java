package service;

import entity.Orders;
import repository.IRepostitory;
import repository.OrderRepository;
import service.impl.IOrderRepository;
import service.impl.IOrderService;

import java.util.List;

public class OrderService implements IOrderService {
    IOrderRepository orderRepository = new OrderRepository();

    @Override
    public List<Orders> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public boolean add(Orders orders) {
        return orderRepository.add(orders);
    }

    @Override
    public boolean delete(int id) {
        return orderRepository.delete(id);
    }

    @Override
    public boolean update(Orders orders) {
        return orderRepository.update(orders);
    }

    @Override
    public boolean search(Orders orders) {
        return orderRepository.search(orders);
    }

    @Override
    public Orders findById(int id) {
        return orderRepository.findById(id);
    }
    @Override
    public boolean updateStatus(int orderId, String newStatus) {
        int fakeAdminId = 1;
        if ("CONFIRMED".equalsIgnoreCase(newStatus)) {
            return orderRepository.updateStatus(orderId, newStatus, fakeAdminId);
        } else {
            // Dùng hàm đơn giản hơn nếu không cần confirmedBy
            // Lưu ý: Nếu bạn muốn logic chính xác, bạn nên thay đổi IService để chấp nhận confirmedByAccountId
            return orderRepository.updateStatus(orderId, newStatus, 0); // Dùng 0 hoặc NULL cho các trạng thái khác
        }
    }
}
