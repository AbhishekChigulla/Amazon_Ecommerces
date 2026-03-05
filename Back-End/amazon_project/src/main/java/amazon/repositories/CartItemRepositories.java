package amazon.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import amazon.entities.CartItem;
@Repository
public interface CartItemRepositories  extends JpaRepository<CartItem, Integer>{

}
