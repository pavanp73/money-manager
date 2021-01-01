package com.pavan.app.entities.base;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(generator = "uuid4")
    @GenericGenerator(name = "UUID", strategy = "uuid4")
    @Type(type = "org.hibernate.type.UUIDCharType")
    @Column(updatable = false, nullable = false, columnDefinition = "CHAR(36)")
    private UUID id;

    @Type(type = "org.hibernate.type.LocalDateTimeType")
    @Column(name = "created_time", nullable = false)
    private LocalDateTime createdTime;

    @Type(type = "org.hibernate.type.LocalDateTimeType")
    @Column(name = "updated_time", nullable = false)
    private LocalDateTime updatedTime;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @PrePersist
    public void setup() {
        if (createdTime == null) {
            createdTime = LocalDateTime.now();
        }
        updatedTime = LocalDateTime.now();
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                ", createdTime=" + createdTime +
                ", updatedTime=" + updatedTime +
                '}';
    }
}
