package com.vtb.jsonparser.core.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "status")
@XmlEnum
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Status implements Entity {
    DONE("done"),
    IN_PROCESS("in_process"),
    IN_REVIEW("in_review"),
    OPEN("open");

    private String name;

    Status(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }
}
