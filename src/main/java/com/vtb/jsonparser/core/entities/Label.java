package com.vtb.jsonparser.core.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "label")
@XmlType(propOrder = {"name"})
@XmlAccessorType(XmlAccessType.FIELD)
@EqualsAndHashCode
@ToString
@Data
public class Label implements Entity {
    @XmlElement
    @JsonProperty("name")
    @NonNull
    private String name;

    public Label(String name) {
        this.name = name;
    }

    public Label() {
    }
}
