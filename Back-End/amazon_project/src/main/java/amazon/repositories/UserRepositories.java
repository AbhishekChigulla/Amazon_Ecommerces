package amazon.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import amazon.entities.User;

@Repository
public interface UserRepositories extends JpaRepository<User,Integer> 
{
	public boolean existsByUsername(String username);
	public boolean existsByEmail(String email);
	
	public Optional<User> findByUsername(String username);
	
}
