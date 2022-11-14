package com.example.gururu_be.service.review;

import com.example.gururu_be.domain.dto.review.ReviewDto;
import com.example.gururu_be.domain.entity.member.Member;
import com.example.gururu_be.domain.entity.reservation.Reservation;
import com.example.gururu_be.domain.entity.review.Review;
import com.example.gururu_be.domain.repository.member.MemberRepository;
import com.example.gururu_be.domain.repository.reservation.user.UserReservationRepository;
import com.example.gururu_be.domain.repository.review.ReviewRepository;
import com.example.gururu_be.enumerate.ReservationState;
import com.example.gururu_be.enumerate.StatusFlag;
import com.example.gururu_be.util.exception.ErrorCode;
import com.example.gururu_be.util.exception.RequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final MemberRepository memberRepository;
    private final UserReservationRepository userReservationRepository;
    private final ReviewRepository reviewRepository;

    /**
     * M5-1 리뷰 등록
     */
    @Transactional
    public void createReview(UUID mbId, UUID reservationId, ReviewDto reviewDto) {
        Optional<Member> optionalMember = memberRepository.findById(mbId);
        Member member = optionalMember.orElseThrow(() -> new RequestException(ErrorCode.MEMBER_LOGINID_NOT_FOUND_404));

        Optional<Reservation> optionalReservation = userReservationRepository.findById(reservationId);
        Reservation reservation = optionalReservation.orElseThrow(() -> new RequestException(ErrorCode.RESERVATION_NOT_FOUND_404));

        if (reservation.getReservationState().equals(ReservationState.COMPLETION)) {
            Review review = Review.builder()
                    .member(member)
                    .reservation(reservation)
                    .reviewImg(reviewDto.getReviewImg())
                    .reviewContent(reviewDto.getReviewContent())
                    .reviewCreatedDate(reviewDto.getReviewCreatedDate())
                    .reviewStar(reviewDto.getReviewStar())
                    .build();
            // 7. 새로 생성한 객체를 Repository 를 이용하여 DB에 저장한다
            reviewRepository.save(review);
        } else throw new RequestException(ErrorCode.COMMON_BAD_REQUEST_400);
    }


    /**
     * M5-2 리뷰 상세 조회 (유저)
     */
    public ReviewDto getOneReview(UUID mbId, UUID reservationId, UUID reviewId) {
        memberRepository.findById(mbId).orElseThrow(
                () -> new RequestException(ErrorCode.MEMBER_LOGINID_NOT_FOUND_404));

        userReservationRepository.findById(reservationId).orElseThrow(
                () -> new RequestException(ErrorCode.RESERVATION_NOT_FOUND_404));

        Optional<Review> optionalReview = reviewRepository.findById(reviewId);
        Review review = optionalReview.orElseThrow(
                () -> new RequestException(ErrorCode.REVIEW_NOT_FOUND_404));
        if (optionalReview.get().getDelFlag().equals(StatusFlag.DELETED)) {
            throw new RequestException(ErrorCode.REVIEW_DELETE_409);
        }
        return new ReviewDto(review);
    }

    /**
     * M5-3 리뷰 수정
     */
    @Transactional
    public void modifyReview(UUID mbId, UUID reservationId, UUID reviewId, ReviewDto reviewDto) {
        memberRepository.findById(mbId).orElseThrow(
                () -> new RequestException(ErrorCode.MEMBER_LOGINID_NOT_FOUND_404));

        userReservationRepository.findById(reservationId).orElseThrow(
                () -> new RequestException(ErrorCode.RESERVATION_NOT_FOUND_404));

        Optional<Review> optionalReview = reviewRepository.findById(reviewId);
        Review review = optionalReview.orElseThrow(
                () -> new RequestException(ErrorCode.REVIEW_NOT_FOUND_404));
        if (optionalReview.get().getDelFlag().equals(StatusFlag.DELETED)) {
            throw new RequestException(ErrorCode.REVIEW_DELETE_409);
        }
        review.updateReview(reviewDto);
    }

    /**
     * M5-4 리뷰 삭제
     */
    @Transactional
    public void deleteReview(UUID mbId, UUID reservationId, UUID reviewId) {
        memberRepository.findById(mbId).orElseThrow(
                () -> new RequestException(ErrorCode.MEMBER_LOGINID_NOT_FOUND_404));

        userReservationRepository.findById(reservationId).orElseThrow(
                () -> new RequestException(ErrorCode.RESERVATION_NOT_FOUND_404));

        Optional<Review> optionalReview = reviewRepository.findById(reviewId);
        Review review = optionalReview.orElseThrow(
                () -> new RequestException(ErrorCode.REVIEW_NOT_FOUND_404));
        if (optionalReview.get().getDelFlag().equals(StatusFlag.DELETED)) {
            throw new RequestException(ErrorCode.REVIEW_DELETE_409);
        }
        review.delete();
    }

    /**
     * M5-5 리뷰 전체 조회 (유저)
     */
    public List<ReviewDto> getAllReview(UUID mbId) {
        memberRepository.findById(mbId)
                .orElseThrow(() -> new RequestException(ErrorCode.MEMBER_LOGINID_NOT_FOUND_404));
        return reviewRepository.findAllReviewBymbId_DSL(mbId);
    }

    /**
     * M5-6 리뷰 전체 조회 (사업자)
     */
    public List<ReviewDto> storeGetAllReview(UUID mbId) {
        memberRepository.findById(mbId)
                .orElseThrow(() -> new RequestException(ErrorCode.MEMBER_LOGINID_NOT_FOUND_404));
        return reviewRepository.findAllStoreReviewBymbId_DSL(mbId);
    }

}
