package com.example.gururu_be.domain.repository.store.product;

import com.example.gururu_be.domain.dto.store.ProductDto;
import com.example.gururu_be.domain.entity.store.QProduct;
import com.example.gururu_be.domain.entity.store.QStore;
import com.example.gururu_be.enumerate.StatusFlag;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ProductDto> findAllProductBystoreRegisterId_DSL(UUID storeRegisterId) {
        QStore store = QStore.store;
        QProduct product = QProduct.product;

        return jpaQueryFactory
                .select(Projections.constructor(
                        ProductDto.class,
                        product.store.id,
                        product.id,
                        product.sizeName,
                        product.beautyName,
                        product.beautyDesc,
                        product.amount))
                .from(product)
                .leftJoin(store)
                .on(store.id.eq(product.store.id))
                .where(store.id.eq(storeRegisterId)
                        .and(product.delFlag.eq(StatusFlag.NORMAL)))
                .distinct().fetch();
    }
}
