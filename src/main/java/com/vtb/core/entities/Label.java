package com.vtb.core.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Label {
    VER_CONTROL,
    VIRTUALIZATION,
    JAVA_PROGRAMMING
}
