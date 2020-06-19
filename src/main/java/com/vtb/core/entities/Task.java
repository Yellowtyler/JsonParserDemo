package com.vtb.core.entities;

public class Task {
    private Long id;

    private String name;

    private String description;

    private Status status;

    private Label label;

    public Task(Long id, String name, String description, Status status, Label label) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.label = label;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }
}
