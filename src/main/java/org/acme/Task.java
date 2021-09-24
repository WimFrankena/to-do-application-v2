package org.acme;

import javax.validation.constraints.NotEmpty;

import static java.util.Objects.requireNonNull;

public class Task {
    private Long id;
    @NotEmpty private String name;
    private String description;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        //make this autoGenerated, have to call setId inside if tasks[] is not empty or null
        // be careful because each task needs an id, so have to call setId for each element in the array
        this.id = requireNonNull(id);
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
