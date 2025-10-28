package com.store.onlinestore.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.onlinestore.entity.CartItem;
import com.store.onlinestore.entity.Product;
import com.store.onlinestore.repository.CartRepository;
import com.store.onlinestore.repository.ProductRepository;

@Service
public class CartService {
    
    @Autowired
    private CartRepository cartRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    public void addToCart(Long productId, String sessionId) {
        CartItem existingItem = cartRepository.findBySessionIdAndProductId(sessionId, productId);
        
        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + 1);
            cartRepository.save(existingItem);
        } else {
            CartItem newItem = new CartItem(productId, sessionId, 1);
            cartRepository.save(newItem);
        }
    }
    
    public List<CartItem> getCartItems(String sessionId) {
        List<CartItem> cartItems = cartRepository.findBySessionId(sessionId);
        
        // Enrich cart items with product details
        return cartItems.stream().map(item -> {
            Product product = productRepository.findById(item.getProductId()).orElse(null);
            if (product != null) {
                item.setProductName(product.getName());
                item.setPrice(product.getPrice());
                item.setImageUrl(product.getImageUrl());
            }
            return item;
        }).collect(Collectors.toList());
    }
    
    public void removeFromCart(Long cartItemId) {
        cartRepository.deleteById(cartItemId);
    }
    
    public void clearCart(String sessionId) {
        cartRepository.deleteBySessionId(sessionId);
    }
    
    public double getCartTotal(String sessionId) {
        List<CartItem> cartItems = getCartItems(sessionId);
        return cartItems.stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();
    }
}