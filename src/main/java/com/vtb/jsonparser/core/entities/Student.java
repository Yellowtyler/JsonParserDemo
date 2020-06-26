package com.vtb.jsonparser.core.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "student")
@XmlType(propOrder = {"id", "firstName", "secondName", "phone", "email", "tasks"})
@XmlAccessorType(XmlAccessType.FIELD)
@JsonRootName(value = "student")
@JsonPropertyOrder({"id", "firstName", "secondName", "phone", "email", "tasks"})
@EqualsAndHashCode
@ToString
@Data
public class Student implements Entity {
    @XmlElement
    @JsonProperty("id")
    @NonNull
    private Long id;

    @XmlElement
    @JsonProperty("firstName")
    @NonNull
    private String firstName;

    @XmlElement
    @JsonProperty("secondName")
    @NonNull
    private String secondName;

    @XmlTransient
    @JsonIgnore
    @NonNull
    private String password;

    @XmlElement
    @JsonProperty("phone")
    @NonNull
    private String phone;

    @XmlElement
    @JsonProperty("email")
    @NonNull
    private String email;

    @XmlElementWrapper(name = "tasks")
    @XmlElement(name = "task")
    @JsonProperty("tasks")
    @NonNull
    private List<Task> tasks;

    public Student() {
    }

    public Student(Long id, String firstName, String secondName, String phone, String email, List<Task> tasks) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.phone = phone;
        this.email = email;
        this.tasks = tasks;
    }
}
