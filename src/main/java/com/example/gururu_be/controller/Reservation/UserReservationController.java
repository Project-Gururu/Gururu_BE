package com.example.gururu_be.controller.Reservation;

import com.example.gururu_be.domain.dto.ResResultDto;
import com.example.gururu_be.domain.dto.reservation.UserReservationDto;
import com.example.gururu_be.service.reservation.UserReservationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ApiOperation(value="M4-1 예약 등록", notes="시스템에 등록된 회원의 예약 등록한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @RequestMapping(value="/{mbId}/reservation/{storeRegisterId}/{petId}/{beauticianId}/{productId}", method={RequestMethod.POST})
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
    @ApiOperation(value="M4-2 예약 취소", notes="시스템에 등록된 회원의 예약 취소한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @RequestMapping(value="/{mbId}/reservation/{reservationId}", method={RequestMethod.DELETE})
    @DeleteMapping("/{mbId}/reservation/{reservationId}")
    public ResponseEntity<ResResultDto> deleteReservation(@PathVariable String mbId, @PathVariable String reservationId) {

        userReservationService.deleteReservation(UUID.fromString(mbId), UUID.fromString(reservationId));

        return ResponseEntity.ok(new ResResultDto("예약 취소 성공"));
    }


    /**
     * M4-3 예약 상세 조회 (유저)
     */
    @ApiOperation(value="M4-3 예약 상세 조회 (유저)", notes="시스템에 등록된 회원의 예약 상세 조회(유저)한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @RequestMapping(value="/{mbId}/reservation/{reservationId}", method={RequestMethod.GET})
    @GetMapping("/{mbId}/reservation/{reservationId}")
    public ResponseEntity<UserReservationDto> getOneReservation(@PathVariable String mbId, @PathVariable String reservationId) {

        UserReservationDto UserReservationDto = userReservationService.getOneReservation(UUID.fromString(mbId),UUID.fromString(reservationId));

        return ResponseEntity.ok(UserReservationDto);
    }


    /**
     * M4-4 예약 전체 조회 (유저)
     */
    @ApiOperation(value="M4-4 예약 전체 조회 (유저)", notes="시스템에 등록된 회원의 예약 전체 조회 (유저)한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @RequestMapping(value="/{mbId}/reservation/all/{status}", method={RequestMethod.GET})
    @GetMapping("/{mbId}/reservation/all/{status}")
    public List<UserReservationDto> getAllReservation(@PathVariable String mbId, @PathVariable String status) {
        return userReservationService.getAllReservation(UUID.fromString(mbId), status);
    }

    /**
     * M4-5 예약 전체 조회 카운트 (유저)
     */
    @ApiOperation(value="M4-5 예약 전체 조회 카운트 (유저)", notes="시스템에 등록된 회원의 예약 전체 조회 카운트 (유저)")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @RequestMapping(value="/{mbId}/reservation/allCount/{status}", method={RequestMethod.GET})
    @GetMapping("/{mbId}/reservation/allCount/{status}")
    public int getAllCountReservation(@PathVariable String mbId, @PathVariable String status) {
        return userReservationService.getAllCountReservation(UUID.fromString(mbId), status);
    }
}
