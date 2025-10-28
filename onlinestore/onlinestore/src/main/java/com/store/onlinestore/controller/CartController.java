package com.store.onlinestore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;  // CHANGED from javax to jakarta
import org.springframework.web.bind.annotation.RequestParam;

import com.store.onlinestore.service.CartService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/cart")
public class CartController {
    
    @Autowired
    private CartService cartService;
    
    @GetMapping
    public String viewCart(Model model, HttpServletRequest request) {
        String sessionId = request.getSession().getId();
        model.addAttribute("cartItems", cartService.getCartItems(sessionId));
        model.addAttribute("total", cartService.getCartTotal(sessionId));
        return "cart";
    }
    
    @PostMapping("/add")
    public String addToCart(@RequestParam Long productId, HttpServletRequest request) {
        String sessionId = request.getSession().getId();
        cartService.addToCart(productId, sessionId);
        return "redirect:/products";
    }
    
    @PostMapping("/remove")
    public String removeFromCart(@RequestParam Long cartItemId) {
        cartService.removeFromCart(cartItemId);
        return "redirect:/cart";
    }
    
    @PostMapping("/clear")
    public String clearCart(HttpServletRequest request) {
        String sessionId = request.getSession().getId();
        cartService.clearCart(sessionId);
        return "redirect:/cart";
    }
}