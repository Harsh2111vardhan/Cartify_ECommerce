package com.store.onlinestore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.store.onlinestore.entity.CartItem;  // CHANGED from javax to jakarta
import com.store.onlinestore.entity.Order;
import com.store.onlinestore.service.CartService;
import com.store.onlinestore.service.OrderService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/orders")
public class OrderController {
    
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private CartService cartService;
    
    @GetMapping
    public String viewOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "orders";
    }
    
    @GetMapping("/checkout")
    public String checkout(Model model, HttpServletRequest request) {
        String sessionId = request.getSession().getId();
        List<CartItem> cartItems = cartService.getCartItems(sessionId);
        double total = cartService.getCartTotal(sessionId);
        
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("total", total);
        model.addAttribute("order", new Order());
        return "checkout";
    }
    
    @PostMapping("/place")
    public String placeOrder(@ModelAttribute Order order, HttpServletRequest request) {
        String sessionId = request.getSession().getId();
        List<CartItem> cartItems = cartService.getCartItems(sessionId);
        double total = cartService.getCartTotal(sessionId);
        
        order.setTotalAmount(total);
        order.setItems(cartItems);
        
        Order savedOrder = orderService.createOrder(order);
        
        if (savedOrder != null) {
            cartService.clearCart(sessionId);
        }
        
        return "redirect:/orders";
    }
}