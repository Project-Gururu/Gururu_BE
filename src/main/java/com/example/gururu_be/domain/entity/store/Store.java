package com.example.gururu_be.domain.entity.store;

import com.example.gururu_be.domain.dto.store.StoreDto;
import com.example.gururu_be.domain.entity.baseentity.BaseEntity;
import com.example.gururu_be.domain.entity.member.Member;
import com.example.gururu_be.enumerate.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "STORE")
public class Store extends BaseEntity {

    //Store : Member => N: 1 엔티티에서 mbId 외래키를 뜻함
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mbId")
    private Member member;

    @NotNull
    @Size(max = 50)
    private String storeName;

    @Column(columnDefinition = "LONGTEXT")
    private String storeImg;

    @Size(max = 225)
    private String storeDesc;

    @Size(max = 50)
    private String storeHoliday;

    @NotNull
    @Size(max = 50)
    private String storeNewAddrs;

    @NotNull
    @Size(max = 50)
    private String storeOldAddrs;

    @NotNull
    @Size(max = 50)
    private String storeDetailedAddrs;

    @Size(max = 225)
    private String storeAddrsDesc;

    @NotNull
    @Size(max = 50)
    private String companyRegistrationNumber;

    @NotNull
    @Size(max = 50)
    private String openTime;

    @NotNull
    @Size(max = 50)
    private String closeTime;

    @NotNull
    @Size(max = 50)
    private String phoneNumber;

    @Size(max = 50)
    private String homepage;

    @NotNull
    @Size(max = 50)
    private String x;

    @NotNull
    @Size(max = 50)
    private String y;

    @Enumerated(EnumType.STRING)
    private Role role = Role.ROLE_USER;

    public void admin() {
        this.role = Role.ROLE_ADMIN;
    }

    public void updateStore(StoreDto storeDto) {
        this.storeName = storeDto.getStoreName();
        this.storeImg = storeDto.getStoreImg();
        this.storeDesc = storeDto.getStoreDesc();
        this.storeHoliday = storeDto.getStoreHoliday();
        this.storeNewAddrs = storeDto.getStoreNewAddrs();
        this.storeOldAddrs = storeDto.getStoreOldAddrs();
        this.storeDetailedAddrs = storeDto.getStoreDetailedAddrs();
        this.storeAddrsDesc = storeDto.getStoreAddrsDesc();
        this.companyRegistrationNumber = storeDto.getCompanyRegistrationNumber();
        this.openTime = storeDto.getOpenTime();
        this.closeTime = storeDto.getCloseTime();
        this.phoneNumber = storeDto.getPhoneNumber();
        this.homepage = storeDto.getHomepage();
        this.x = storeDto.getX();
        this.y = storeDto.getY();
    }
}
