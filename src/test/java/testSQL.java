import entity.Customer;
import entity.Orders;
import service.CustomerService;
import service.OrderItemService;
import service.OrdersService;

public class testSQL {
    public static void main(String[] args) {
        OrdersService orderService = new OrdersService();

        int bo= orderService.createCartForCustomer(6);
        System.out.println("Cart created for customer with account ID 1" + bo);
    }
}
