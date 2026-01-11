package com.mytesting.app.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.time.LocalDateTime;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@MappedSuperclass
public abstract class BaseEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(readOnly = true)
    public Long id;

    @Schema(readOnly = true, description = "Fecha de creación del registro")
    @Column(name = "created_at", updatable = false)
    public LocalDateTime createdAt;

    @Schema(readOnly = true, description = "Fecha de última actualización")
    @Column(name = "updated_at")
    public LocalDateTime updatedAt;

    @Schema(hidden = true)
    @Column(name = "deleted", nullable = false)
    public boolean deleted = false;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
