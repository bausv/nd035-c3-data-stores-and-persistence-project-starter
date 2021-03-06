package com.udacity.jdnd.course3.critter.model;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    @Column(name = "version", nullable = false)
    private int version;

    @NonNull
    @CreatedBy
    @Column(name = "created_by", nullable = false, updatable = false)
    private String createdBy;

    @NonNull
    @CreatedDate
    @Column(name = "created", nullable = false)
    private LocalDateTime created;

    @NonNull
    @LastModifiedBy
    @Column(name = "last_modified_by", nullable = false)
    private String lastModifiedBy;

    @NonNull
    @LastModifiedDate
    @Column(name = "last_modified", nullable = false)
    private LocalDateTime lastModified;

    public AbstractBaseEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @NonNull
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(@NonNull String createdBy) {
        this.createdBy = createdBy;
    }

    @NonNull
    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(@NonNull LocalDateTime created) {
        this.created = created;
    }

    @NonNull
    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(@NonNull String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    @NonNull
    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(@NonNull LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", version=" + version +
                ", createdBy='" + createdBy + '\'' +
                ", created=" + created +
                ", lastModifiedBy='" + lastModifiedBy + '\'' +
                ", lastModified=" + lastModified;
    }
}
