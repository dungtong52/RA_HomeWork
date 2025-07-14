package entity;

import java.math.BigDecimal;

public class Order {
    private int id;
    private int customerId;
    private BigDecimal totalAmount;
    private int productId;
    private int quantity;

    public Order() {
    }

    public Order(int id, int customerId, BigDecimal totalAmount, int productId, int quantity) {
        this.id = id;
        this.customerId = customerId;
        this.totalAmount = totalAmount;
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
