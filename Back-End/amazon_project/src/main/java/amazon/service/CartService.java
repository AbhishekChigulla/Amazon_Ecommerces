package amazon.service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import amazon.entities.Cart;
import amazon.entities.CartItem;
import amazon.entities.Product;
import amazon.entities.User;
import amazon.repositories.CartItemRepositories;
import amazon.repositories.CartRepositories;
import amazon.repositories.ProductRepositories;
import amazon.repositories.UserRepositories;

@Service
public class CartService {
	
	@Autowired
	private CartRepositories cartRepositories;

	
	@Autowired
	private UserRepositories userRepositories;
	
	@Autowired
	private ProductRepositories productRepositories;
	
	@Autowired
	private CartItemRepositories cartItemRepositories;
	
	public Cart getCartByUserId(int userId)
	{
		
		//get carts of user
		Cart cart=cartRepositories.findByUserId(userId);
		
		//if cart does not exist create new
		if(cart == null)
		{
			cart=new Cart();
			
			
			//cart link with a particaular user
			
			Optional<User> optionalUser =userRepositories.findById(userId);
			
			if (!optionalUser.isPresent()) 
			{
				throw new RuntimeException("Invalid user id");
			}
			User user=optionalUser.get();
			cart.setUser(user);
			
			cart=cartRepositories.save(cart);
		}
		
		
		return cart;
		

	}
	
	
	
	
	
	
	public List<CartItem> getAllCartItems(int userId)
	{
		Cart cart=getCartByUserId(userId);
		
		return	cart.getCartItems();
	}
	
	/*
	 * add the cartItem
	 * 
	 * 
	 */
	
	public CartItem addCartItem(int userId,int productId)
	{
		CartItem cartItem=null;
		
		Cart cart=getCartByUserId(userId);
		
		List<CartItem> cartItemsList=cart.getCartItems();
		
		Optional<Product> optionalProduct=productRepositories.findById(productId);
		
		if(!optionalProduct.isPresent())
		{
			throw new RuntimeException("Product does not exist");
		}
		
		Product product =optionalProduct.get();
		
		//check product present in cart if present increase quantity
		
		for(CartItem cartIteminList:cartItemsList)
		{
			if(cartIteminList.getProduct().getId()==productId)
			{
				cartIteminList.setQuantity(cartIteminList.getQuantity()+1);
				
				cartRepositories.save(cart);   //incremease in the data base
				
				cartItem=cartIteminList;
			}
		}
		
		if(cartItem==null)
		{
           CartItem  newCartItem= new CartItem();
			
			newCartItem .setQuantity(1);
			newCartItem .setCart(cart);
			newCartItem .setProduct(product);
			
			
			cartItemRepositories.save(newCartItem);
			
			
			cartItem=newCartItem;
			
			
			
		}
		
		
		
		return cartItem;
	}
	
	public CartItem increaseQuantity(int cartItemId)
	{
		Optional<CartItem> optionalCartItem=cartItemRepositories.findById(cartItemId);
		
		if(!optionalCartItem.isPresent())
		{
			throw new RuntimeException("Item does not exists");
			
		}
		
		CartItem cartItem=optionalCartItem.get();
		
		cartItem.setQuantity(cartItem.getQuantity()+1);
		
		cartItemRepositories.save(cartItem);
		
		return cartItem;
	}
	
	
	
	
	
	
	public CartItem decreaseQuantity(int cartItemId)
	{
		Optional<CartItem> optionalCartItem=cartItemRepositories.findById(cartItemId);
		
		if(!optionalCartItem.isPresent())
		{
			throw new RuntimeException("Item does not exists");
			
		}
		
		CartItem cartItem=optionalCartItem.get();
		
		if(cartItem.getQuantity()>0)
		{
			cartItem.setQuantity(cartItem.getQuantity()-1);
		}
		
		cartItemRepositories.save(cartItem);
		
		return cartItem;
	}
	
	
	//remove from the cart
	
	
	public CartItem removeFromCart(int cartItemId)
	{
		Optional<CartItem> optionalCartItem=cartItemRepositories.findById(cartItemId);
		
		if(!optionalCartItem.isPresent())
		{
			throw new RuntimeException("Item does not Exists");
		}
		
		CartItem cartItem=optionalCartItem.get();
		cartItemRepositories.delete(cartItem);
		
		return cartItem;
	}
	
	
	
	public Cart clearCart(int userId)
	{
		Cart cart=getCartByUserId(userId);
		
		
		cart.getCartItems().clear();
		
		cartRepositories.save(cart);
		
		cart=getCartByUserId(userId);
		return cart;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
