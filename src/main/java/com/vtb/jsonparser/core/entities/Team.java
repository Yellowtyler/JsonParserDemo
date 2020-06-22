package com.vtb.jsonparser.core.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

import java.util.List;

@JsonPropertyOrder({"id", "name", "students", "tasks"})
@EqualsAndHashCode
@ToString
@Data
public class Team {
    @JsonProperty("id")
    @NonNull
    private Long id;

    @JsonProperty("name")
    @NonNull
    private String name;

    @JsonProperty("students")
    @NonNull
    private List<Student> students;

    @JsonProperty("tasks")
    @NonNull
    private List<Task> tasks;

    public Team() {}
    public Team(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Team(Long id, String name, List<Student> students, List<Task> tasks) {
        this.id = id;
        this.name = name;
        this.students = students;
        this.tasks = tasks;
    }

    public void addStudent(Student student) {
        if (student != null) {
            students.add(student);
        }
    }

    public void addTask(Task task) {
        if (task != null) {
            tasks.add(task);
        }
    }
}
