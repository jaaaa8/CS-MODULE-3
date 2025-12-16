package dto;

public class OrderItemDto {
    private int orderId;
    private String bookName;
    private int quantity;
    private double price;
    private double subtotal;

    public OrderItemDto() {
    }

    public OrderItemDto(int orderId, String bookName, int quantity, double price) {
        this.orderId = orderId;
        this.bookName = bookName;
        this.quantity = quantity;
        this.price = price;
        this.subtotal = quantity * price;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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


}
