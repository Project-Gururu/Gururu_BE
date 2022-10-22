package com.example.gururu_be.controller.Reservation;

import com.example.gururu_be.domain.dto.ResResultDto;
import com.example.gururu_be.domain.dto.reservation.UserReservationDto;
import com.example.gururu_be.service.reservation.UserReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user/v1.0")
@RequiredArgsConstructor
public class UserReservationController {

    private final UserReservationService userReservationService;

    /**
     * M4-1 예약 등록
     */
    @PostMapping("/{mbId}/reservation/{storeRegisterId}/{petId}/{beauticianId}/{productId}")
    public ResponseEntity<ResResultDto> createReservation(@PathVariable String mbId,@PathVariable String storeRegisterId, @PathVariable String petId,
                                                          @PathVariable String beauticianId, @PathVariable String productId, @RequestBody UserReservationDto userReservationDto) {

        //사업자 생성 서비스 호출
        userReservationService.createReservation(UUID.fromString(mbId),UUID.fromString(storeRegisterId),UUID.fromString(petId),UUID.fromString(beauticianId),
                UUID.fromString(productId),userReservationDto);

        return ResponseEntity.ok(new ResResultDto("예약 등록 성공"));
    }


    /**
     * M4-2 예약 취소
     */
    @DeleteMapping("/{mbId}/reservation/{reservationId}")
    public ResponseEntity<ResResultDto> deleteReservation(@PathVariable String mbId, @PathVariable String reservationId) {

        userReservationService.deleteReservation(UUID.fromString(mbId), UUID.fromString(reservationId));

        return ResponseEntity.ok(new ResResultDto("예약 취소 성공"));
    }


    /**
     * M4-3 예약 상세 조회 (유저)
     */
    @GetMapping("/{mbId}/reservation/{reservationId}")
    public ResponseEntity<UserReservationDto> getOneReservation(@PathVariable String mbId, @PathVariable String reservationId) {

        UserReservationDto UserReservationDto = userReservationService.getOneReservation(UUID.fromString(mbId),UUID.fromString(reservationId));

        return ResponseEntity.ok(UserReservationDto);
    }


    /**
     * M4-4 예약 전체 조회 (유저)
     */
    @GetMapping("/{mbId}/reservation/all/{status}")
    public List<UserReservationDto> getAllReservation(@PathVariable String mbId, @PathVariable String status) {
        return userReservationService.getAllReservation(UUID.fromString(mbId), status);
    }
}
