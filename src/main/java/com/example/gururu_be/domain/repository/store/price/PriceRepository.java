package com.example.gururu_be.domain.repository.store.price;



import com.example.gururu_be.domain.entity.store.Price;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PriceRepository extends JpaRepository<Price, UUID>, PriceRepositoryCustom {
}
