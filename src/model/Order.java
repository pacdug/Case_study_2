package model;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
    private int orderId;
    private User user;
    private Product product;
    private Date orderDate;

    public Order(int orderId, User user, Product product, Date orderDate) {
        this.orderId = orderId;
        this.user = user;
        this.product = product;
        this.orderDate = orderDate;
    }

    // Getters và Setters
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", user=" + user +
                ", product=" + product +
                ", orderDate=" + orderDate +
                '}';
    }
}

