package com.example.gururu_be.domain.dto.reservation;

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

    private UUID mbId;

    private UUID petId;

    private UUID storeRegisterId;

    private UUID productId;

    private UUID beauticianId;

    private ReservationState reservationState;

    private String reservationTime;

    private String requestsInfo;

    private RefuseState refuseState;

    private String refuseMessage;

    private ReviewState reviewState;

}
