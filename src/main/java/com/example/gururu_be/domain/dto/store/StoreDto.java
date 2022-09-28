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

    private String storeNewAddrs;

    private String storeOldAddrs;

    private String storeDetailedAddrs;

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
        this.storeNewAddrs = store.getStoreNewAddrs();
        this.storeOldAddrs = store.getStoreOldAddrs();
        this.storeDetailedAddrs = store.getStoreDetailedAddrs();
        this.storeAddrsDesc = store.getStoreAddrsDesc();
        this.companyRegistrationNumber = store.getCompanyRegistrationNumber();
        this.openTime = store.getOpenTime();
        this.closeTime = store.getCloseTime();
        this.phoneNumber = store.getPhoneNumber();
        this.homepage = store.getHomepage();
    }
}
