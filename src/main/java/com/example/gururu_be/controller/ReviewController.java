package com.example.gururu_be.controller;

import com.example.gururu_be.domain.dto.ResResultDto;
import com.example.gururu_be.domain.dto.review.ReviewDto;
import com.example.gururu_be.service.review.ReviewService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user/v1.0/review")
@RequiredArgsConstructor
public class ReviewController {
    
    private final ReviewService reviewService;
    /**
     * M5-1 리뷰 등록
     */
    @ApiOperation(value="M5-1 리뷰 등록", notes="종료된 예약의 회원의 리뷰 등록한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @RequestMapping(value="/{mbId}/{reservationId}", method={RequestMethod.POST})
    @PostMapping("/{mbId}/{reservationId}")
    public ResponseEntity<ResResultDto> createReview(@PathVariable String mbId, @PathVariable String reservationId, @RequestBody ReviewDto reviewDto) {
        
        reviewService.createReview(UUID.fromString(mbId), UUID.fromString(reservationId), reviewDto);

        return ResponseEntity.ok(new ResResultDto("리뷰 저장 성공"));
    }

    /**
     * M5-2 리뷰 상세 조회
     */
    @ApiOperation(value="M3-2 리뷰 상세 조회", notes="시스템에 등록된 회원의 리뷰를 상세 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @RequestMapping(value="/{mbId}/{reservationId}/{reviewId}", method={RequestMethod.GET})
    @GetMapping("/{mbId}/{reservationId}/{reviewId}")
    public ResponseEntity<ReviewDto> getOneReview(@PathVariable String mbId,@PathVariable String reservationId,@PathVariable String reviewId) {

        ReviewDto reviewDto = reviewService.getOneReview(UUID.fromString(mbId),UUID.fromString(reservationId),UUID.fromString(reviewId));

        return ResponseEntity.ok(reviewDto);
    }

    /**
     * M5-3 리뷰 수정
     */
    @ApiOperation(value="M5-3 리뷰 수정", notes="시스템에 등록된 회원의 리뷰를 수정한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @RequestMapping(value="/{mbId}/{reservationId}/{reviewId}", method={RequestMethod.PUT})
    @PutMapping("/{mbId}/{reservationId}/{reviewId}")
    public ResponseEntity<ResResultDto> modifyReview(@PathVariable String mbId, @PathVariable String reservationId, @PathVariable String reviewId, @RequestBody ReviewDto reviewDto) {

        //사업자 수정 서비스 호출
        reviewService.modifyReview(UUID.fromString(mbId),UUID.fromString(reservationId),UUID.fromString(reviewId),reviewDto);

        return ResponseEntity.ok(new ResResultDto("리뷰 수정 성공"));
    }

    /**
     * M5-4 리뷰 삭제
     */
    @ApiOperation(value="M5-4 리뷰 삭제", notes="시스템에 등록된 리뷰 정보 삭제한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @RequestMapping(value="/{mbId}/{reservationId}/{reviewId}", method={RequestMethod.DELETE})
    @DeleteMapping("/{mbId}/{reservationId}/{reviewId}")
    public ResponseEntity<ResResultDto> deleteReview(@PathVariable String mbId, @PathVariable String reservationId, @PathVariable String reviewId) {

        reviewService.deleteReview(UUID.fromString(mbId),UUID.fromString(reservationId),UUID.fromString(reviewId));

        return ResponseEntity.ok(new ResResultDto("리뷰 삭제 성공"));
    }

    /**
     * M3-5 유저 리뷰 전체조회
     */
    @ApiOperation(value="M3-5 유저 리뷰 전체조회", notes="시스템에 등록된 회원의 리뷰 정보를 전체조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @RequestMapping(value="/normal/{mbId}", method={RequestMethod.GET})
    @GetMapping("/normal/{mbId}")
    public List<ReviewDto> getAllReview(@PathVariable String mbId) {
        return reviewService.getAllReview(UUID.fromString(mbId));
    }


    /**
     * M3-6 사업자 리뷰 전체조회
     */
    @ApiOperation(value="M3-6 사업자 리뷰 전체조회", notes="시스템에 등록된 사업자의 리뷰 정보를 전체조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @RequestMapping(value="/store/{storeRegisterId}", method={RequestMethod.GET})
    @GetMapping("/store/{storeRegisterId}")
    public List<ReviewDto> storeGetAllReview(@PathVariable String mbId) {
        return reviewService.storeGetAllReview(UUID.fromString(mbId));
    }
}
