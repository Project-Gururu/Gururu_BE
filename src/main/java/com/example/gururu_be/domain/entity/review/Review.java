package com.example.gururu_be.domain.entity.review;

import com.example.gururu_be.domain.dto.review.ReviewDto;
import com.example.gururu_be.domain.entity.baseentity.BaseEntity;
import com.example.gururu_be.domain.entity.member.Member;
import com.example.gururu_be.domain.entity.reservation.Reservation;
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
@Table(name = "REVIEW")
public class Review extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mbId")
    private Member member;

    // 1: 1 엔티티에서 reservationId 외래키를 뜻함
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservationId")
    private Reservation reservation;

    @Column(columnDefinition = "LONGTEXT")
    private String reviewImg;

    @NotNull
    @Size(max = 255)
    private String reviewContent;

    @NotNull
    @Size(max = 50)
    private String reviewCreatedDate;

    @NotNull
    @Size(max = 5)
    private Float reviewStar;

    public void updateReview(ReviewDto reviewDto) {
        this.reviewImg = reviewDto.getReviewImg();
        this.reviewContent = reviewDto.getReviewContent();
        this.reviewCreatedDate = reviewDto.getReviewCreatedDate();
        this.reviewStar = reviewDto.getReviewStar();
    }
}
