package com.drama.shop.drama_shop_backend.Repository;

import com.drama.shop.drama_shop_backend.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Métodos personalizados si son necesarios
}
