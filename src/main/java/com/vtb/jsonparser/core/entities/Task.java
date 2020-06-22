package com.vtb.jsonparser.core.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

import java.util.List;

@JsonPropertyOrder({"id", "name", "status", "labels", "description"})
@EqualsAndHashCode
@ToString
@Data
public class Task {
    @JsonProperty("id")
    @NonNull
    private Long id;

    @JsonProperty("name")
    @NonNull
    private String name;

    @JsonProperty("description")
    @NonNull
    private String description;

    @JsonProperty("status")
    @NonNull
    private Status status;

    @JsonProperty("labels")
    @NonNull
    private List<Label> labels;

    public Task(Long id, String name, String description, Status status, List<Label> labels) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.labels = labels;
    }

    public Task() {

    }
}
