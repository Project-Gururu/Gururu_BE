package com.example.gururu_be.domain.repository.reservation.user;

import com.example.gururu_be.domain.dto.reservation.UserReservationDto;

import java.util.List;
import java.util.UUID;

public interface UserReservationRepositoryCustom {
    List<UserReservationDto> findAllReservationBymbId_DSL(UUID mbId);
    List<UserReservationDto> findWaitingAllReservationBymbId_DSL(UUID mbId);
    List<UserReservationDto> findProgressAllReservationBymbId_DSL(UUID mbId);
    List<UserReservationDto> findRefuseAllReservationBymbId_DSL(UUID mbId);
    List<UserReservationDto> findCompletionAllReservationBymbId_DSL(UUID mbId);
}
