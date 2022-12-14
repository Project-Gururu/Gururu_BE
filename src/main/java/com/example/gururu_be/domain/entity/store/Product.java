package com.example.gururu_be.domain.entity.store;

import com.example.gururu_be.domain.dto.store.ProductDto;
import com.example.gururu_be.domain.entity.baseentity.BaseEntity;
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
@Table(name = "PRODOCT")
public class Product extends BaseEntity {

    //Product : Store => N: 1 엔티티에서 mbId 외래키를 뜻함
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storeRegisterId")
    private Store store;

    @NotNull
    @Size(max = 50)
    private String sizeName;

    @NotNull
    @Size(max = 50)
    private String beautyName;

    @Size(max = 225)
    private String beautyDesc;

    @NotNull
    @Size(max = 50)
    private String amount;

    public void updateProduct(ProductDto productDto) {
        this.sizeName = productDto.getSizeName();
        this.beautyName = productDto.getBeautyName();
        this.beautyDesc = productDto.getBeautyDesc();
        this.amount = productDto.getAmount();
    }
}
