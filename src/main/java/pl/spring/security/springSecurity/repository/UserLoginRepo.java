package pl.spring.security.springSecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.spring.security.springSecurity.model.LoginUser;

public interface UserLoginRepo extends JpaRepository<LoginUser, Long> {
}
