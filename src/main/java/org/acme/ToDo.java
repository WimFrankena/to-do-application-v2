package org.acme;
import java.util.concurrent.atomic.*;
import static java.util.Objects.requireNonNull;

public class ToDo {

    private Long id;
    private String name;
    private String description;

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

    private static AtomicLong ID_Generator = new AtomicLong(1000);

    public Long getId() {
        return id;
    }

    public void setId() {
        id = ID_Generator.getAndIncrement();
        this.id = requireNonNull(id);
    }
}