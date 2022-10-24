package com.example.gururu_be.domain.repository.reservation.admin;

import com.example.gururu_be.domain.dto.reservation.AdminReservationDto;
import com.example.gururu_be.domain.entity.reservation.QReservation;
import com.example.gururu_be.domain.entity.store.QStore;
import com.example.gururu_be.enumerate.RefuseState;
import com.example.gururu_be.enumerate.ReservationState;
import com.example.gururu_be.enumerate.StatusFlag;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class AdminReservationRepositoryImpl implements AdminReservationRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<AdminReservationDto> findAllReservationByreservationDay_DSL(String reservationDay) {
        QReservation reservation = QReservation.reservation;

        return jpaQueryFactory
                .select(Projections.constructor(
                        AdminReservationDto.class,
                        reservation.id,
                        reservation.store.id,
                        reservation.pet.id,
                        reservation.store.id,
                        reservation.product.id,
                        reservation.beautician.id,
                        reservation.reservationState,
                        reservation.reservationDay,
                        reservation.reservationTime,
                        reservation.requestsInfo,
                        reservation.refuseState,
                        reservation.refuseMessage,
                        reservation.reviewState))
                .from(reservation)
                .where(reservation.reservationDay.eq(reservationDay)
                        .and(reservation.delFlag.eq(StatusFlag.NORMAL))
                        .and(reservation.reservationState.eq(ReservationState.PROGRESS)))
                .orderBy(reservation.reservationTime.desc())
                .distinct().fetch();
    }

    @Override
    public List<AdminReservationDto> findAllReservationBystoreRegisterId_DSL(UUID storeRegisterId) {
        QStore store = QStore.store;
        QReservation reservation = QReservation.reservation;

        return jpaQueryFactory
                .select(Projections.constructor(
                        AdminReservationDto.class,
                        reservation.id,
                        reservation.store.id,
                        reservation.pet.id,
                        reservation.store.id,
                        reservation.product.id,
                        reservation.beautician.id,
                        reservation.reservationState,
                        reservation.reservationDay,
                        reservation.reservationTime,
                        reservation.requestsInfo,
                        reservation.refuseState,
                        reservation.refuseMessage,
                        reservation.reviewState))
                .from(reservation)
                .leftJoin(store)
                .on(store.id.eq(reservation.store.id))
                .where(store.id.eq(storeRegisterId)
                        .and(reservation.delFlag.eq(StatusFlag.NORMAL)))
                .orderBy(reservation.reservationTime.desc())
                .distinct().fetch();
    }

    @Override
    public List<AdminReservationDto> findWaitingAllReservationBystoreRegisterId_DSL(UUID storeRegisterId) {
        QStore store = QStore.store;
        QReservation reservation = QReservation.reservation;

        return jpaQueryFactory
                .select(Projections.constructor(
                        AdminReservationDto.class,
                        reservation.id,
                        reservation.store.id,
                        reservation.pet.id,
                        reservation.store.id,
                        reservation.product.id,
                        reservation.beautician.id,
                        reservation.reservationState,
                        reservation.reservationDay,
                        reservation.reservationTime,
                        reservation.requestsInfo,
                        reservation.refuseState,
                        reservation.refuseMessage,
                        reservation.reviewState))
                .from(reservation)
                .leftJoin(store)
                .on(store.id.eq(reservation.store.id))
                .where(store.id.eq(storeRegisterId)
                        .and(reservation.delFlag.eq(StatusFlag.NORMAL))
                        .and(reservation.refuseState.eq(RefuseState.REFUSE_WAITING)))
                .orderBy(reservation.reservationTime.desc())
                .distinct().fetch();
    }

    @Override
    public List<AdminReservationDto> findProgressAllReservationBystoreRegisterId_DSL(UUID storeRegisterId) {
        QStore store = QStore.store;
        QReservation reservation = QReservation.reservation;

        return jpaQueryFactory
                .select(Projections.constructor(
                        AdminReservationDto.class,
                        reservation.id,
                        reservation.store.id,
                        reservation.pet.id,
                        reservation.store.id,
                        reservation.product.id,
                        reservation.beautician.id,
                        reservation.reservationState,
                        reservation.reservationDay,
                        reservation.reservationTime,
                        reservation.requestsInfo,
                        reservation.refuseState,
                        reservation.refuseMessage,
                        reservation.reviewState))
                .from(reservation)
                .leftJoin(store)
                .on(store.id.eq(reservation.store.id))
                .where(store.id.eq(storeRegisterId)
                        .and(reservation.delFlag.eq(StatusFlag.NORMAL))
                        .and(reservation.reservationState.eq(ReservationState.PROGRESS)))
                .orderBy(reservation.reservationTime.desc())
                .distinct().fetch();
    }
    @Override
    public List<AdminReservationDto> findRefuseAllReservationBystoreRegisterId_DSL(UUID storeRegisterId) {
        QStore store = QStore.store;
        QReservation reservation = QReservation.reservation;

        return jpaQueryFactory
                .select(Projections.constructor(
                        AdminReservationDto.class,
                        reservation.id,
                        reservation.store.id,
                        reservation.pet.id,
                        reservation.store.id,
                        reservation.product.id,
                        reservation.beautician.id,
                        reservation.reservationState,
                        reservation.reservationDay,
                        reservation.reservationTime,
                        reservation.requestsInfo,
                        reservation.refuseState,
                        reservation.refuseMessage,
                        reservation.reviewState))
                .from(reservation)
                .leftJoin(store)
                .on(store.id.eq(reservation.store.id))
                .where(store.id.eq(storeRegisterId)
                        .and(reservation.delFlag.eq(StatusFlag.NORMAL))
                        .and(reservation.refuseState.eq(RefuseState.NO_RESERVATION)))
                .orderBy(reservation.reservationTime.desc())
                .distinct().fetch();
    }
    @Override
    public List<AdminReservationDto> findCompletionAllReservationBystoreRegisterId_DSL(UUID storeRegisterId) {
        QStore store = QStore.store;
        QReservation reservation = QReservation.reservation;

        return jpaQueryFactory
                .select(Projections.constructor(
                        AdminReservationDto.class,
                        reservation.id,
                        reservation.store.id,
                        reservation.pet.id,
                        reservation.store.id,
                        reservation.product.id,
                        reservation.beautician.id,
                        reservation.reservationState,
                        reservation.reservationDay,
                        reservation.reservationTime,
                        reservation.requestsInfo,
                        reservation.refuseState,
                        reservation.refuseMessage,
                        reservation.reviewState))
                .from(reservation)
                .leftJoin(store)
                .on(store.id.eq(reservation.store.id))
                .where(store.id.eq(storeRegisterId)
                        .and(reservation.delFlag.eq(StatusFlag.NORMAL))
                        .and(reservation.reservationState.eq(ReservationState.COMPLETION)))
                .orderBy(reservation.reservationTime.desc())
                .distinct().fetch();
    }
}
