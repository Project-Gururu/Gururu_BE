package com.example.gururu_be.domain.repository.reservation;


import com.example.gururu_be.domain.dto.reservation.UserReservationDto;
import com.example.gururu_be.domain.entity.member.QMember;
import com.example.gururu_be.domain.entity.reservation.QReservation;
import com.example.gururu_be.enumerate.RefuseState;
import com.example.gururu_be.enumerate.ReservationState;
import com.example.gururu_be.enumerate.StatusFlag;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class UserReservationRepositoryImpl implements UserReservationRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<UserReservationDto> findWaitingAllReservationBymbId_DSL(UUID mbId) {
        QMember member = QMember.member;
        QReservation reservation = QReservation.reservation;

        return jpaQueryFactory
                .select(Projections.constructor(
                        UserReservationDto.class,
                        reservation.id,
                        reservation.member.id,
                        reservation.pet.id,
                        reservation.store.id,
                        reservation.product.id,
                        reservation.beautician.id,
                        reservation.reservationState,
                        reservation.reservationTime,
                        reservation.requestsInfo,
                        reservation.refuseState,
                        reservation.refuseMessage,
                        reservation.reviewState))
                .from(reservation)
                .leftJoin(member)
                .on(member.id.eq(reservation.member.id))
                .where(member.id.eq(mbId)
                        .and(reservation.delFlag.eq(StatusFlag.NORMAL))
                        .and(reservation.refuseState.eq(RefuseState.REFUSE_WAITING)))
                .orderBy(reservation.reservationTime.asc())
                .distinct().fetch();
    }

    @Override
    public List<UserReservationDto> findProgressAllReservationBymbId_DSL(UUID mbId) {
        QMember member = QMember.member;
        QReservation reservation = QReservation.reservation;

        return jpaQueryFactory
                .select(Projections.constructor(
                        UserReservationDto.class,
                        reservation.id,
                        reservation.member.id,
                        reservation.pet.id,
                        reservation.store.id,
                        reservation.product.id,
                        reservation.beautician.id,
                        reservation.reservationState,
                        reservation.reservationTime,
                        reservation.requestsInfo,
                        reservation.refuseState,
                        reservation.refuseMessage,
                        reservation.reviewState))
                .from(reservation)
                .leftJoin(member)
                .on(member.id.eq(reservation.member.id))
                .where(member.id.eq(mbId)
                        .and(reservation.delFlag.eq(StatusFlag.NORMAL))
                        .and(reservation.reservationState.eq(ReservationState.PROGRESS)))
                .orderBy(reservation.reservationTime.asc())
                .distinct().fetch();
    }
    @Override
    public List<UserReservationDto> findRefuseAllReservationBymbId_DSL(UUID mbId) {
        QMember member = QMember.member;
        QReservation reservation = QReservation.reservation;

        return jpaQueryFactory
                .select(Projections.constructor(
                        UserReservationDto.class,
                        reservation.id,
                        reservation.member.id,
                        reservation.pet.id,
                        reservation.store.id,
                        reservation.product.id,
                        reservation.beautician.id,
                        reservation.reservationState,
                        reservation.reservationTime,
                        reservation.requestsInfo,
                        reservation.refuseState,
                        reservation.refuseMessage,
                        reservation.reviewState))
                .from(reservation)
                .leftJoin(member)
                .on(member.id.eq(reservation.member.id))
                .where(member.id.eq(mbId)
                        .and(reservation.delFlag.eq(StatusFlag.NORMAL))
                        .and(reservation.refuseState.eq(RefuseState.NO_RESERVATION)))
                .orderBy(reservation.reservationTime.asc())
                .distinct().fetch();
    }
    @Override
    public List<UserReservationDto> findCompletionAllReservationBymbId_DSL(UUID mbId) {
        QMember member = QMember.member;
        QReservation reservation = QReservation.reservation;

        return jpaQueryFactory
                .select(Projections.constructor(
                        UserReservationDto.class,
                        reservation.id,
                        reservation.member.id,
                        reservation.pet.id,
                        reservation.store.id,
                        reservation.product.id,
                        reservation.beautician.id,
                        reservation.reservationState,
                        reservation.reservationTime,
                        reservation.requestsInfo,
                        reservation.refuseState,
                        reservation.refuseMessage,
                        reservation.reviewState))
                .from(reservation)
                .leftJoin(member)
                .on(member.id.eq(reservation.member.id))
                .where(member.id.eq(mbId)
                        .and(reservation.delFlag.eq(StatusFlag.NORMAL))
                        .and(reservation.reservationState.eq(ReservationState.COMPLETION)))
                .orderBy(reservation.reservationTime.asc())
                .distinct().fetch();
    }
}
