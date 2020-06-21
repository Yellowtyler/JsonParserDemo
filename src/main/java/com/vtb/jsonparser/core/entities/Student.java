package com.vtb.jsonparser.core.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

import java.util.List;

@JsonPropertyOrder({"id", "firstName", "secondName", "phone", "email", "tasks"})
@EqualsAndHashCode
@ToString
@Data
public class Student {
    @JsonProperty("id")
    @NonNull
    private Long id;

    @JsonProperty("firstName")
    @NonNull
    private String firstName;

    @JsonProperty("secondName")
    @NonNull
    private String secondName;

    @JsonIgnore
    @NonNull
    private String password;

    @JsonProperty("phone")
    @NonNull
    private String phone;

    @JsonProperty("email")
    @NonNull
    private String email;

    @JsonProperty("tasks")
    @NonNull
    private List<Task> subTasks;

    public Student() {
    }

    public Student(Long id, String firstName, String secondName, String phone, String email, List<Task> tasks) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.phone = phone;
        this.email = email;
        this.subTasks = tasks;
    }
}
