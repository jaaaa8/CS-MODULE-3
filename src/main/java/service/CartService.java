//package service;
//
//import dto.CartItemDto;
//import repository.CartRepository;
//import repository.ICartRepository;
//
//import java.util.List;
//
//public class CartService implements ICartService {
//
//    private final ICartRepository cartRepository = new CartRepository();
//
//    @Override
//    public void addToCart(int customerId, int bookId, int quantity) {
//
//        Integer cartId = cartRepository.findCartIdByCustomer(customerId);
//
//        if (cartId == null) {
//            cartId = cartRepository.createCart(customerId);
//        }
//
//        if (cartRepository.existsItem(cartId, bookId)) {
//            cartRepository.updateQuantity(cartId, bookId, quantity);
//        } else {
//            cartRepository.addItem(cartId, bookId, quantity);
//        }
//    }
//
//    @Override
//    public List<CartItemDto> getCart(int customerId) {
//        return cartRepository.getCartItems(customerId);
//    }
//}
