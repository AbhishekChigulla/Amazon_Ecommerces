package amazon.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import amazon.entities.Product;
import amazon.repositories.ProductRepositories;
import ch.qos.logback.core.joran.conditional.IfAction;

@Service
public class ProductService {
	
	@Autowired    //automatically inserted
	private ProductRepositories productRepositories;
	
	
	
	
	public Product addProduct(Product product)
	{
		
		return this.productRepositories.save(product);
	}
	
	
	public Product getProductById(int productId) 
	{
		Optional<Product>  optionalProduct=this.productRepositories.findById(productId);
		
		if (optionalProduct.isPresent()) {    //optionalProduct lo product undha ani chudhataniki ispresnt menthod untey return cheyataniki get method
			
		return	optionalProduct.get();
		}
		else {
			return null;
		}
	}
	
	public List<Product> getAllProducts()
	{
		return this.productRepositories.findAll();
		
	}
	
	
	public List<Product> getProductsBasedOnCategory(String category)
	{
		
	  return	this.productRepositories.findAllByCategory(category);
	}
	
	
	public List<Product> searchProducts(String keyword)
	{
	 return	this.productRepositories.findAllByNameContainingIgnoreCase(keyword);
	}
	
	

}
