package com.example.gururu_be.domain.repository.store.price;

import com.example.gururu_be.domain.dto.store.PriceDto;
import com.example.gururu_be.domain.entity.store.QPrice;
import com.example.gururu_be.domain.entity.store.QStore;
import com.example.gururu_be.enumerate.StatusFlag;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class PriceRepositoryImpl implements PriceRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<PriceDto> findAllPriceBystoreRegisterId_DSL(UUID storeRegisterId) {
        QStore store = QStore.store;
        QPrice price = QPrice.price;

        return jpaQueryFactory
                .select(Projections.constructor(
                        PriceDto.class,
                        price.store.id,
                        price.id,
                        price.sizeName,
                        price.beautyName,
                        price.beautyDesc,
                        price.amount))
                .from(price)
                .leftJoin(store)
                .on(store.id.eq(price.store.id))
                .where(store.id.eq(storeRegisterId)
                        .and(price.delFlag.eq(StatusFlag.NORMAL)))
                .distinct().fetch();
    }
}
