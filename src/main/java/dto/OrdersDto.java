package dto;

import java.util.Date;

public class OrdersDto {
    private int id;
    private String customerName;
    private String status;
    private double total;
    private Date createAt;
    private String confirmByName;

    public OrdersDto(int id, String customerName, String status, double totalPrice, Date createAt, String confirmByName) {
        this.id = id;
        this.customerName = customerName;
        this.status = status;
        this.total = totalPrice;
        this.createAt = createAt;
        this.confirmByName = confirmByName;
    }

    public String getConfirmByName() {
        return confirmByName;
    }

    public void setConfirmByName(String confirmByName) {
        this.confirmByName = confirmByName;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double totalPrice) {
        this.total = totalPrice;
    }
}
