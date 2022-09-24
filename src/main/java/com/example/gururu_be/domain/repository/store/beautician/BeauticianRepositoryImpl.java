package com.example.gururu_be.domain.repository.store.beautician;
import com.example.gururu_be.domain.dto.store.BeauticianDto;
import com.example.gururu_be.domain.entity.store.QBeautician;
import com.example.gururu_be.domain.entity.store.QStore;
import com.example.gururu_be.enumerate.StatusFlag;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class BeauticianRepositoryImpl implements BeauticianRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<BeauticianDto> findAllBeauticianBystoreRegisterId_DSL(UUID storeRegisterId) {
        QStore store = QStore.store;
        QBeautician beautician = QBeautician.beautician;

        return jpaQueryFactory
                .select(Projections.constructor(
                        BeauticianDto.class,
                        beautician.store.id,
                        beautician.id,
                        beautician.beauticianName,
                        beautician.beauticianDesc,
                        beautician.beauticianImg,
                        beautician.beauticianHoliday,
                        beautician.beauticianOpenTime,
                        beautician.beauticianCloseTime))
                .from(beautician)
                .leftJoin(store)
                .on(store.id.eq(beautician.store.id))
                .where(store.id.eq(storeRegisterId)
                        .and(beautician.delFlag.eq(StatusFlag.NORMAL)))
                .distinct().fetch();
    }
}
