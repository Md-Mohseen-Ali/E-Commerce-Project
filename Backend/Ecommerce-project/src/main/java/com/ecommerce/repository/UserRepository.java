package com.ecommerce.repository;
import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository;
import java.util.Optional;
import com.ecommerce.entity.User;

@SuppressWarnings("unused")
@Repository
public interface UserRepository extends JpaRepository<User,Long>{

	Optional<User> findByEmail(String email);
	User findByEmailAndPassword(String email, String password);
}
