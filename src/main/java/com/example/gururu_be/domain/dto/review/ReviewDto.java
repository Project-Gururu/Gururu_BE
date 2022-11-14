package com.example.gururu_be.domain.dto.review;

import com.example.gururu_be.domain.entity.review.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDto {

    private UUID mbId;

    private UUID reviewId;

    private UUID reservationId;

    private String reviewImg;

    private String reviewContent;

    private String reviewCreatedDate;

    private Float reviewStar;

    public ReviewDto(Review review) {
        this.mbId = review.getMember().getId();
        this.reviewId = review.getId();
        this.reservationId = review.getReservation().getId();
        this.reviewImg = review.getReviewImg();
        this.reviewContent = review.getReviewContent();
        this.reviewCreatedDate = review.getReviewCreatedDate();
        this.reviewStar = review.getReviewStar();
    }
}
