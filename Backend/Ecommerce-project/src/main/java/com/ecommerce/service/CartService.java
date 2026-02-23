package com.ecommerce.service;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecommerce.entity.Cart;
import com.ecommerce.entity.CartItem;
import com.ecommerce.repository.CartRepository;
import com.ecommerce.repository.CartItemRepository;


@SuppressWarnings("unused")
@Service
public class CartService {
	@SuppressWarnings("unused")
	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private CartItemRepository cartItemRepository;

	
	public List<CartItem> getCartItems(Long cartId) {
        return cartItemRepository.findByCartId(cartId);
    }

    public CartItem addCartItem(CartItem item) {
        return cartItemRepository.save(item);
    }

    public void removeCartItem(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

}
