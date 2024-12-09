package com.drama.shop.drama_shop_backend.Repository;

import com.drama.shop.drama_shop_backend.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); // Buscar por email

    Optional<User> findByUsername(String username); // Buscar por nombre de usuario
}
