package com.example.gururu_be.domain.entity.store;

import com.example.gururu_be.domain.dto.store.PriceDto;
import com.example.gururu_be.domain.entity.baseentity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;


@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "PRICE")
public class Price extends BaseEntity {

    //Price : Store => N: 1 엔티티에서 mbId 외래키를 뜻함
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storeRegisterId")
    private Store store;

    @Column(nullable = false)
    @Size(max = 50)
    private String sizeName;

    @Column(nullable = false)
    @Size(max = 50)
    private String beautyName;

    @Column(nullable = false)
    @Size(max = 225)
    private String beautyDesc;

    @Column(nullable = false)
    @Size(max = 50)
    private String amount;

    public void updatePrice(PriceDto priceDto) {
        this.sizeName = priceDto.getSizeName();
        this.beautyName = priceDto.getBeautyName();
        this.beautyDesc = priceDto.getBeautyDesc();
        this.amount = priceDto.getAmount();
    }
}
