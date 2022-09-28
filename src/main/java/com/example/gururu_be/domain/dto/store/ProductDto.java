package com.example.gururu_be.domain.dto.store;


import com.example.gururu_be.domain.entity.store.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {

    private UUID storeRegisterId;

    private UUID productId;

    private String sizeName;

    private String beautyName;

    private String beautyDesc;

    private String amount;

    public ProductDto(Product product) {
        this.storeRegisterId = product.getStore().getId();
        this.productId = product.getId();
        this.sizeName = product.getSizeName();
        this.beautyName = product.getBeautyName();
        this.beautyDesc = product.getBeautyDesc();
        this.amount = product.getAmount();
    }
}
