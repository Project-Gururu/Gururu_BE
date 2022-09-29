package com.example.gururu_be.domain.repository.member;

import com.example.gururu_be.domain.dto.member.MemberLocalDto;
import com.example.gururu_be.domain.entity.member.QMember;
import com.example.gururu_be.domain.entity.member.QMemberLocal;
import com.example.gururu_be.enumerate.StatusFlag;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class MemberLocalRepositoryImpl implements MemberLocalRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<MemberLocalDto> findAllMemberLocalBymbId_DSL(UUID mbId) {
        QMember member = QMember.member;
        QMemberLocal memberLocal = QMemberLocal.memberLocal;

        return jpaQueryFactory
                .select(Projections.constructor(
                        MemberLocalDto.class,
                        memberLocal.member.id,
                        memberLocal.id,
                        memberLocal.addrsName,
                        memberLocal.memberAddrs))
                .from(memberLocal)
                .leftJoin(member)
                .on(member.id.eq(memberLocal.member.id))
                .where(member.id.eq(mbId)
                        .and(memberLocal.delFlag.eq(StatusFlag.NORMAL)))
                .distinct().fetch();
    }
}
