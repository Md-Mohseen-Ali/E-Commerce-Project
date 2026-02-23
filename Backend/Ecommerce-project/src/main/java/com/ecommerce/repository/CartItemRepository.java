package com.ecommerce.repository;

import com.ecommerce.entity.CartItem;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long>{

	List<CartItem> findByCartId(Long cartId);
	
	@Modifying
	@Query("DELETE FROM CartItem c WHERE c.cart.id = :cartId")
	void deleteByCartId(Long cartId);
}
