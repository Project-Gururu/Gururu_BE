package com.example.gururu_be.domain.dto.store;


import com.example.gururu_be.domain.entity.store.Price;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PriceDto {

    private UUID storeRegisterId;

    private UUID priceId;

    private String sizeName;

    private String beautyName;

    private String beautyDesc;

    private String amount;

    public PriceDto(Price price) {
        this.storeRegisterId = price.getStore().getId();
        this.priceId = price.getId();
        this.sizeName = price.getSizeName();
        this.beautyName = price.getBeautyName();
        this.beautyDesc = price.getBeautyDesc();
        this.amount = price.getAmount();
    }
}
