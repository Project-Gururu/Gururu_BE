package com.example.gururu_be.domain.repository.store.product;



import com.example.gururu_be.domain.entity.store.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID>, ProductRepositoryCustom {
}
