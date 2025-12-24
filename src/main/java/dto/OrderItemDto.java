package dto;

public class OrderItemDto {
    private int orderItemId;
    private String bookName;
    private int quantity;
    private double price;
    private double subtotal;
    private int stock;  // Thêm field stock để kiểm soát số lượng

    public OrderItemDto() {
    }

    public OrderItemDto(int orderItemId, String bookName, int quantity, double price) {
        this.orderItemId = orderItemId;
        this.bookName = bookName;
        this.quantity = quantity;
        this.price = price;
        this.subtotal = quantity * price;
        this.stock = quantity;  // Mặc định stock = quantity
    }

    public OrderItemDto(int orderItemId, String bookName, int quantity, double price, int stock) {
        this.orderItemId = orderItemId;
        this.bookName = bookName;
        this.quantity = quantity;
        this.price = price;
        this.subtotal = quantity * price;
        this.stock = stock;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
