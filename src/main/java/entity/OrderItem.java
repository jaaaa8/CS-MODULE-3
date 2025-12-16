package entity;

public class OrderItem {
    private int id;
    private int orderId;
    private int bookId;
    private int quantity;
    private double price;

    public OrderItem() {
    }

    public OrderItem(int orderId, int bookId, int quantity, double price) {
        this.bookId = bookId;
        this.orderId = orderId;
        this.price = price;
        this.quantity = quantity;
    }

    public OrderItem(int id,int orderId, int bookId, int quantity, double price) {
        this.id = id;
        this.bookId = bookId;
        this.orderId = orderId;
        this.price = price;
        this.quantity = quantity;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
