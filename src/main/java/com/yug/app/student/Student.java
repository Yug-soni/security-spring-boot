package com.yug.app.student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter @ToString
@AllArgsConstructor
public class Student {

    private final Integer studentId;
    private final String studentName;

}
