package com.example.gururu_be.domain.repository.store.price;

import com.example.gururu_be.domain.dto.store.PriceDto;

import java.util.List;
import java.util.UUID;

public interface PriceRepositoryCustom {
    List<PriceDto> findAllPriceBystoreRegisterId_DSL(UUID storeRegisterId);
}
