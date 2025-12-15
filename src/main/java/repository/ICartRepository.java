//package repository;
//
//import dto.CartItemDto;
//
//import java.util.List;
//
//public interface ICartRepository {
//
//    Integer findCartIdByCustomer(int customerId);
//
//    int createCart(int customerId);
//
//    boolean existsItem(int cartId, int bookId);
//
//    void addItem(int cartId, int bookId, int quantity);
//
//    void updateQuantity(int cartId, int bookId, int quantity);
//
//    List<CartItemDto> getCartItems(int customerId);
//}
