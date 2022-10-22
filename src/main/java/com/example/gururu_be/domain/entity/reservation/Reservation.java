package com.example.gururu_be.domain.entity.reservation;

import com.example.gururu_be.domain.dto.reservation.AdminReservationDto;
import com.example.gururu_be.domain.entity.baseentity.BaseEntity;
import com.example.gururu_be.domain.entity.member.Member;
import com.example.gururu_be.domain.entity.pet.Pet;
import com.example.gururu_be.domain.entity.store.Beautician;
import com.example.gururu_be.domain.entity.store.Product;
import com.example.gururu_be.domain.entity.store.Store;
import com.example.gururu_be.enumerate.RefuseState;
import com.example.gururu_be.enumerate.ReservationState;
import com.example.gururu_be.enumerate.ReviewState;
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
@Table(name = "RESERVATION")
public class Reservation extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mbId")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "petId")
    private Pet pet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storeRegisterId")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "beauticianId")
    private Beautician beautician;

    @Enumerated(EnumType.STRING)
    private ReservationState reservationState = ReservationState.RESERVATION_WAITING;

    @NotNull
    @Size(max = 50)
    private String reservationTime;

    @NotNull
    @Size(max = 50)
    private String requestsInfo;

    @Enumerated(EnumType.STRING)
    private RefuseState refuseState = RefuseState.REFUSE_WAITING;

    private String refuseMessage;

    @Enumerated(EnumType.STRING)
    private ReviewState reviewState = ReviewState.UN_WRITE;

    public void yes(AdminReservationDto adminReservationDto) {
        this.refuseState = RefuseState.YES_RESERVATION;
        this.reservationState = ReservationState.PROGRESS;
        this.refuseMessage = adminReservationDto.getRefuseMessage();
    }

    public void no(AdminReservationDto adminReservationDto) {
        this.refuseState = RefuseState.NO_RESERVATION;
        this.refuseMessage = adminReservationDto.getRefuseMessage();
    }
}

