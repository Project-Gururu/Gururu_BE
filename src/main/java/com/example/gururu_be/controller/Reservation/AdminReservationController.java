package com.example.gururu_be.controller.Reservation;

import com.example.gururu_be.domain.dto.ResResultDto;
import com.example.gururu_be.domain.dto.reservation.AdminReservationDto;
import com.example.gururu_be.service.reservation.AdminReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/admin/v1.0")
@RequiredArgsConstructor
public class AdminReservationController {


    private final AdminReservationService adminReservationService;
    /**
     * M4-5 예약 상세 조회(사업자)
     */
//    @GetMapping("/{storeRegisterId}/reservation/{reservationId}")
//    public ResponseEntity<AdminReservationDto> storeGetOneReservation(@PathVariable String storeRegisterId, @PathVariable String reservationId) {
//
//        AdminReservationDto AdminReservationDto = adminReservationService.storeGetOneReservation(UUID.fromString(storeRegisterId),UUID.fromString(reservationId));
//
//        return ResponseEntity.ok(AdminReservationDto);
//    }
//
//
//    /**
//     * M4-6 예약 전체 조회(사업자)
//     */
//    @GetMapping("/{storeRegisterId}/reservation")
//    public List<AdminReservationDto> storeGetAllReservation(@PathVariable String storeRegisterId) {
//        return adminReservationService.storeGetAllReservation(UUID.fromString(storeRegisterId));
//    }
//

    /**
     * M4-7 예약 수락 거부
     */
    @PostMapping("/{storeRegisterId}/reservation/{reservationId}/{refuse}")
    public ResponseEntity<ResResultDto> waitingForAcceptance(@PathVariable String storeRegisterId, @PathVariable String reservationId, @PathVariable String refuse, @RequestBody AdminReservationDto adminReservationDto) {

        //사업자 생성 서비스 호출
        adminReservationService.waitingForAcceptance(UUID.fromString(storeRegisterId), UUID.fromString(reservationId), refuse, adminReservationDto);

        return ResponseEntity.ok(new ResResultDto("완료"));
    }
}
