package dto;

public class CartItemDto {
    private int orderItemId;
    private int bookId;
    private String title;
    private String imageUrl;
    private double price;
    private int quantity;

    public CartItemDto(int orderItemId, int bookId, String title,
                       String imageUrl, double price, int quantity) {
        this.orderItemId = orderItemId;
        this.bookId = bookId;
        this.title = title;
        this.imageUrl = imageUrl;
        this.price = price;
        this.quantity = quantity;
    }

    // getter
    public int getOrderItemId() { return orderItemId; }
    public int getBookId() { return bookId; }
    public String getTitle() { return title; }
    public String getImageUrl() { return imageUrl; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }

    public double getTotal() {
        return price * quantity;
    }
}
