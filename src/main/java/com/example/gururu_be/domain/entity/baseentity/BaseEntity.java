package com.example.gururu_be.domain.entity.baseentity;

import com.example.gururu_be.enumerate.StatusFlag;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Getter
@MappedSuperclass
//@EntityListeners(AuditingEntityListener.class)
@ToString
public abstract class BaseEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Type(type = "org.hibernate.type.UUIDCharType")
    @Column(updatable = false, nullable = false)
    private UUID id;


    @Enumerated(EnumType.STRING)
    private StatusFlag delFlag = StatusFlag.NORMAL;

    public void delete() {
        this.delFlag = StatusFlag.DELETED;
    }
}
