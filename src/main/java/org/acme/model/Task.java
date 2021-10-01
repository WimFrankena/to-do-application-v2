package org.acme.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

import java.util.concurrent.atomic.AtomicLong;

import static java.util.Objects.requireNonNull;

public class Task {
    private Long id;
    @NotEmpty private String name;
    private String description;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = requireNonNull(id);
    }

    private static AtomicLong ID_Generator = new AtomicLong(1000);

    public void generateId() {
        id = ID_Generator.getAndIncrement();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
