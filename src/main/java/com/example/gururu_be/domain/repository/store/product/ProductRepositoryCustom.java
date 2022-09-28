package com.example.gururu_be.domain.repository.store.product;

import com.example.gururu_be.domain.dto.store.ProductDto;

import java.util.List;
import java.util.UUID;

public interface ProductRepositoryCustom {
    List<ProductDto> findAllProductBystoreRegisterId_DSL(UUID storeRegisterId);
}
