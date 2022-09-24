package com.example.gururu_be.domain.dto.store;



import com.example.gururu_be.domain.entity.store.Store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreDto {

    private UUID mbId;

    private UUID storeRegisterId;

    private String storeName;

    private String storeImg;

    private String storeDesc;

    private String storeHoliday;

    private String storeAddrs;

    private String storeAddrsDesc;

    private String companyRegistrationNumber;

    private String openTime;

    private String closeTime;

    private String phoneNumber;

    private String homepage;

    public StoreDto(Store store) {
        this.mbId = store.getMember().getId();
        this.storeRegisterId = store.getId();
        this.storeName = store.getStoreName();
        this.storeImg = store.getStoreImg();
        this.storeDesc = store.getStoreDesc();
        this.storeHoliday = store.getStoreHoliday();
        this.storeAddrs = store.getStoreAddrs();
        this.storeAddrsDesc = store.getStoreAddrsDesc();
        this.companyRegistrationNumber = store.getCompanyRegistrationNumber();
        this.openTime = store.getOpenTime();
        this.closeTime = store.getCloseTime();
        this.phoneNumber = store.getPhoneNumber();
        this.homepage = store.getHomepage();
    }
}
