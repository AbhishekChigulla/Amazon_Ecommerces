package amazon.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import amazon.entities.Product;
@Repository
public interface ProductRepositories extends JpaRepository<Product, Integer>{

	
	public List<Product> findAllByCategory(String category);
	
	public List<Product> findAllByNameContainingIgnoreCase(String keyword);
}
