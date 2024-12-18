package model;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
    private User user;
    private Product product;

    // Constructors, Getters, Setters
    public Order(User user, Product product) {
        this.user = user;
        this.product = product;
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
}

