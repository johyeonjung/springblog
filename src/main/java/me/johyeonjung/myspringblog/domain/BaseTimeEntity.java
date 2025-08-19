package me.johyeonjung.myspringblog.domain;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.persistence.*;

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

// 모든 엔티티에 공통으로 쓰는 시간필드를 자동으로 채워주는 부모 클래스
// Article, Comment 가 이 클래스를  extends하면 DB컬럼이 따라가고, 값은 자동으로 들어감

@MappedSuperclass
//이 클래스를 직접 테이블로 만들지 않고, 자식 엔티티의 테이블에 컬럼만 상속시킴
//seclet*from base_time_entity 쿼리 X
//Article 테이블에 created_at, updated_at 컬럼이 생김

@EntityListeners(AuditingEntityListener.class)
//감사 리스너를 연결
//jpa가 엔티티를 저장/수정하는 시점에 이 리스너 호출, @CreatedDate, @LastModifiedDate 필드 자동 세팅
// @EnableJpaAuditing 켜둬야 동작(메인)
@Getter
public class BaseTimeEntity {

    //처음 insert 될 때 자동으로 채워지는 필드
    @CreatedDate
    @Column(name="created_at",updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name="updated_at",nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        if(createdAt == null) createdAt = LocalDateTime.now();
        if (updatedAt == null) updatedAt = createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

}
