package amazon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import amazon.entities.Product;
import amazon.service.ProductService;

@RestController
@RequestMapping("/amazon/products")
public class ProductController {
	
	
	@Autowired
	private ProductService productService;
	
	//Add the Products
	
	@PostMapping("/add")
	public ResponseEntity<?>  addProduct(@RequestBody Product product)     //send the data form in the  requestBody/product information
	{
		
		Product savedProduct=this.productService.addProduct(product);   //savedProduct lo sucessfully add products are added 
		
		if(savedProduct !=null)    
		{
			return new ResponseEntity<>(savedProduct, HttpStatus.OK);         //first one is body ,second one is satutas
		}
		else {
			return new ResponseEntity<>("Failed to add Products", HttpStatus.BAD_REQUEST);
		}
	}
	
	
	//Read the Products Informations in single product
	
	@GetMapping("/{productId}")
	public ResponseEntity<?>getProductsById(@PathVariable int productId){        //read the url use the path variable
		
		Product product=this.productService.getProductById(productId);
		
		if(product != null)
		{
			return new ResponseEntity<>(product, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("Product does not exists", HttpStatus.NOT_FOUND);
		}
			
	
	}
	
	
	//To Read the All the Products At a time.
	
	@GetMapping
	public ResponseEntity<?>getAllProducts()
	{
		List<Product> productsList=this.productService.getAllProducts();
		
		return new ResponseEntity<>(productsList, HttpStatus.OK);
	}
	
	//To Read the Products In category Based.
	
	@GetMapping("/category/{category}")
	public ResponseEntity<?> getAllProductsBasedOnCategory(@PathVariable String category)
	{
		List<Product> productList=this.productService.getProductsBasedOnCategory(category);
		
		return new ResponseEntity<>(productList, HttpStatus.OK);
	}
	
	//To search the keywords
	
	@GetMapping("/search/{keyword}")
	public ResponseEntity<?> getAllProductsBasedOnKeyword(@PathVariable String keyword)
	{
		List<Product> productList=this.productService.searchProducts(keyword);
		
		return new ResponseEntity<>(productList, HttpStatus.OK);
	}
	
	
	
	
	
	

}
