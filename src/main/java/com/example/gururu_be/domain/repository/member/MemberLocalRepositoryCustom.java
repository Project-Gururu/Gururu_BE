package com.example.gururu_be.domain.repository.member;

import com.example.gururu_be.domain.dto.member.MemberLocalDto;

import java.util.List;
import java.util.UUID;

public interface MemberLocalRepositoryCustom {
    List<MemberLocalDto> findAllMemberLocalBymbId_DSL(UUID mbId);
}
