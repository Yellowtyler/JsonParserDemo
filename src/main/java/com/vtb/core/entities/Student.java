package com.vtb.core.entities;

import com.fasterxml.jackson.annotation.*;

import java.util.ArrayList;

@JsonPropertyOrder({"id", "firstName", "secondName", "phone", "email", "tasks"})
public class Student {

    private Long id;
    private String firstName;
    private String secondName;
    @JsonIgnore
    private String password;
    private String phone;
    private String email;
    @JsonProperty("tasks")
    private ArrayList<Task> subTasks;

    @JsonCreator
    public Student( @JsonProperty("id") Long id, @JsonProperty("firstName") String firstName,
                    @JsonProperty("secondName") String secondName, String password,
                    @JsonProperty("phone") String phone, @JsonProperty("email")String email,
                    @JsonProperty("tasks") ArrayList<Task> tasks) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.subTasks = tasks;
    }

    @JsonGetter("id")
    public Long getId() {
        return id;
    }

    @JsonSetter("id")
    public void setId(Long id) {
        this.id = id;
    }

    @JsonGetter("firstName")
    public String getFirstName() {
        return firstName;
    }

    @JsonSetter("firstName")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonGetter("secondName")
    public String getSecondName() {
        return secondName;
    }

    @JsonSetter("secondName")
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonGetter("phone")
    public String getPhone() {
        return phone;
    }

    @JsonSetter("phone")
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @JsonGetter("email")
    public String getEmail() {
        return email;
    }

    @JsonSetter("email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonGetter("tasks")
    public ArrayList<Task> getTasks() {
        return subTasks;
    }

    @JsonSetter("tasks")
    public void setTasks(ArrayList<Task> tasks) {
        this.subTasks = tasks;
    }
}
