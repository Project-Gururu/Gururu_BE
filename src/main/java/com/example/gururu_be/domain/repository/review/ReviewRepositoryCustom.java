package com.example.gururu_be.domain.repository.review;

import com.example.gururu_be.domain.dto.review.ReviewDto;

import java.util.List;
import java.util.UUID;

public interface ReviewRepositoryCustom {
    List<ReviewDto> findAllReviewBymbId_DSL(UUID mbId);
    List<ReviewDto> findAllStoreReviewBymbId_DSL(UUID mbId);
}
