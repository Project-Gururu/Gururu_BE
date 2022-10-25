package com.example.gururu_be.domain.dto.reservation;

import com.example.gururu_be.domain.entity.reservation.Reservation;
import com.example.gururu_be.enumerate.RefuseState;
import com.example.gururu_be.enumerate.ReservationState;
import com.example.gururu_be.enumerate.ReviewState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminReservationDto {

    private UUID reservationId;

    private UUID mbId;

    private UUID petId;

    private UUID storeRegisterId;

    private UUID productId;

    private UUID beauticianId;

    private ReservationState reservationState;

    private String reservationDay;

    private String reservationTime;

    private String requestsInfo;

    private RefuseState refuseState;

    private String refuseMessage;

    private ReviewState reviewState;

    public AdminReservationDto(Reservation reservation) {
        this.reservationId = reservation.getId();
        this.mbId = reservation.getMember().getId();
        this.petId = reservation.getPet().getId();
        this.storeRegisterId = reservation.getStore().getId();
        this.productId = reservation.getProduct().getId();
        this.beauticianId = reservation.getBeautician().getId();
        this.reservationState = reservation.getReservationState();
        this.reservationDay = reservation.getReservationDay();
        this.reservationTime = reservation.getReservationTime();
        this.requestsInfo = reservation.getRequestsInfo();
        this.refuseState = reservation.getRefuseState();
        this.refuseMessage = reservation.getRefuseMessage();
        this.reviewState = reservation.getReviewState();
    }
}
