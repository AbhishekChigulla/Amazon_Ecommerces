package amazon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import amazon.entities.Cart;
import amazon.entities.CartItem;
import amazon.service.CartService;

@RestController
@RequestMapping("/amazon/carts")
public class CartController {
	
	
	
	@Autowired
	private CartService cartService;
	
	
	
	@GetMapping("/{userId}")
	public ResponseEntity<?> getAllCartItems(@PathVariable int userId)
	{
		try {
			List<CartItem> cartItemsList=cartService.getAllCartItems(userId);
			return new ResponseEntity<>(cartItemsList, HttpStatus.OK);
		} 
		catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	
	@PostMapping("/{userId}/add/{productId}")
	public ResponseEntity<?>  addProductToCart(@PathVariable int userId,@PathVariable int productId)
	{
		try
		{
			CartItem cartItem=cartService.addCartItem(userId, productId);
			return new ResponseEntity<>(cartItem, HttpStatus.OK);
			
		
		}
		
		catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	@PatchMapping("/increase/{cartItemId}")
	public ResponseEntity<?> increaseQuantity(@PathVariable int cartItemId )
	{
		try {
			CartItem updatedCartItem=cartService.increaseQuantity(cartItemId);
			
			return new ResponseEntity<>(updatedCartItem, HttpStatus.OK);
		} 
		catch (Exception e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

		}
	}
	
	
	@PatchMapping("/decrease/{cartItemId}")
	public ResponseEntity<?> decreaseQuantity(@PathVariable int cartItemId )
	{
		try {
			CartItem updatedCartItem=cartService.decreaseQuantity(cartItemId);
			
			return new ResponseEntity<>(updatedCartItem, HttpStatus.OK);
		} 
		catch (Exception e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

		}
	}

	
	
	@DeleteMapping("/remove/{cartItemId}")
	public ResponseEntity<?> removeItemFromCart(@PathVariable int cartItemId)
	{
		try {
			CartItem deletedCartItem=cartService.removeFromCart(cartItemId);
			
			return new ResponseEntity<>(deletedCartItem, HttpStatus.OK);
		} 
		catch (Exception e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

		}
	}
	
	
	
	@DeleteMapping("/clear/{userId}")
	public ResponseEntity<?> clearCart(@PathVariable int userId)
	{

		try {
			Cart cart=cartService.clearCart(userId);
			return new ResponseEntity<>(cart, HttpStatus.OK);
		} 
		catch (Exception e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

		}
	}
	
	
	
	
	
	
	
	
	
	
	
}
