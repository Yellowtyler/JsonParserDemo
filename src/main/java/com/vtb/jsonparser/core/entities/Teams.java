package com.vtb.jsonparser.core.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "teams")
@XmlType(propOrder = {"teams"})
@XmlAccessorType(XmlAccessType.FIELD)
@JsonRootName(value = "teams")
@JsonPropertyOrder({"teams"})
@EqualsAndHashCode
@ToString
@Data
public class Teams implements Entity {
    @XmlElement(name = "team")
    @JsonProperty("teams")
    @NonNull
    private List<Team> teams = new ArrayList<>();

    public Teams(@NonNull List<Team> teams) {
        this.teams = teams;
    }

    public Teams() {

    }

    public void addTeam(Team newTeam){
        teams.add(newTeam);
    }
}
