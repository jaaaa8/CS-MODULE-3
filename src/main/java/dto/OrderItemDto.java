package dto;

public class OrderItemDto {
    private int orderItemId;
    private int bookId;         // <-- Đã thêm field này
    private String bookName;
    private int quantity;
    private double price;
    private double subtotal;
    private int stock;

    public OrderItemDto() {
    }

    // Constructor đầy đủ
    public OrderItemDto(int orderItemId, int bookId, String bookName, int quantity, double price, int stock) {
        this.orderItemId = orderItemId;
        this.bookId = bookId;   // <-- Gán giá trị
        this.bookName = bookName;
        this.quantity = quantity;
        this.price = price;
        this.subtotal = quantity * price;
        this.stock = stock;
    }

    // Getter & Setter cho bookId
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    // ... Các getter/setter khác giữ nguyên ...
    public String getBookName() { return bookName; }
    public void setBookName(String bookName) { this.bookName = bookName; }
    public int getOrderItemId() { return orderItemId; }
    public void setOrderItemId(int orderItemId) { this.orderItemId = orderItemId; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public double getSubtotal() { return subtotal; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
}