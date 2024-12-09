package com.drama.shop.drama_shop_backend.Repository;

import com.drama.shop.drama_shop_backend.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
