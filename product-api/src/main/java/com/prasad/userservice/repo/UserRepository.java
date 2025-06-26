package com.prasad.userservice.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import com.prasad.userservice.model.User;
public interface UserRepository extends JpaRepository<User, Long>{

}
