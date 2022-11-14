package com.example.gururu_be.domain.repository.review;


import com.example.gururu_be.domain.dto.review.ReviewDto;
import com.example.gururu_be.domain.entity.member.QMember;
import com.example.gururu_be.domain.entity.review.QReview;
import com.example.gururu_be.domain.entity.store.QStore;
import com.example.gururu_be.enumerate.StatusFlag;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ReviewDto> findAllReviewBymbId_DSL(UUID mbId) {
        QMember member = QMember.member;
        QReview review = QReview.review;

        return jpaQueryFactory
                .select(Projections.constructor(
                        ReviewDto.class,
                        review.member.id,
                        review.id,
                        review.reservation.id,
                        review.reviewImg,
                        review.reviewContent,
                        review.reviewCreatedDate,
                        review.reviewStar))
                .from(review)
                .leftJoin(member)
                .on(member.id.eq(review.member.id))
                .where(member.id.eq(mbId)
                        .and(review.delFlag.eq(StatusFlag.NORMAL)))
                .orderBy(review.reviewCreatedDate.desc())
                .distinct().fetch();
    }

    @Override
    public List<ReviewDto> findAllStoreReviewBymbId_DSL(UUID mbId) {
        QStore store = QStore.store;
        QReview review = QReview.review;

        return jpaQueryFactory
                .select(Projections.constructor(
                        ReviewDto.class,
                        review.member.id,
                        review.id,
                        review.reservation.id,
                        review.reviewImg,
                        review.reviewContent,
                        review.reviewCreatedDate,
                        review.reviewStar))
                .from(review)
                .leftJoin(store)
                .on(store.id.eq(review.member.id))
                .where(store.id.eq(mbId)
                        .and(review.delFlag.eq(StatusFlag.NORMAL)))
                .orderBy(review.reviewCreatedDate.desc())
                .distinct().fetch();
    }
}
