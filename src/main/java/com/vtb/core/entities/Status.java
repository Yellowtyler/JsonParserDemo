package com.vtb.core.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Status {
    DONE,
    IN_PROCESS,
    REVIEW,
    IN_REVIEW
}
