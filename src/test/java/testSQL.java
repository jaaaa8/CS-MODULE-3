import entity.Account;
import entity.Customer;
import entity.Orders;
import repository.AccountRepository;
import service.CustomerService;
import service.OrderItemService;
import service.OrderService;

public class testSQL {
    public static void main(String[] args) {
        OrderService orderService = new OrderService();
        OrderItemService orderItemService = new OrderItemService();
        CustomerService customerService = new CustomerService();
        Customer customer = new Customer(1,1,"John Doe","as@gmail.comm","1234567890","123 Main St");
        customerService.add(customer);

        Orders orders = orderService.findCartByCustomerId(1);
        System.out.println("Cart created for customer with account ID 1" + orders);
    }
}
