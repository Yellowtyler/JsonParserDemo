package com.vtb.core.entities;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Label {
    VER_CONTROL("ver_control"),
    VIRTUALIZATION("virtualization"),
    JAVA_PROGRAMMING("java_programming");

    private String name;

    Label(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }

}
