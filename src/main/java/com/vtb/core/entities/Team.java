package com.vtb.core.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonPropertyOrder({"id", "name", "students", "tasks"})
public class Team {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("students")
    private List<Student> students;

    @JsonProperty("tasks")
    private List<Task> tasks;

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
        students.add(student);
    }

    public void addTask(Task task) {
        tasks.add(task);
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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
