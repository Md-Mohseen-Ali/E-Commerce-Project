package com.ecommerce.controller;

import com.ecommerce.dto.CartRequest;
import com.ecommerce.entity.Cart; 
import com.ecommerce.entity.CartItem;
import com.ecommerce.entity.Product;
import com.ecommerce.repository.CartItemRepository;
import com.ecommerce.repository.CartRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.service.CartService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;
 
    @Autowired
    private CartItemRepository cartItemRepository;
    
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @PostMapping("/create")
    public Cart createCart(@RequestBody Cart cart) {
        return cartRepository.save(cart);
    }

    @GetMapping("/cart/{cartId}")
    public List<CartItem> getCartItems(@PathVariable Long cartId) {
        return cartService.getCartItems(cartId);
    }

    @PostMapping("/add")
    public CartItem addToCart(@RequestBody CartRequest request) {

        Cart cart = cartRepository.findByUserId(request.getUserId());

        if(cart == null){
            cart = new Cart();
            cart.setUserId(request.getUserId());
            cart = cartRepository.save(cart);
        }

        Product product = productRepository.findById(request.getProductId()).get();

        CartItem item = new CartItem();
        item.setCart(cart);
        item.setProduct(product);
        item.setQuantity(request.getQuantity());

        return cartItemRepository.save(item);
    }
    
        @DeleteMapping("/remove/{cartItemId}")
    public String removeFromCart(@PathVariable Long cartItemId) {

        try {

            CartItem item = cartItemRepository.findById(cartItemId).orElse(null);

            if (item == null) {
                return "Cart item not found";
            }

            cartItemRepository.delete(item);

            return "Item removed successfully";

        } catch (Exception e) {

            e.printStackTrace();
            return "Error removing item";

        }
    }
        @GetMapping("/my-cart/{userId}")
        public List<CartItem> getMyCart(@PathVariable Long userId) {

            Cart cart = cartRepository.findByUserId(userId);

            if(cart == null){
                return List.of(); // return empty list instead of crash
            }

            return cartItemRepository.findByCartId(cart.getId());
        }}