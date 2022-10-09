package com.example.gururu_be.domain.repository.member;

import com.example.gururu_be.domain.entity.member.MemberLocal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MemberLocalRepository extends JpaRepository<MemberLocal, UUID>, MemberLocalRepositoryCustom {

}
