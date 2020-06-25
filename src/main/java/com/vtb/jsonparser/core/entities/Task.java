package com.vtb.jsonparser.core.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

import javax.xml.bind.annotation.*;
import java.util.List;


@XmlRootElement(name = "task")
@XmlType(propOrder = {"id", "name", "status", "labels", "description"})
@XmlAccessorType(XmlAccessType.FIELD)
@JsonRootName(value = "task")
@JsonPropertyOrder({"id", "name", "status", "labels", "description"})
@EqualsAndHashCode
@ToString
@Data
public class Task implements Entity {
    @XmlElement
    @JsonProperty("id")
    @NonNull
    private Long id;

    @XmlElement
    @JsonProperty("name")
    @NonNull
    private String name;

    @XmlElement
    @JsonProperty("description")
    @NonNull
    private String description;

    @XmlElement
    @JsonProperty("status")
    @NonNull
    private Status status;

    @XmlElementWrapper(name = "labels")
    @XmlElement(name = "label")
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
