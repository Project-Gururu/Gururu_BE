package com.example.gururu_be.domain.repository.review;

import com.example.gururu_be.domain.entity.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID>, ReviewRepositoryCustom {
}
