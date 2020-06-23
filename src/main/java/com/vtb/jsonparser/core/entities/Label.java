package com.vtb.jsonparser.core.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Data
public class Label {
    @JsonProperty("name")
    @NonNull
    private String name;

    public Label(String name) {
        this.name = name;
    }
    public Label() {}
}
