package amazon.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import amazon.entities.Cart;
@Repository
public interface CartRepositories extends JpaRepository<Cart, Integer> {

	
	public Cart findByUserId(int userId);
}
