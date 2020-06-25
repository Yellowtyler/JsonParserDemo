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

@XmlRootElement(name = "team")
@XmlType(propOrder = {"id", "name", "students", "tasks"})
@XmlAccessorType(XmlAccessType.FIELD)
@JsonRootName(value = "team")
@JsonPropertyOrder({"id", "name", "students", "tasks"})
@EqualsAndHashCode
@ToString
@Data
public class Team implements Entity {
    @XmlElement
    @JsonProperty("id")
    @NonNull
    private Long id;

    @XmlElement
    @JsonProperty("name")
    @NonNull
    private String name;

    @XmlElementWrapper(name = "students")
    @XmlElement(name = "student")
    @JsonProperty("students")
    @NonNull
    private List<Student> students;

    @XmlElementWrapper(name = "tasks")
    @XmlElement(name = "task")
    @JsonProperty("tasks")
    @NonNull
    private List<Task> tasks;

    public Team() {
    }

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
