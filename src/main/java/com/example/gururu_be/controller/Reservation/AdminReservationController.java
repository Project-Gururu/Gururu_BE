package com.example.gururu_be.controller.Reservation;

import com.example.gururu_be.domain.dto.ResResultDto;
import com.example.gururu_be.domain.dto.reservation.AdminReservationDto;
import com.example.gururu_be.service.reservation.AdminReservationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/admin/v1.0")
@RequiredArgsConstructor
public class AdminReservationController {


    private final AdminReservationService adminReservationService;
    /**
     * M4-6 예약 상세 조회(사업자)
     */
    @ApiOperation(value="M4-6 예약 상세 조회(사업자)", notes="시스템에 등록된 사업자의 예약 상세 조회(사업자)한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @RequestMapping(value="/admin/v1.0/{storeRegisterId}/reservation/{reservationId}", method={RequestMethod.GET})
    @GetMapping("/{storeRegisterId}/reservation/{reservationId}")
    public ResponseEntity<AdminReservationDto> storeGetOneReservation(@PathVariable String storeRegisterId, @PathVariable String reservationId) {

        AdminReservationDto AdminReservationDto = adminReservationService.storeGetOneReservation(UUID.fromString(storeRegisterId),UUID.fromString(reservationId));

        return ResponseEntity.ok(AdminReservationDto);
    }


    /**
     * M4-7 예약 전체 조회(사업자)
     */
    @ApiOperation(value="M4-7 예약 전체 조회(사업자)", notes="시스템에 등록된 사업자의 예약 전체 조회(사업자)한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @RequestMapping(value="/admin/v1.0/{storeRegisterId}/reservation/all/{status}", method={RequestMethod.GET})
    @GetMapping("/{storeRegisterId}/reservation/all/{status}")
    public List<AdminReservationDto> storeGetAllReservation(@PathVariable String storeRegisterId, @PathVariable String status) {
        return adminReservationService.storeGetAllReservation(UUID.fromString(storeRegisterId), status);
    }


    /**
     * M4-8 예약 전체 조회 카운트(사업자)
     */
    @ApiOperation(value="M4-8 예약 전체 조회 카운트(사업자)", notes="시스템에 등록된 사업자의 예약 전체 조회 카운트(사업자)")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @RequestMapping(value="/admin/v1.0/{storeRegisterId}/reservation/allCount/{status}", method={RequestMethod.GET})
    @GetMapping("/{storeRegisterId}/reservation/allCount/{status}")
    public int getAllCountReservation(@PathVariable String storeRegisterId, @PathVariable String status) {
        return adminReservationService.getAllCountReservation(UUID.fromString(storeRegisterId), status);
    }


    /**
     * M4-9 예약 수락 거부
     */
    @ApiOperation(value="M4-9 예약 수락 거부", notes="시스템에 등록된 회원의 예약 수락/거부한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @RequestMapping(value="/admin/v1.0/{storeRegisterId}/reservation/{reservationId}/{refuse}", method={RequestMethod.POST})
    @PostMapping("/{storeRegisterId}/reservation/{reservationId}/{refuse}")
    public ResponseEntity<ResResultDto> waitingForAcceptance(@PathVariable String storeRegisterId, @PathVariable String reservationId, @PathVariable String refuse, @RequestBody AdminReservationDto adminReservationDto) {

        //사업자 생성 서비스 호출
        adminReservationService.waitingForAcceptance(UUID.fromString(storeRegisterId), UUID.fromString(reservationId), refuse, adminReservationDto);

        return ResponseEntity.ok(new ResResultDto("완료"));
    }


    /**
     * M4-10 예약 스케줄
     */
    @ApiOperation(value="M4-10 예약 스케줄", notes="시스템에 등록된 사업자의 예약 스케줄")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @RequestMapping(value="/admin/v1.0/{storeRegisterId}/reservation/schedule/{reservationDay}", method={RequestMethod.GET})
    @GetMapping("/{storeRegisterId}/reservation/schedule/{reservationDay}")
    public List<AdminReservationDto> getAllreservationSchedule(@PathVariable String storeRegisterId, @PathVariable String reservationDay) {
        return adminReservationService.getAllreservationSchedule(UUID.fromString(storeRegisterId), reservationDay);
    }
}
