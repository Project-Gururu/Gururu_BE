package com.example.gururu_be.domain.repository.reservation;


import com.example.gururu_be.domain.entity.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserReservationRepository extends JpaRepository<Reservation, UUID>, UserReservationRepositoryCustom {
}
