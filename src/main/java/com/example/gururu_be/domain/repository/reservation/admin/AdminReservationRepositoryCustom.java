package com.example.gururu_be.domain.repository.reservation.admin;

import com.example.gururu_be.domain.dto.reservation.AdminReservationDto;

import java.util.List;
import java.util.UUID;

public interface AdminReservationRepositoryCustom {
    List<AdminReservationDto> findAllReservationByreservationDay_DSL(String reservationDay);
    List<AdminReservationDto> findAllReservationBystoreRegisterId_DSL(UUID storeRegisterId);
    List<AdminReservationDto> findWaitingAllReservationBystoreRegisterId_DSL(UUID storeRegisterId);
    List<AdminReservationDto> findProgressAllReservationBystoreRegisterId_DSL(UUID storeRegisterId);
    List<AdminReservationDto> findRefuseAllReservationBystoreRegisterId_DSL(UUID storeRegisterId);
    List<AdminReservationDto> findCompletionAllReservationBystoreRegisterId_DSL(UUID storeRegisterId);
}
