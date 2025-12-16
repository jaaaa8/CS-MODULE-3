package entity;

import java.util.Date;

public class Orders {
    private int id;
    private int customerId;
    private String status;
    private double totalPrice;
    private Date createAt;
    private int confirmById;

    public Orders( int id, int customerID, String status, double totalPrice, Date createAt, int confirmById) {
        this.confirmById = confirmById;
        this.createAt = createAt;
        this.customerId = customerID;
        this.id = id;
        this.status = status;
        this.totalPrice = totalPrice;
    }

    public Orders() {
    }

    public int getConfirmById() {
        return confirmById;
    }

    public void setConfirmById(int confirmById) {
        this.confirmById = confirmById;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

}
