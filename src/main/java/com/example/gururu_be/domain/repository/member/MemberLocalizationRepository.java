package com.example.gururu_be.domain.repository.member;

import com.example.gururu_be.domain.entity.member.MemberLocalization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MemberLocalizationRepository extends JpaRepository<MemberLocalization, UUID> {
}
