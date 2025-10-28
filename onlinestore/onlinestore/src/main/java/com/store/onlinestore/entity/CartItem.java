package com.store.onlinestore.entity;

import jakarta.persistence.Column;  // CHANGED from javax to jakarta
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "cart")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "product_id")
    private Long productId;
    
    @Column(name = "session_id")
    private String sessionId;
    
    private Integer quantity = 1;
    
    // Transient fields (not stored in database)
    @Transient
    private String productName;
    
    @Transient
    private Double price;
    
    @Transient
    private String imageUrl;
    
    // Constructors
    public CartItem() {}
    
    public CartItem(Long productId, String sessionId, Integer quantity) {
        this.productId = productId;
        this.sessionId = sessionId;
        this.quantity = quantity;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
    
    public String getSessionId() { return sessionId; }
    public void setSessionId(String sessionId) { this.sessionId = sessionId; }
    
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    
    public Double getTotalPrice() {
        return price != null ? price * quantity : 0.0;
    }
}