package com.vtb.jsonparser.core.entities;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.xml.bind.annotation.XmlRootElement;

//@XmlRootElement(name = "entity")
//@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
//@JsonSubTypes({
//        @JsonSubTypes.Type(value = Team.class, name = "Team"),
//        @JsonSubTypes.Type(value = Student.class, name = "Student"),
//        @JsonSubTypes.Type(value = Task.class, name = "Task"),
//        @JsonSubTypes.Type(value = Label.class, name = "Label"),
//        @JsonSubTypes.Type(value = Status.class, name = "Status")
//})
public interface Entity {
}
